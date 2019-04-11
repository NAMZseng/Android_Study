package com.iot.lesson8_servivelifecircledemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private int count = 0;
    private boolean quit = false;

    public class MyBinder extends Binder {
        public MyBinder() {
            Log.i(TAG, "MyBinder Constructure invoked!");
        }

        public int getCount() {
            return count;
        }
    }

    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        Log.i(TAG, "MyService onCreate invoked!");
        super.onCreate();
        new Thread() {
            public void run() {
                while (!quit) {
                    try {
                        Thread.sleep(100);
                        count++;
                        System.out.println();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    // 每次开启服务均会调用(onCreate方法仅会在第一次启动时被调用)
    public int onStartCommand(Intent intent, int flags, int startId) {

        // 创建服务相关通知
        Intent intent_1 = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent_1, 0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("this is content title")
                .setContentText("this is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent);
        Notification notification = builder.build();
        // 将服务相关的通知放入前台
        // 安卓8.0后不支持
        startForeground(0, notification);

        Log.i(TAG, "MyService onStartCommand invoked!");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "MyService onDestroy invoked!");
        super.onDestroy();
        quit = true;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "MyService onBind invoked!");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "MyService onUnbind invoked!");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "MyService onRebind invoked!");
        super.onRebind(intent);
    }
}