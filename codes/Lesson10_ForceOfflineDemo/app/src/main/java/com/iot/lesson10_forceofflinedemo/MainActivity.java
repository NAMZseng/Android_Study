package com.iot.lesson10_forceofflinedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    public static final String FORCE_OOFLINE = "com.iot.lesson10_forceofflinedemo.MainActivity.firstblind";
    private Button sendBtn=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn=findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(FORCE_OOFLINE);
                sendBroadcast(intent);
            }
        });
    }
}

