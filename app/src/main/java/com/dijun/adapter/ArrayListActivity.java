package com.dijun.adapter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ArrayListActivity extends Activity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);


        listView = (ListView) findViewById(R.id.listView);

        final List<String> data = new ArrayList<>();
        data.add("测试1");
        data.add("测试2");
        data.add("测试3");
        data.add("测试4");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,data);

        listView.setAdapter(arrayAdapter);



        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.add("嘻嘻");
                arrayAdapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.minusBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.size() == 0) return;
                data.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}
