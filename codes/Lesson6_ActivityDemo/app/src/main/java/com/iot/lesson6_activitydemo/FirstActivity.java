package com.iot.lesson6_activitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class FirstActivity extends AppCompatActivity {

    public static  final String TAG = "FirstActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 关联布局文件
        setContentView(R.layout.activity_first);
        // 输出日志信息
        Log.i(TAG, "onCreate: is invoked");

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