package com.iot.lesson8_sendmessage_service;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

/*
* 常用的系统级服务：
* 1.SmsManager:短信管理器
* 2.TelphoneManager:获取网络状态，SIM卡信息
* 3.AudioManager:调节音量大小，静音
* 4.Vibrator:震动器
* 5.AlarmManager:闹钟管理器
* */
public class MainActivity extends AppCompatActivity {

    private EditText phone = null;
    private EditText message  = null;
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = findViewById(R.id.phone);
        message = findViewById(R.id.message);
        btn = findViewById(R.id.btn);

        // 申请动态获取权限
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tell = phone.getText().toString().trim();
                String msg = message.getText().toString().trim();
                // 获取短信管理器
                SmsManager smsManager = SmsManager.getDefault();

                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this , 0, new Intent(), 0);
                // 短信包括160 Eng字符， 70中文字符(包括中文符号)
                ArrayList<String> msgList = smsManager.divideMessage(msg);
                // 遍历msgList，发送短信各字段
                for (String s : msgList) {
                    smsManager.sendTextMessage(tell, null, msg, pendingIntent, null);
                }
                Toast.makeText(MainActivity.this, "短信发送完毕！", Toast.LENGTH_LONG).show();
            }
        });
    }
}
