package com.iot.texteditor_1607094250;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button red = null;
    private Button green = null;
    private Button blue = null;
    private Button bold = null;
    private Button italic = null;
    private Button moren = null;
    private Button bigger = null;
    private Button smaller = null;
    private TextView testText = null;
    private EditText content = null;
    // 控制字体粗斜体
    // 0: normal | 1: bold | 2: italic | 3: bold_italic
    private int tType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testText = (TextView) findViewById(R.id.testText);
        testText.setTypeface(Typeface.DEFAULT);

        red = (Button) findViewById(R.id.red);
        green = (Button) findViewById(R.id.green);
        blue = (Button) findViewById(R.id.blue);

        // 将颜色按钮与内部设置的鼠标点击监听器对象关联
        ColorListener colorListener = new ColorListener();
        red.setOnClickListener(colorListener);
        green.setOnClickListener(colorListener);
        blue.setOnClickListener(colorListener);

        bold = (Button) findViewById(R.id.bold);
        italic = (Button) findViewById(R.id.italic);
        moren = (Button) findViewById(R.id.moren);

        // 将字体样式按钮与当前监听器类对象关联
        italic.setOnClickListener(this);
        bold.setOnClickListener(this);
        moren.setOnClickListener(this);

        bigger = (Button) findViewById(R.id.bigger);
        smaller = (Button) findViewById(R.id.smaller);

        // 将字体大小按钮与外部设置的鼠标点击监听器对象关联
        SizeListener sizeListener = new SizeListener(testText);
        bigger.setOnClickListener(sizeListener);
        smaller.setOnClickListener(sizeListener);

        content = (EditText) findViewById(R.id.content);

        // 内容组件关联匿名内部类对象
        content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                testText.setText(content.getText().toString());
                // 为true时，点击回车后不会换行
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.italic:
                // 粗体或粗斜体，点击斜体后变粗斜体
                if (tType == 1 || tType == 3) {
                    testText.setTypeface(Typeface.MONOSPACE, Typeface.BOLD_ITALIC);
                    tType = 3;
                } else {
                    testText.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
                    tType = 2;
                }
                break;
            case R.id.bold:
                // 斜体体或粗斜体，点击加粗后变粗斜体
                if (tType == 2 || tType == 3) {
                    testText.setTypeface(Typeface.MONOSPACE, Typeface.BOLD_ITALIC);
                    tType = 3;
                } else {
                    testText.setTypeface(Typeface.DEFAULT_BOLD, Typeface.BOLD);
                    tType = 1;
                }
                break;
            case R.id.moren:
                testText.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
                tType = 0;
                break;
            default:
                break;
        }
    }

    // 定义内部监听器类
    private class ColorListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.red:
                    testText.setTextColor(Color.RED);
                    break;
                case R.id.green:
                    testText.setTextColor(Color.GREEN);
                    break;
                case R.id.blue:
                    testText.setTextColor(Color.BLUE);
                default:
                    break;
            }


        }
    }
}
