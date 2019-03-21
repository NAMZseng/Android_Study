package com.iot.lesson5_handlerdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //消息的标记
    public static final int UPDATE_TEXT = 0x12;
    private TextView myText = null;
    private Handler myHandler = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myText = (TextView) findViewById(R.id.myText);
        myText.setText("生成的随机数为：" + Math.random());

        myHandler = new Handler() {
            //接收MessageQueue队列发送的待处理的消息
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //如果该消息是本程序所发送的,则进行相关操作，如更新UI
                if (msg.what == UPDATE_TEXT) {
                    myText.setText("生成的随机数为：\n" + Math.random());
                }
            }
        };

        // 创建子线程，周期性发送信号
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(500);
                        // 创建message对象，用于线程间消息的传递
                        Message msg = new Message();
                        msg.what = UPDATE_TEXT;
                        // 使用handler发送消息
                        // 该消息将被添加到MessageQueue中
                        myHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}