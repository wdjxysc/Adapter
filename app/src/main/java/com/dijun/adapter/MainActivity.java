package com.dijun.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.arrayAdapterBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ArrayAdapterActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.arrayAdapter1Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArrayAdapter1Activity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.arrayAdapter2Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArrayAdapter2Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.simpleAdapterBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SimpleAdapterActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.baseAdapterBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,BaseAdapterActivity.class);
                startActivity(intent);
            }
        });
    }
}
