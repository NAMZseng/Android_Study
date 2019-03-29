package com.iot.lesson7_sendmessagedemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText num = null;
    private  EditText mess = null;
    private Button btn = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        num = (EditText) findViewById(R.id.num);
        mess = (EditText) findViewById(R.id.Mess);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String mobile = num.getText().toString();
                String content = mess.getText().toString();
                Intent intent = new Intent();

                intent.setData(Uri.parse("smsto:" + mobile));
                intent.putExtra("sms_body", content);

                startActivity(intent);
            }
        });
    }
}