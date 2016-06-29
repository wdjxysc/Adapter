package com.dijun.adapter.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.dijun.adapter.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

public class NetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);

        getAreas();

//        weatherTest();

//        testNetManager();
    }


    /**
     * 获取某县下属街道信息 淘宝物流API
     */
    void getAreas(){
        //https://lsp.wuliu.taobao.com/locationservice/addr/output_address_town_array.do?l1=110000&l2=110100&l3=110101

        String str = "https://lsp.wuliu.taobao.com/locationservice/addr/output_address_town_array.do";

        //使用了OkHttpUtils 封装类
        OkHttpUtils.get()
                .url(str)
                .addParams("l1", "110000")
                .addParams("l2", "110100")
                .addParams("l3", "110101")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("okhttp", "failed" + id);

                        Toast.makeText(getApplication(), "failed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("okhttp", id + "------" + response);


                        String json = response.substring(response.indexOf("(") + 1,response.lastIndexOf(")"));

                        JSONTokener jsonParser = new JSONTokener(json);
                        JSONObject result;
                        try {
                            result = (JSONObject) jsonParser.nextValue();
                            JSONArray list = result.getJSONArray("result");
                            int size = list.length();

                            ArrayList<String> areaNameList = new ArrayList<String>();
                            for (int i=0;i<size;i++){

                                JSONArray item = list.getJSONArray(i);
                                areaNameList.add(item.get(1).toString());
                            }

                            Log.i("wdj", size + "");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        Toast.makeText(getApplication(), "response id:" + id, Toast.LENGTH_SHORT).show();


                    }
                });
    }


    /**
     * 获取某城市天气API
     */
    void weatherTest(){
        //百度API市场
        String str = "http://apis.baidu.com/apistore/weatherservice/cityname";

        //使用了OkHttpUtils 封装类
        OkHttpUtils.get()
                .url(str)
                .addHeader("apikey", getResources().getString(R.string.my_baidu_api_key))
                .addParams("cityname", "武穴")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("okhttp", "failed" + id);

                        Toast.makeText(getApplication(), "failed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("okhttp", id + "------" + response);

                        Toast.makeText(getApplication(), "response id:" + id, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private static final String DEBUG_TAG = "NetworkStatusExample";

    private void testNetManager() {
        //能获取网络连接信息 但不能获取当前连接wifi密码  只能获取root权限后在手机缓存文件里读取
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connMgr.getAllNetworkInfo();
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        Network[] network = connMgr.getAllNetworks();
//        NetworkInfo networkInfo2 = connMgr.getNetworkInfo(network[0]);
        boolean isWifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();
        Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);


        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();

        try {
            List<MyWifiInfo> myWifiInfos = Read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<MyWifiInfo> Read() throws Exception {
        List<MyWifiInfo> wifiInfos = new ArrayList<MyWifiInfo>();

        Process process = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        StringBuffer wifiConf = new StringBuffer();
        try {
            process = Runtime.getRuntime().exec("su");//
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            dataInputStream = new DataInputStream(process.getInputStream());
            dataOutputStream
                    .writeBytes("cat /data/misc/wifi/*.conf\n");
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    dataInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                wifiConf.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            process.waitFor();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                process.destroy();
            } catch (Exception e) {
                throw e;
            }
        }


        Pattern network = Pattern.compile("network=\\{([^\\}]+)\\}", Pattern.DOTALL);
        Matcher networkMatcher = network.matcher(wifiConf.toString());
        while (networkMatcher.find()) {
            String networkBlock = networkMatcher.group();
            Pattern ssid = Pattern.compile("ssid=\"([^\"]+)\"");
            Matcher ssidMatcher = ssid.matcher(networkBlock);

            if (ssidMatcher.find()) {
                MyWifiInfo wifiInfo = new MyWifiInfo();
                wifiInfo.Ssid = ssidMatcher.group(1);
                Pattern psk = Pattern.compile("psk=\"([^\"]+)\"");
                Matcher pskMatcher = psk.matcher(networkBlock);
                if (pskMatcher.find()) {
                    wifiInfo.Password = pskMatcher.group(1);
                } else {
                    wifiInfo.Password = "无密码";
                }
                wifiInfos.add(wifiInfo);
            }

        }

        return wifiInfos;
    }

    public class MyWifiInfo {
        public String Ssid="";
        public String Password="";
    }
}
