package com.iot.android.lesson10;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询图书
 *
 * @author Nanrong Zeng
 * @version 1.0
 */
public class SearchBookActivity extends AppCompatActivity {

    private EditText nameSearchEt = null;
    private Button confirmSearchBtn = null;
    private Button searchAllBtn = null;
    private LinearLayout titleLL = null;
    private ListView resultLv = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        init();

        confirmSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.confirmSearchBtn) {
                    String searchName = nameSearchEt.getText().toString();

                    titleLL.setVisibility(View.VISIBLE);

                    List<Book> books = LitePal.where("name = ?", searchName).find(Book.class);
                    resultLv.setAdapter(new BookLvAdapter(SearchBookActivity.this, books));

                }
            }
        });

        searchAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.searchAllBtn) {
                    List<Book> books = LitePal.findAll(Book.class);
                    titleLL.setVisibility(View.VISIBLE);
                    resultLv.setAdapter(new BookLvAdapter(SearchBookActivity.this, books));
                }
            }
        });
    }

    private void init() {
        nameSearchEt = findViewById(R.id.nameSearchEt);
        confirmSearchBtn = findViewById(R.id.confirmSearchBtn);
        searchAllBtn = findViewById(R.id.searchAllBtn);
        titleLL = findViewById(R.id.titleLL);
        resultLv = findViewById(R.id.resultLV);
    }
}
