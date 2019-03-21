package com.iot.lesson5_asynctaskdemo;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownTask extends AsyncTask<Integer, Integer, String> {
    private TextView tv;
    private ProgressBar pb;

    public DownTask(TextView tv, ProgressBar pb) {
        this.tv = tv;
        this.pb = pb;
    }

    public DownTask() {
        //提供一个无参的构造方法
    }

    // 后台任务执行前的初始化操作
    protected void onPreExecute() {
        tv.setVisibility(View.VISIBLE);
        pb.setVisibility(View.VISIBLE);
        super.onPreExecute();
    }

    // 处理任务
    // 该方法中的所有任务都会在子线程中运行
    protected String doInBackground(Integer... param) {
        for (int i = 0; i <= 100; i++) {
            // 反馈任务执行进度
            publishProgress(i);
            try {
                Thread.sleep(param[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Finished";
    }

    // 接收publishProgress()的反馈，根据参数的数值进行UI更新
    protected void onProgressUpdate(Integer... param) {
        //更改界面组件的属性
        tv.setText("当前完成任务的" + param[0] + "%");
        pb.setProgress(param[0]);
        super.onProgressUpdate(param);
    }

    // 接收doInBackground()的返回值
    // 执行任务结束的收尾工作
    protected void onPostExecute(String result) {
        //执行结束后，相关界面组件属性的设置
        tv.setText(result);
        tv.setTextColor(Color.RED);
        tv.setTextSize(20);
        pb.setVisibility(View.INVISIBLE);
        super.onPostExecute(result);
    }
}
