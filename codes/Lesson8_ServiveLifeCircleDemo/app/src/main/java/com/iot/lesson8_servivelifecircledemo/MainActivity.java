package com.iot.lesson8_servivelifecircledemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button start, stop, bind, unbind, getData;
    private final static String TAG = "MyService";
    private MyService.MyBinder myBinder = null;

    // 创建服务连接监听类，用于绑定服务时使用
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "MainActivity onServiceDisconnected invoked!");
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "MainActivity onServiceConnected invoked!");
            myBinder = (MyService.MyBinder) service;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        bind = (Button) findViewById(R.id.bind);
        unbind = (Button) findViewById(R.id.unbind);
        getData = (Button) findViewById(R.id.getData);

        final Intent intent = new Intent(MainActivity.this, MyService.class);
        // 若没有在manifest文件中设置过滤器，并指定name，则可以不用setAction
        intent.setAction("com.iot.lesson8_servivelifecircledemo.MyService");

        start.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // ContextWrapper中的方法，Activity已继承该类,故可以直接使用父类方法
                startService(intent);
            }
        });

        stop.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                stopService(intent);
            }
        });

        bind.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // conn用于监听访问者(即当前Activity)和service之前的联系状态
                // 如绑定状态是否正常连接等
                bindService(intent, conn, Service.BIND_AUTO_CREATE);
            }
        });

        unbind.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                unbindService(conn);
            }
        });

        getData.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Count=" + myBinder.getCount(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
