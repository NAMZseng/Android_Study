package com.iot.android.lesson10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 添加图书界面
 * @author Nanrong Zeng
 * @version 1.0
 */
public class AddBookActivity extends AppCompatActivity {

    private EditText nameEt = null;
    private EditText authorEt = null;
    private EditText pressEt = null;
    private EditText priceEt = null;
    private Button confirmAddBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        init();

        confirmAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.confirmAddBtn) {
                    Book book = new Book();
                    book.setName(nameEt.getText().toString());
                    book.setAuthor(authorEt.getText().toString());
                    book.setPress(pressEt.getText().toString());
                    book.setPrice(Float.valueOf(priceEt.getText().toString()));
                    book.save();

                    Toast.makeText(AddBookActivity.this, "图书添加成功！", 1000).show();

                    // 清空之前填些的数据
                    nameEt.setText("");
                    authorEt.setText("");
                    pressEt.setText("");
                    priceEt.setText("");
                }
            }
        });

    }

    private void init() {
        nameEt = findViewById(R.id.nameAddEt);
        authorEt = findViewById(R.id.authorAddEt);
        pressEt = findViewById(R.id.pressAddEt);
        priceEt = findViewById(R.id.priceAddEt);
        confirmAddBtn = findViewById(R.id.confirmAddBtn);
    }
}
