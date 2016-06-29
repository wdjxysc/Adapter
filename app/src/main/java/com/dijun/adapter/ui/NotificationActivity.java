package com.dijun.adapter.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.dijun.adapter.MainActivity;
import com.dijun.adapter.R;

public class NotificationActivity extends AppCompatActivity {

    Context context;

    NotificationManager manager;

    private static final int NOTIFICATION_FLAG = 1;

    Notification notification;

    NotificationCompat.Builder mNotifyBuilder;

    /**
     * 通知序号
     */
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        context = this;

        // 在Android进行通知处理，首先需要重系统哪里获得通知管理器NotificationManager，它是一个系统Service。
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        final PendingIntent contentIntent = PendingIntent.getActivity(
                context, 0, new Intent(context, NotificationActivity.class), 0);


        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**取消通知有如下4种方式:
                 点击通知栏的清除按钮，会清除所有可清除的通知
                 设置了 setAutoCancel() 或 FLAG_AUTO_CANCEL的通知，点击该通知时会清除它
                 通过 NotificationManager 调用 cancel() 方法清除指定ID的通知
                 通过 NotificationManager 调用 cancelAll() 方法清除所有该应用之前发送的通知
                 */
                NotificationCompat.Builder builder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("标题")
                                .setContentText("内容")
                                .setDefaults(Notification.DEFAULT_ALL)
                                // 该方法在Android 4.1之前会被忽略
                                .setStyle(new NotificationCompat.BigTextStyle()
                                        .bigText("取消通知有如下4种方式:\n" +
                                                "点击通知栏的清除按钮，会清除所有可清除的通知\n" +
                                                "设置了 setAutoCancel() 或 FLAG_AUTO_CANCEL的通知，点击该通知时会清除它\n" +
                                                "通过 NotificationManager 调用 cancel() 方法清除指定ID的通知\n" +
                                                "通过 NotificationManager 调用 cancelAll() 方法清除所有该应用之前发送的通知"))
                                //添加Action Button
                                .addAction (R.mipmap.ic_launcher,"确定", null)
                                .addAction (R.mipmap.ic_launcher,"取消", null)
                                .setVisibility(Notification.VISIBILITY_PUBLIC);

                builder.setContentText("new content text")
                        .setNumber(++num);

                notification = builder.build();

                manager.notify(NOTIFICATION_FLAG, notification);
            }
        });



        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(context, MainActivity.class);
                PendingIntent resultPendingIntent = PendingIntent.getActivity(
                        context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                /**
                 * 以下两种情况会显示浮动通知:

                 setFullScreenIntent()，如上述示例。
                 通知拥有高优先级且使用了铃声和振动
                 */
                NotificationCompat.Builder mNotifyBuilder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                                .setContentTitle("New Message")
                                .setContentText("以下两种情况会显示浮动通知:\n" +
                                        "setFullScreenIntent()，如上述示例。\n" +
                                        "通知拥有高优先级且使用了铃声和振动")
                                .setSmallIcon(R.mipmap.ic_launcher)
                                //只在高版本(5.0以上)生效 但具体有些手机厂商不支持
                                .setFullScreenIntent(resultPendingIntent, true);

                notification = mNotifyBuilder.build();
                notification.defaults |= Notification.DEFAULT_VIBRATE;
                notification.defaults |= Notification.DEFAULT_SOUND;

                manager.notify(2223, notification);
            }
        });

    }
}
