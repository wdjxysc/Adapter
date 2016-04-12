package com.dijun.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * SimpleAdapter使用：使用一个List<Map<String,Object>>作为数据源，一个map中的key数组，以及一个对应界面R.id数组 来使数据对应显示
 */
public class SimpleAdapterActivity extends Activity {

    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);

        listView = (ListView) findViewById(R.id.listView);

        List<String> data = new ArrayList<>();
        data.add("测试1");
        data.add("测试2");
        data.add("测试3");
        data.add("测试4");

        Map<String, Object> map = new HashMap<>();
        map.put("name", "齐天大圣");
        map.put("image", R.mipmap.ic_launcher);

        Map<String, Object> map1 = new HashMap<>();
        map.put("name", "牛魔王");
        map.put("image", R.mipmap.ic_launcher);

        Map<String, Object> map2 = new HashMap<>();
        map.put("name", "张怡宁大魔王");
        map.put("image", R.mipmap.ic_launcher);

        List<Map<String, Object>> maps = new ArrayList<>();
        maps.add(map);
        maps.add(map1);
        maps.add(map2);
        SimpleAdapter arrayAdapter = new SimpleAdapter(this,
                maps,
                R.layout.simple_adapter_item,
                new String[]{"name", "image"},
                new int[]{R.id.itemTextView, R.id.imageView});

        listView.setAdapter(arrayAdapter);
    }
}
