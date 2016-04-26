package com.dijun.adapter;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;

public class DialogActivity extends AppCompatActivity implements Runnable {


    /*显示点击的内容*/
    private void showClickMessage(String message)
    {
        Toast.makeText(DialogActivity.this, "你选择的是: "+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DialogActivity.this);
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setTitle("普通的对话框");
                alertDialog.setMessage("普通对话框的message内容");

                alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        showClickMessage("确定");
                    }
                });
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        showClickMessage("取消");
                    }
                });
                alertDialog.create().show();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder multiDia=new AlertDialog.Builder(DialogActivity.this);
                multiDia.setTitle("多选项对话框");
                multiDia.setPositiveButton("按钮一", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        showClickMessage("按钮一");
                    }
                });
                multiDia.setNeutralButton("按钮二", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        showClickMessage("按钮二");
                    }
                });
                multiDia.setNegativeButton("按钮三", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        showClickMessage("按钮三");
                    }
                });
                multiDia.create().show();

            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] mList={"选项1","选项2","选项3","选项4","选项5","选项6","选项7"};
                AlertDialog.Builder listDia=new AlertDialog.Builder(DialogActivity.this);
                listDia.setTitle("列表对话框");
                listDia.setItems(mList, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                /*下标是从0开始的*/
                        showClickMessage(mList[which]);
                    }
                });
                listDia.create().show();
            }
        });


        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            int yourChose=-1;

            @Override
            public void onClick(View v) {
                final String[] mList={"选项1","选项2","选项3","选项4","选项5","选项6","选项7"};
                yourChose=-1;
                AlertDialog.Builder sinChosDia=new AlertDialog.Builder(DialogActivity.this);
                sinChosDia.setTitle("单项选择对话框");
                sinChosDia.setSingleChoiceItems(mList, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        yourChose=which;

                    }
                });
                sinChosDia.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        if(yourChose!=-1)
                        {
                            showClickMessage(mList[yourChose]);
                        }
                    }
                });
                sinChosDia.create().show();
            }
        });


        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReadProcess();
            }
        });


        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProcessDia();
            }
        });

        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDia();
            }
        });

        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCusPopUp(v);
            }
        });

        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMultiChosDia();
            }
        });

        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSinChosDia();
            }
        });
    }


    /*单项选择对话框*/
    int yourChose=-1;
    private void showSinChosDia()
    {
        final String[] mList={"选项1","选项2","选项3","选项4","选项5","选项6","选项7"};
        yourChose=-1;
        AlertDialog.Builder sinChosDia=new AlertDialog.Builder(DialogActivity.this);
        sinChosDia.setTitle("单项选择对话框");
        sinChosDia.setSingleChoiceItems(mList, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                yourChose=which;
            }
        });
        sinChosDia.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if(yourChose!=-1)
                {
                    showClickMessage(mList[yourChose]);
                }
            }
        });
        sinChosDia.create().show();
    }


    ArrayList<Integer> myChose= new ArrayList<Integer>();
    private void showMultiChosDia()
    {
        final String[] mList={"选项1","选项2","选项3","选项4","选项5","选项6","选项7"};
        final boolean mChoseSts[]={false,false,false,false,false,false,false};
        myChose.clear();
        AlertDialog.Builder multiChosDia=new AlertDialog.Builder(DialogActivity.this);
        multiChosDia.setTitle("多项选择对话框");
        multiChosDia.setMultiChoiceItems(mList, mChoseSts, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked)
                {
                    myChose.add(which);
                }
                else
                {
                    myChose.remove(which);
                }
            }
        });
        multiChosDia.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                int size=myChose.size();
                String str="";
                for(int i=0;i<size;i++)
                {
                    str+=mList[myChose.get(i)];
                }
                showClickMessage(str);
            }
        });
        multiChosDia.create().show();
    }


    //进度读取框需要模拟读取
    ProgressDialog mReadProcessDia=null;
    public final static int MAX_READPROCESS = 100;
    private void showReadProcess()
    {
        mReadProcessDia=new ProgressDialog(DialogActivity.this);
        mReadProcessDia.setProgress(0);
        mReadProcessDia.setTitle("进度条窗口");
        mReadProcessDia.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mReadProcessDia.setMax(MAX_READPROCESS);
        mReadProcessDia.show();
        new Thread(this).start();
    }

    //新开启一个线程，循环的累加，一直到100然后在停止
    @Override
    public void run()
    {
        int Progress= 0;
        while(Progress < MAX_READPROCESS)
        {
            try {
                Thread.sleep(100);
                Progress++;
                mReadProcessDia.incrementProgressBy(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //读取完了以后窗口自消失
        mReadProcessDia.cancel();
    }

    /*读取中的对话框*/
    private void showProcessDia()
    {
        ProgressDialog processDia= new ProgressDialog(DialogActivity.this);
        processDia.setTitle("进度条框");
        processDia.setMessage("内容读取中...");
        processDia.setIndeterminate(true);
        processDia.setCancelable(true);
        processDia.show();
    }

    /*自定义对话框*/
    private void showCustomDia()
    {
        AlertDialog.Builder customDia=new AlertDialog.Builder(DialogActivity.this);
        final View viewDia=LayoutInflater.from(DialogActivity.this).inflate(R.layout.custom_dialog, null);
        customDia.setTitle("自定义对话框");
        customDia.setView(viewDia);
        customDia.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                EditText diaInput=(EditText) viewDia.findViewById(R.id.txt_cusDiaInput);
                showClickMessage(diaInput.getText().toString());
            }
        });
        customDia.create().show();
    }


    private PopupWindow window=null;
    private Button cusPopupBtn1;
    private View popupView;

    /*popup window 来实现*/
    private void showCusPopUp(View parent)
    {
        if(window == null)
        {
            popupView= LayoutInflater.from(DialogActivity.this).inflate(R.layout.dia_cuspopup_dia, null);
            cusPopupBtn1=(Button)popupView.findViewById(R.id.diaCusPopupSure);
            window =new PopupWindow(popupView, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
        }
        window.setAnimationStyle(R.style.PopupAnimation);
        /*必须调用setBackgroundDrawable， 因为popupwindow在初始时，会检测background是否为null,如果是onTouch or onKey events就不会相应，所以必须设置background*/
        /*网上也有很多人说，弹出pop之后，不响应键盘事件了，这个其实是焦点在pop里面的view去了。*/
        window.setFocusable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.update();
        window.showAtLocation(parent, Gravity.CENTER_VERTICAL, 0, 0);
        cusPopupBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showClickMessage("popup window的确定");
            }
        });
    }


}
