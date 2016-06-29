package com.dijun.adapter.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dijun.adapter.R;

public class ToolbarTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_test);


        Toolbar toolbar = (Toolbar) findViewById(R.id.ac_toolbar_for_action_bar_toolbar);
        toolbar.setTitle("大标题");
        toolbar.setSubtitle("小标题");
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToolbarTestActivity.this, "Navigation", Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String result = "";
        switch (item.getItemId()) {
            case R.id.ac_toolbar_copy:
                result = "Copy";
                break;
            case R.id.ac_toolbar_cut:
                result = "Cut";
                break;
            case R.id.ac_toolbar_del:
                result = "Del";
                break;
            case R.id.ac_toolbar_edit:
                result = "Edit";
                break;
            case R.id.ac_toolbar_email:
                result = "Email";
                break;
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
