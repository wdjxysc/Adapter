package com.dijun.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容丰富的  需要继承ArrayAdapter 重写方法
 */
public class ArrayAdapter2Activity extends Activity {
    ListView listView;

    List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter2);

        listView = (ListView)findViewById(R.id.listView);

        users.add(new User(25,"王大锤"));
        users.add(new User(24,"黄继光"));
        users.add(new User(23,"Apple"));
        users.add(new User(22,"Android"));

        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(this,R.layout.array_adpter2_item,users);

        listView.setAdapter(customArrayAdapter);

    }


    /**
     * 自定义ArrayAdapter  重写getView方法
     */
    private class CustomArrayAdapter extends ArrayAdapter<User>{

        int resourceId;
        /**
         * 构造方法 可以在这里指定数据
         * @param context
         * @param resource 资源ID
         * @param objects 数据
         */
        public CustomArrayAdapter(Context context, int resource, List<User> objects) {
            super(context, resource, objects);
            this.resourceId = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            User user = users.get(position);

            LinearLayout userListItem = new LinearLayout(getContext());
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi.inflate(resourceId, userListItem, true);

            TextView ageTextView = (TextView)userListItem.findViewById(R.id.itemTextView1);
            TextView nameTextView = (TextView)userListItem.findViewById(R.id.itemTextView2);

            ageTextView.setText(user.age + "");

            nameTextView.setText(user.name);
            return userListItem;
        }
    }

    private class User{
        private int age;
        private String name;
        public User(int age,String name){
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
