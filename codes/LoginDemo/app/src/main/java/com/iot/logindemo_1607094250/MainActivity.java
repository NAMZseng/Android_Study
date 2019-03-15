package com.iot.logindemo_1607094250;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_title =(TextView)findViewById(R.id.tv_title);
        tv_title.setText(Html.fromHtml(
                "Welcome to<br /><font color=red>APP Design Competition<\font>"));

    }
}
