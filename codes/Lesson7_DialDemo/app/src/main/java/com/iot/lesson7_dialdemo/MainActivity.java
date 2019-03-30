package com.iot.lesson7_dialdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editText = null;
    private Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.num);
        button = (Button) findViewById(R.id.mybtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将字符串转换成Uri对象(parse:分析）
                Uri uri = Uri.parse("tel:" + editText.getText().toString());
                //第一个参数表示操作的动作，系统根据这个会调用拨号功能
                //第二个参数用于指定操作的数据，即向拨打哪个号码
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);

                startActivity(intent);
            }
        });
    }
}
