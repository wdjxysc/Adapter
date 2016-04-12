package com.dijun.adapter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容简单的 布局可以丰富（只能指定TextView的内容）
 */
public class ArrayAdapter1Activity extends Activity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter1);

        listView = (ListView) findViewById(R.id.listView);

        final List<String> data = new ArrayList<>();
        data.add("幻刺");
        data.add("人马");
        data.add("幽鬼");
        data.add("火枪");


        //此处指定内容layout 和 TextView
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.array_adpter1_item, R.id.itemTextView, data);

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
                if (data.size() == 0) return;
                data.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }

}
