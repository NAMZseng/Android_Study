package com.iot.lesson6_activitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    public static  final String TAG = "FirstActivity";
    private Button btn2 = null;
    private Button btn3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 关联布局文件
        setContentView(R.layout.activity_first);
        // 输出日志信息
        Log.i(TAG, "onCreate: is invoked");

        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        // 添加事件监听
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建意图对象，表明从第一个activity跳动第二个activity
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: is invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: is invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: is invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: is invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: is invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: is invoked");
    }
}