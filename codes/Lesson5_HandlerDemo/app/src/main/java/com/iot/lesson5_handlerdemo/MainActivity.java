package com.iot.lesson5_handlerdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView myText = null;
    private Handler myHandler = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myText = (TextView) findViewById(R.id.myText);
        myText.setText("生成的随机数为：" + Math.random());
        myHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x12) {//如果该消息是本程序所发送的
                    myText.setText("生成的随机数为：\n" + Math.random());
                }
            }
        };
        // 创建子线程，周期性发送信号
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        Message msg = new Message();
                        msg.what = 0x12;//消息的标记
                        myHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}