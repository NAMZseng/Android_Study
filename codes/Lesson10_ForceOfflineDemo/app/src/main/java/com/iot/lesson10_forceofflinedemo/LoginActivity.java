package com.iot.lesson10_forceofflinedemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText uerNameEt = null;
    private EditText passwordEt = null;
    private CheckBox rememberPass = null;
    private Button loginBtn = null;

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uerNameEt = findViewById(R.id.userNameEt);
        passwordEt = findViewById(R.id.passwordEt);
        rememberPass = findViewById(R.id.rememberPass);
        loginBtn = findViewById(R.id.loginBtn);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        boolean isRemember = sharedPreferences.getBoolean("rememberPass", false);

        if (isRemember) {
            String userNameStr = sharedPreferences.getString("useName", "");
            String passwordStr = sharedPreferences.getString("password", "");

            uerNameEt.setText(userNameStr);
            passwordEt.setText(passwordStr);
            rememberPass.setChecked(true);
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nsernameStr = uerNameEt.getText().toString().trim();
                String passwordStr = passwordEt.getText().toString().trim();

                if (nsernameStr.equals("admin") && passwordStr.equals("123456")) {
                    if (rememberPass.isChecked()) {
                        editor.putString("useName", nsernameStr);
                        editor.putString("password", passwordStr);
                        editor.putBoolean("rememberPass", rememberPass.isChecked());
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "你输入的用户名或密码不正确，请重新输入", Toast.LENGTH_LONG).show();
                    uerNameEt.setText("");
                    passwordEt.setText("");
                }
            }
        });
    }
}

