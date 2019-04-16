package com.iot.lesson9_musicplayerdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageButton stopBtn = null;
    private ImageButton playBtn = null;
    private TextView titleTv = null;
    private TextView authorTv = null;
    private MyListener myListener = null;
    private ActivityReceiver activityReceiver = null;
    //控制播放、暂停
    public static final String CONTROL = "com.iot.lesson9_musicplayerdemo.control";
    //更新界面显示
    public static final String UPDATE = "com.iot.lesson9_musicplayerdemo.update";

    // 定义音乐的播放状态，0x11代表没有播放；0x12代表正在播放；0x13代表暂停
    int status = 0x11;
    String[] titleStrs = new String[]{"生活不止眼前的苟且", "平凡之路"};
    String[] authorStrs = new String[]{"许巍", "朴树"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 控件的初始化可以封装到一个init方法体中
        stopBtn = findViewById(R.id.stopBtn);
        playBtn = findViewById(R.id.playBtn);
        titleTv = findViewById(R.id.titleTv);
        authorTv = findViewById(R.id.authorTv);

        myListener = new MyListener();
        activityReceiver = new ActivityReceiver();

        stopBtn.setOnClickListener(myListener);
        playBtn.setOnClickListener(myListener);

        // 设置过滤条件，只能监听UPDATE类广播
        IntentFilter filter = new IntentFilter(UPDATE);
        registerReceiver(activityReceiver, filter);

        Intent intent = new Intent(this, MusicService.class);
        // 启动后台Service
        startService(intent);
    }

    // 自定义的BroadcastReceiver，负责监听从Service传回来的广播
    private class ActivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            int update = intent.getIntExtra("update", -1);
            int current = intent.getIntExtra("current", -1);
            if (current >= 0) {
                titleTv.setText(titleStrs[current]);
                authorTv.setText(authorStrs[current]);
            }
            switch (update) {
                case 0x11:
                    // 设置状态图标
                    playBtn.setImageResource(R.drawable.play);
                    // 设置当前状态为没有播放
                    status = 0x11;
                    break;

                // 控制系统进入播放状态
                case 0x12:
                    playBtn.setImageResource(R.drawable.pause);
                    status = 0x12;
                    break;

                // 控制系统进入暂停状态
                case 0x13:
                    playBtn.setImageResource(R.drawable.play);
                    // 设置当前状态为暂停
                    status = 0x13;
                    break;
                default:
                    break;
            }
        }
    }

    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CONTROL);
            switch (v.getId()) {
                case R.id.playBtn:
                    // 1: 播放/暂停
                    intent.putExtra("control", 1);
                    break;
                case R.id.stopBtn:
                    // 2：停止播放
                    intent.putExtra("control", 2);
                    break;
                default:
                    break;
            }

            // 发送广播 ，将被Service组件中的serviceReceiver接收到
            sendBroadcast(intent);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(activityReceiver);
    }
}
