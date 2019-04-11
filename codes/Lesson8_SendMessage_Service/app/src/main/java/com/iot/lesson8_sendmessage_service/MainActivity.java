package com.iot.lesson8_sendmessage_service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
* 常用的系统级服务：
* 1.SmsManager
* 2.TelphoneManager:获取网络状态，SIM卡信息
* 3.AudioManager:调节音量大小，静音
* 4.Vibrator:震动器
* 5.AlarmManager:闹钟管理器
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
