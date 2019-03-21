package com.iot.lesson5_asynctaskdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button myBtn = null;
    private TextView myText = null;
    private ProgressBar myBar = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBtn = findViewById(R.id.myBtn);
        myText = findViewById(R.id.myText);
        myBar =  findViewById(R.id.myBar);

        myBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                DownTask downTask = new DownTask(myText, myBar);
                // 启动任务
                // 100 表示每100ms更新一次下载状态信息
                // 该参数将传递给doInBackground()方法
                downTask.execute(100);
            }
        });
    }
}
