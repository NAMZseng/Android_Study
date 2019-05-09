package com.iot.android.lesson12_webviewdemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private WebView webView = null;
    private Button send_request = null;
    private TextView respodseTv = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        // 支持js脚本
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置客户端
        // 当打开新的浏览界面时，该界面仍在本app中显示
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.nuc.edu.cn");

        send_request = findViewById(R.id.sent_request);
        respodseTv = findViewById(R.id.responseTv);

        send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithHttpURLConnection();
            }
        });
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL("http://www.nuc.edu.cn");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    // 设置链接超时
                    connection.setConnectTimeout(5000);
                    // 设置读取超时
                    connection.setReadTimeout(5000);

                    // 字节流
                    InputStream inputStream = connection.getInputStream();
                    // TODO 字节流 字符流？
                    // 字节流转换成字符流
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    // TODO builder buffer ?
                    StringBuilder responseStr = new StringBuilder();
                    String lineStr = null;

                    while ((lineStr = bufferedReader.readLine())!= null) {
                        responseStr.append(lineStr);
                    }

                    showResponse(responseStr.toString());

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String toString) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                respodseTv.setText(toString);
            }
        });
    }
}
