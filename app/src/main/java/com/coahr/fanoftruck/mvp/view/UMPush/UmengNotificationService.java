package com.coahr.fanoftruck.mvp.view.UMPush;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.umeng.message.UmengMessageService;

import org.android.agoo.common.AgooConstants;

public class UmengNotificationService extends UmengMessageService {
    @Override
    public void onMessage(Context context, Intent intent) {
        Log.i("UmengNotification", "onMessage");
        String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
        Intent intent1 = new Intent();
        intent1.setClass(context, MyNotificationService.class);
        intent1.putExtra("UmengMsg", message);
        context.startService(intent1);
    }
}
