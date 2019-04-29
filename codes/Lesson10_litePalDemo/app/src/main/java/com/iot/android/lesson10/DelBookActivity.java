package com.iot.android.lesson10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

/**
 * 删除图书界面
 * @author Nanrong Zeng
 * @version 1.0
 */
public class DelBookActivity extends AppCompatActivity {
    private EditText nameDelEt = null;
    private Button confirmDelBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_book);

        init();

        confirmDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.confirmDelBtn) {
                    String delname = nameDelEt.getText().toString();
                    LitePal.deleteAll(Book.class, "name=?", delname);

                    Toast.makeText(DelBookActivity.this, "图书删除成功！", 1000).show();

                    nameDelEt.setText("");
                }
            }
        });

    }

    private void init() {
        nameDelEt = findViewById(R.id.nameDelEt);
        confirmDelBtn = findViewById(R.id.confirmDelBtn);
    }
}
