package com.dijun.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseAdapterActivity extends Activity {

    BaseAdapter baseAdapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);

        listView = (ListView)findViewById(R.id.listView);



        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", R.mipmap.ic_launcher);
            map.put("title", "这是标题" + i);
            map.put("content", "这是内容" + i);
            map.put("select", false);
            map.put("select2", false);
            list.add(map);
        }

        MyBaseAdapter adapter = new MyBaseAdapter(this, list, R.layout.base_layout_item,
                new String[] {"img", "title", "content", "select", "select2"},
                new int[] {R.id.iv_img, R.id.tv_title, R.id.tv_content, R.id.cb_select, R.id.cb_select2});
        listView.setAdapter(adapter);
    }



    private class MyBaseAdapter extends BaseAdapter{

        //Map列表，一个Map保存一个列表项数据
        private List<? extends Map<String, ?>> mData;
        //列表项对应布局文件的id号
        private int mLayoutId;
        //Map列表数据对应的键值（即通过键值访问数据）
        private String[] mKey;
        //列表项布局中各个控件的id号
        private int[] mResId;
        //列表项布局
        private LayoutInflater mItemLayout;

        /**
         * 构造方法
         * @param context 应用程序上下文
         * @param data Map列表，一个Map保存一个列表项数据
         * @param layoutId 列表项对应布局文件的id号
         * @param key Map列表数据对应的键值（即通过键值访问数据）
         * @param resId 列表项布局中各个控件的id号
         */
        public MyBaseAdapter(Context context, List<? extends Map<String, ?>> data, int layoutId, String[] key, int[] resId){
            this.mData = data;
            this.mKey = key;
            this.mResId = resId;
            this.mLayoutId = layoutId;

            mItemLayout = LayoutInflater.from(context);
        }

        /**
         * 获取列表项数据条数
         */
        @Override
        public int getCount() {
            return mData.size();
        }

        /**
         * 获取对应位置的列表项数据
         */
        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        /**
         * 返回对应列表项的位置
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //获取对应位置的列表项数据
            final Map dataSet = mData.get(position);
            ViewHolder holder = null;
            final String[] key = mKey;
            final int[] resId = mResId;
            //获取列表项布局控件总数
            final int count = resId.length;


            //如果未缓存布局
            if (convertView == null) {
                //加载列表项布局
                convertView = mItemLayout.inflate(this.mLayoutId, null);

                holder = new ViewHolder();
                for (int i = 0; i < count; i++) {
                    //把列表项布局的控件加入holder
                    holder.put(resId[i], convertView.findViewById(resId[i]));
                }
                //保存holder
                convertView.setTag(holder);
            } else {  //如果已经缓存布局，直接取出holder
                holder = (ViewHolder) convertView.getTag();
            }


            for (int i = 0; i < count; i++) {
                //获取对应键值的数据
                final Object data = dataSet.get(key[i]);
                //获取对应资源id的控件
                View v = holder.get(resId[i]);

                if (v instanceof CheckBox) {  //设置CheckBox选中状态
                    CheckBox cb = (CheckBox) v;
                    cb.setChecked((Boolean) data);

                    final int pos = position;
                    final int j = i;
                    cb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //获取当前CheckBox的状态
                            boolean state = (Boolean) mData.get(pos).get(key[j]);
                            //删除原来CheckBox的状态
                            dataSet.remove(key[j]);
                            //把CheckBox的状态设置为当前状态取反
                            dataSet.put(key[j], !state);
                        }
                    });
                } else if (v instanceof TextView) {  //设置TextView文本
                    ((TextView) v).setText(data.toString());
                } else if(v instanceof ImageView) {  //设置ImageView图像
                    ((ImageView) v).setImageResource((Integer) data);
                }
            }


            return convertView;
        }



        /**
         * 用于保存findViewById得到的View控件的引用
         */
        private class ViewHolder {

            //使用SparseArray 代替HashMap<Integer,Object> 提高性能
            //单纯从字面上来理解，SparseArray指的是稀疏数组(Sparse array)，所谓稀疏数组就是数组中大部分的内容值都未被使用（或都为零），在数组中仅有少部分的空间使用。因此造成内存空间的浪费，为了节省内存空间，并且不影响数组中原有的内容值，我们可以采用一种压缩的方式来表示稀疏数组的内容。
            SparseArray<View> mViews;

            public ViewHolder() {
                mViews = new SparseArray<View>();
            }

            /**
             * 保存view引用
             * @param resId view对应的id号
             * @param view view引用
             */
            public void put(int resId, View view) {
                mViews.put(resId, view);
            }

            /**
             * 根据view的id号获得view引用
             * @param resId view的id号
             */
            public View get(int resId) {
                return mViews.get(resId);
            }
        }
    }
}
