package com.coahr.fanoftruck.mvp.view.UMPush;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.TimeUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.umeng.message.UTrack;
import com.umeng.message.entity.UMessage;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MyNotificationService extends Service {
    private static final String TAG = UmengNotificationService.class.getName();
    public static UMessage oldMessage = null;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return super.onStartCommand(intent, flags, startId);
        }
        String message = intent.getStringExtra("UmengMsg");
        try {
            UMessage msg = new UMessage(new JSONObject(message));
            if (oldMessage != null) {
                UTrack.getInstance(getApplicationContext()).setClearPrevMessage(true);
                UTrack.getInstance(getApplicationContext()).trackMsgDismissed(oldMessage);
            }
            showNotification(msg);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void showNotification(UMessage msg) {
        int id = new Random(System.nanoTime()).nextInt();
        oldMessage = msg;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
        RemoteViews myNotificationView = new RemoteViews(BaseApplication.mContext.getPackageName(), R.layout.notification_view);
        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
        myNotificationView.setTextViewText(R.id.tv_time,TimeUtils.getStringDate_start(System.currentTimeMillis()));
        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, msg.largeIcon != null ? Imageloader.getBitMap(msg.largeIcon) : BitmapFactory.decodeResource(BaseApplication.mContext.getResources(), R.mipmap.ic_launcher));
        myNotificationView.setImageViewResource(R.id.notification_small_icon, R.drawable.ic_launcher_background);
        Notification.Builder mBuilder;
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel("Umeng", "Umeng", NotificationManager.IMPORTANCE_HIGH);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
            mBuilder = new Notification.Builder(BaseApplication.mContext, "Umeng");
            mBuilder.setCustomContentView(myNotificationView);
        } else {

            mBuilder = new Notification.Builder(this);
            mBuilder.setContent(myNotificationView);
        }
        mBuilder.setSmallIcon(R.drawable.ic_launcher_background)
                .setWhen(System.currentTimeMillis())
                .setLargeIcon(BitmapFactory.decodeResource(BaseApplication.mContext.getResources(), R.drawable.ic_launcher_background))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(msg.title)
                .setContentText(msg.text)
                .setTicker(msg.ticker)
                .setAutoCancel(true);
        if (msg.play_lights ||msg.play_vibrate||msg.play_sound){
            mBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        }
        Notification notification = mBuilder.build();
        PendingIntent clickPendingIntent = getClickPendingIntent(this, msg);
        PendingIntent dismissPendingIntent = getDismissPendingIntent(this, msg);
        notification.deleteIntent = dismissPendingIntent;
        notification.contentIntent = clickPendingIntent;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(id, notification);
    }

    public PendingIntent getClickPendingIntent(Context context, UMessage msg) {
        Intent clickIntent = new Intent();
        clickIntent.setClass(context, NotificationBroadcast.class);
        clickIntent.putExtra(NotificationBroadcast.EXTRA_KEY_MSG,
                msg.getRaw().toString());
        clickIntent.putExtra(NotificationBroadcast.EXTRA_KEY_ACTION,
                NotificationBroadcast.ACTION_CLICK);
        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context,
                (int) (System.currentTimeMillis()),
                clickIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        return clickPendingIntent;
    }

    public PendingIntent getDismissPendingIntent(Context context, UMessage msg) {
        Intent deleteIntent = new Intent();
        deleteIntent.setClass(context, NotificationBroadcast.class);
        deleteIntent.putExtra(NotificationBroadcast.EXTRA_KEY_MSG,
                msg.getRaw().toString());
        deleteIntent.putExtra(
                NotificationBroadcast.EXTRA_KEY_ACTION,
                NotificationBroadcast.ACTION_DISMISS);
        PendingIntent deletePendingIntent = PendingIntent.getBroadcast(context,
                (int) (System.currentTimeMillis() + 1),
                deleteIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        return deletePendingIntent;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
