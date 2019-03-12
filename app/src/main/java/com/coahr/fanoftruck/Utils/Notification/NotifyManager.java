package com.coahr.fanoftruck.Utils.Notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.text.TextUtils;

import com.coahr.fanoftruck.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Leehor
 * on 2018/12/19
 * on 10:11
 * 通知管理器
 */
public class NotifyManager {
    private Context context;
    private NotificationManager notificationManager;
    private Random random;

    public NotifyManager(@NonNull Context context) {
        this.context = context.getApplicationContext();
        init();
    }

    private void init() {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        random = new Random();
    }

    /**
     * 创建渠道
     *
     * @param channelEntity 渠道消息
     */
    public void createNotificationChannel(ChannelEntity channelEntity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(channelEntity, null);
        }
    }

    /**
     * 创建渠道组和一个渠道
     *
     * @param groupId
     * @param groupName
     * @param channel
     */
    public void createNotificationGroupWithChannel(@NonNull String groupId, @Nullable String groupName, @NonNull ChannelEntity channel) {
        ArrayList<ChannelEntity> channelList = new ArrayList<>();
        channelList.add(channel);
        createNotificationGroupWithChannel(groupId, groupName, channelList);
    }

    /**
     * 创建渠道组和一组渠道
     *
     * @param groupId
     * @param groupName
     * @param channelList
     */
    public void createNotificationGroupWithChannel(@NonNull String groupId, @Nullable String groupName, @NonNull ArrayList<ChannelEntity> channelList) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if (!TextUtils.isEmpty(groupId)) {
                createNotificationGroup(groupId, groupName);
            }

            for (ChannelEntity channel : channelList) {
                createNotificationChannel(channel, groupId);
            }
        }
    }

    /**
     * 创建渠道，并创建组
     *
     * @param channelEntity
     * @param groupId
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(@NonNull ChannelEntity channelEntity, @Nullable String groupId) {
        NotificationChannel channel = new NotificationChannel(channelEntity.getChannelId(), channelEntity.getChannelName(), channelEntity.getImportance());
        channel.setShowBadge(channelEntity.isShowBadge());
        if (!TextUtils.isEmpty(channelEntity.getDescription()))
            channel.setDescription(channelEntity.getDescription());
        if (!TextUtils.isEmpty(groupId))
            channel.setGroup(groupId);
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * 创建渠道组
     *
     * @param groupId
     * @param groupName
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationGroup(@NonNull String groupId, @Nullable String groupName) {
        NotificationChannelGroup group = new NotificationChannelGroup(groupId, groupName);
        notificationManager.createNotificationChannelGroup(group);
    }

    /**
     * 删除渠道
     *
     * @param channelId
     */
    public void deleteNotificationChannel(@NonNull String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.deleteNotificationChannel(channelId);
        }
    }

    /**
     * 删除组
     *
     * @param groupId
     */
    public void deleteNotificationChannelGroup(@NonNull String groupId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.deleteNotificationChannelGroup(groupId);
        }
    }

    /**
     * 发送通知
     *
     * @param notification 通知具体内容
     * @return 通知Id
     */
    public int notifyNotify(@NonNull Notification notification) {
        int notifyId = getRandomId();
        return notifyNotify(notifyId, notification);
    }

    /**
     * 发送通知
     *
     * @param notifyId     通知Id
     * @param notification 通知具体内容
     * @return
     */
    public int notifyNotify(int notifyId, @NonNull Notification notification) {
        notificationManager.notify(notifyId, notification);
        return notifyId;
    }

    /**
     * 关闭状态栏通知的显示
     *
     * @param notifyId 通知Id
     */
    public void cancelNotify(int notifyId) {
        notificationManager.cancel(notifyId);
    }

    /**
     * 默认设置，调用方可以添加和修改
     *
     * @param channelId
     * @return
     */
    public NotificationCompat.Builder getDefaultBuilder(@NonNull String channelId) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);
        builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setColor(Color.parseColor("#E92110"));
        return builder;
    }

    /**
     * 检查当前渠道的通知是否可用，Android O及以上版本调用
     * <p>
     * 注：areNotificationsEnabled()返回false时，即当前App通知被关时，此方法仍可能返回true，
     *
     * @param channelId 渠道Id
     * @return false：不可用
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean areChannelsEnabled(@NonNull String channelId) {
        NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelId);
        if (notificationChannel != null && notificationChannel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
            return false;
        }
        return true;
    }

    /**
     * 检查通知是否可用
     *
     * @return false：不可用
     */
    public boolean areNotificationsEnabled() {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        return notificationManagerCompat.areNotificationsEnabled();
    }

    /**
     * 调转到渠道设置页
     *
     * @param channelId
     */
    public void gotoChannelSetting(@NonNull String channelId) {
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
        context.startActivity(intent);
    }

    /**
     * Generate a random integer
     *
     * @return int, [0, 50000)
     */
    private int getRandomId() {
        return random.nextInt(50000);
    }

    /**
     * 折叠通知栏
     *
     * @param context
     */
    public static void collapsingNotification(Context context) {
        Object service = context.getSystemService("statusbar");
        if (null == service)
            return;
        try {
            Class<?> clazz = Class.forName("android.app.StatusBarManager");
            int sdkVersion = android.os.Build.VERSION.SDK_INT;
            Method collapse = null;
            if (sdkVersion <= 16) {
                collapse = clazz.getDeclaredMethod("collapse");
            } else {
                collapse = clazz.getDeclaredMethod("collapsePanels");
            }
            collapse.setAccessible(true);
            collapse.invoke(service);
        } catch (Exception e) {
//       //e.printStackTrace();
        }
    }

    /**
     * 展开通知栏
     * @param context
     */
    public static void expandNotification(Context context) {
        Object service = context.getSystemService("statusbar");
        if (null == service)
            return;
        try {
            Class<?> clazz = Class.forName("android.app.StatusBarManager");
            int sdkVersion = android.os.Build.VERSION.SDK_INT;
            Method expand = null;
            if (sdkVersion <= 16) {
                expand = clazz.getDeclaredMethod("expand");
            } else {
                /*
                 * Android SDK 16之后的版本展开通知栏有两个接口可以处理
                 * expandNotificationsPanel()
                 * expandSettingsPanel()
                 */
                //expand =clazz.getMethod("expandNotificationsPanel");
                expand = clazz.getDeclaredMethod("expandSettingsPanel");
            }
            expand.setAccessible(true);
            expand.invoke(service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
