package com.iot.android.lesson10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

/**
 * 更新图书信息界面
 * @author Nanrong Zeng
 * @version 1.0
 */
public class UpdateBookActivity extends AppCompatActivity {
    private EditText toUpdateNameEt = null;
    private EditText nameEt = null;
    private EditText authorEt = null;
    private EditText pressEt = null;
    private EditText priceEt = null;
    private Button confirmUpdateBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        init();

        confirmUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.confirmUpdateBtn) {
                    String toUpdateName = toUpdateNameEt.getText().toString();
                    String updateName = nameEt.getText().toString();
                    String updateAuthor = authorEt.getText().toString();
                    String updatePress = pressEt.getText().toString();
                    String updatePrice = priceEt.getText().toString();

                    Book book = LitePal.where("name=?", toUpdateName).find(Book.class).get(0);

                    if (!"".equals(updateName)) {
                        book.setName(updateName);
                    }
                    if (!"".equals(updateAuthor)) {
                        book.setAuthor(updateAuthor);
                    }
                    if (!"".equals(updatePress)) {
                        book.setPress(updatePress);
                    }
                    if (!"".equals(updatePrice)) {
                        book.setPrice(Float.valueOf(updatePrice));
                    }
                    book.update(book.getId());

                    Toast.makeText(UpdateBookActivity.this, "图书更新成功！", 1000).show();

                    toUpdateNameEt.setText("");
                    nameEt.setText("");
                    authorEt.setText("");
                    pressEt.setText("");
                    priceEt.setText("");
                }
            }
        });
    }

    private void init() {
        toUpdateNameEt = findViewById(R.id.toUpdateNameEt);
        nameEt = findViewById(R.id.nameUpdateEt);
        authorEt = findViewById(R.id.authorUpdateEt);
        pressEt = findViewById(R.id.pressUpdateEt);
        priceEt = findViewById(R.id.priceUpdateEt);
        confirmUpdateBtn = findViewById(R.id.confirmUpdateBtn);
    }
}
