package com.iot.lesson9_musicplayerdemo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class MusicService extends Service {
    // 当前的状态,0x11代表没有播放 ；0x12代表正在播放；0x13代表暂停
    private int status = 0x11;
    private MediaPlayer mediaPlayer = null;
    private AssetManager assetManager = null;
    private ServiceReceiver serviceReceiver = null;

    private String[] musics = new String[]{"life.mp3", "road.mp3"};
    // 记录当前正在播放音乐的索引
    private int current = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        assetManager = getAssets();
        serviceReceiver = new ServiceReceiver();
        // 设置过滤条件，只能接收CONTROL类广播
        IntentFilter intentFilter = new IntentFilter(MainActivity.CONTROL);
        // 动态注册
        registerReceiver(serviceReceiver, intentFilter);

        // 为MediaPlayer播放完成事件绑定监听器
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                current++;
                if (current >= 2) {
                    // 循环播放
                    current = 0;
                }
                // 发送广播通知MainActivity更改文本框
                Intent sendIntent = new Intent(MainActivity.UPDATE);
                sendIntent.putExtra("current", current);
                // 发送广播 ，将被MainActivity组件中的BroadcastReceiver接收到
                sendBroadcast(sendIntent);
                // 准备、并播放下一首音乐
                prepareAndPlay(musics[current]);
            }
        });
    }

    private void prepareAndPlay(String music) {
        try {
            // 打开指定音乐文件
            AssetFileDescriptor afd = assetManager.openFd(music);
            mediaPlayer.reset();
            // 使用MediaPlayer加载指定的声音文件。
            mediaPlayer.setDataSource(afd.getFileDescriptor()
                    , afd.getStartOffset()
                    , afd.getLength());
            // 准备声音
            mediaPlayer.prepare();
            // 播放
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ServiceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 当没有取到返回值时，默认将-1返回
            int control = intent.getIntExtra("control", -1);
            switch (control) {
                // 播放或暂停
                case 1:
                    // 原来处于没有播放状态
                    if (status == 0x11) {
                        // 准备、并播放音乐
                        prepareAndPlay(musics[current]);
                        status = 0x12;
                    }
                    // 原来处于播放状态
                    else if (status == 0x12) {
                        mediaPlayer.pause();// 暂停
                        status = 0x13;// 改变为暂停状态
                    }
                    // 原来处于暂停状态
                    else if (status == 0x13) {
                        mediaPlayer.start();// 播放
                        status = 0x12;// 改变状态
                    }
                    break;
                // 停止声音
                case 2:
                    // 如果原来正在播放或暂停
                    if (status == 0x12 || status == 0x13) {
                        mediaPlayer.stop();// 停止播放
                        status = 0x11;
                    }
                    break;
                default:
                    break;
            }
            // 发送广播通知MainActivity更改图标、文本框
            Intent sendIntent = new Intent(MainActivity.UPDATE);
            sendIntent.putExtra("update", status);
            sendIntent.putExtra("current", current);
            // 发送广播 ，将被MainActivity组件中的BroadcastReceiver接收到
            sendBroadcast(sendIntent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(serviceReceiver);
    }
}
