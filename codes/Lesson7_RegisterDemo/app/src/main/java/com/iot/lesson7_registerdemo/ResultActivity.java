package com.iot.lesson7_registerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultName = findViewById(R.id.resultName);
        TextView resultPsd = findViewById(R.id.resultPsd);
        TextView resultGender = findViewById(R.id.resultGender);
        TextView resultCity = findViewById(R.id.resultCity);

        // 获取MainActivity发送过来的Intent
        Intent intent = getIntent();

        resultName.setText(intent.getStringExtra("name"));
        resultPsd.setText(intent.getStringExtra("psd"));
        resultGender.setText(intent.getStringExtra("gender"));
        resultCity.setText(intent.getStringExtra("city"));
    }
}
