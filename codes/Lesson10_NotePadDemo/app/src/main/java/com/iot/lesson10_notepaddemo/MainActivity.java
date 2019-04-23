package com.iot.lesson10_notepaddemo;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText themeEt = null;
    private EditText contentEt = null;
    private EditText dateEt = null;

    private Button chooseDateBtn = null;
    private Button addBtn = null;
    private Button queryBtn = null;

    private LinearLayout titleLL = null;
    private ListView resultLv = null;

    private MyDatabaseHelper mydbHelper = null;
    private MyListener  myListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        themeEt = findViewById(R.id.themeET);
        contentEt = findViewById(R.id.contentET);
        dateEt = findViewById(R.id.dateET);
        chooseDateBtn = findViewById(R.id.chooseDateBtn);
        addBtn = findViewById(R.id.addBtn);
        queryBtn = findViewById(R.id.queryBtn);
        titleLL = findViewById(R.id.titleLL);
        resultLv = findViewById(R.id.resultLV);

        chooseDateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();               //获取当前日期
                new DatePickerDialog(MainActivity.this,            //日期选择器对话框
                        new DatePickerDialog.OnDateSetListener() { //日期改变监听器
                            public void onDateSet(DatePicker view,
                                                  int year, int month,
                                                  int day) {
                                dateEt.setText(year + "-" + (month + 1)
                                        + "-" + day);
                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH)
                        , c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

     myListener = new MyListener();
     addBtn.setOnClickListener(myListener);
     queryBtn.setOnClickListener(myListener);

    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //创建数据库辅助类
            mydbHelper = new MyDatabaseHelper(MainActivity.this, "notePad.db", null, 1);
            //获取SQLite数据库(Read 对象中包含Write对象)
            SQLiteDatabase db = mydbHelper.getReadableDatabase();

            String themeStr = themeEt.getText().toString();
            String contentStr = contentEt.getText().toString();
            String dateStr = dateEt.getText().toString();

            switch (v.getId()) {
                case R.id.addBtn:
                    // 当添加内容时，将标题栏和list列表都设为空，即不可见
                    titleLL.setVisibility(View.INVISIBLE);
                    addNotePad(db, themeStr, contentStr, dateStr);
                    Toast.makeText(MainActivity.this, "添加备忘录成功！", 1000).show();
                    // 设置resultListView 列表中为空
                    resultLv.setAdapter(null);
                    break;
                case R.id.queryBtn:
                    // 当查询内容时，将标题栏及listl列表设置为可见
                    titleLL.setVisibility(View.VISIBLE);
                    Cursor cursor = queryNotePad(db, themeStr, contentStr, dateStr);

                    SimpleCursorAdapter resultAdapter = new SimpleCursorAdapter(
                            MainActivity.this, R.id.resultLV, cursor,
                            new String[]{"_id", "theme", "content", "date"},
                            new int[]{R.id.notepad_num, R.id.notepad_theme,
                                    R.id.notepad_content, R.id.notepad_date});

                    resultLv.setAdapter(resultAdapter);
                    break;
                default:
                    break;
            }
        }
    }

    public void addNotePad(SQLiteDatabase db, String theme , String content, String date) {
        db.execSQL("insert into notePad_tb values(null,?,?,?)", new String[]{
                theme, content, date});
        this.themeEt.setText("");
        this.contentEt.setText("");
        this.dateEt.setText("");
    }

    public Cursor queryNotePad(SQLiteDatabase db, String theme , String content, String date) {
        Cursor cursor = db.rawQuery(
                "select * from notePad_tb where theme like ? and content like ? and date like ?",
                new String[]{"%" + theme + "%", "%" + content + "%",
                        "%" + date + "%"});
        return cursor;
    }

    @Override
    protected void onDestroy() {
        if (mydbHelper != null) {
            mydbHelper.close();
        }
    }
}

