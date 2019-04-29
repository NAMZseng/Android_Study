package com.iot.android.lesson10;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;

/**
 * 主界面程序, 设有增删查改的跳转按钮
 *
 * @author Nanrong Zeng
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private Button addBtn = null;
    private Button delBtn = null;
    private Button updateBtn = null;
    private Button searchBtn = null;
    private MyListener myListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建数据库
        LitePal.getDatabase();
        // 初始化变量
        init();

        //添加各个操作的监听器
        addBtn.setOnClickListener(myListener);
        delBtn.setOnClickListener(myListener);
        updateBtn.setOnClickListener(myListener);
        searchBtn.setOnClickListener(myListener);
    }


    private void init() {
        addBtn = findViewById(R.id.addBtn);
        delBtn = findViewById(R.id.delBtn);
        updateBtn = findViewById(R.id.updateBtn);
        searchBtn = findViewById(R.id.searchBtn);
        myListener = new MyListener();
    }

    private class MyListener implements View.OnClickListener {
        private  Intent intent = null;

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.addBtn:
                    intent = new Intent(MainActivity.this, AddBookActivity.class);
                    break;
                case R.id.delBtn:
                    intent = new Intent(MainActivity.this, DelBookActivity.class);
                    break;
                case R.id.updateBtn:
                    intent = new Intent(MainActivity.this, UpdateBookActivity.class);
                    break;
                case R.id.searchBtn:
                    intent = new Intent(MainActivity.this, SearchBookActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }

}
