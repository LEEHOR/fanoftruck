package com.coahr.fanoftruck.Utils;

import android.app.Activity;
import android.text.TextUtils;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.commom.Constants;
import com.socks.library.KLog;
import com.umeng.message.PushAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class ThirdLoginUtil {
    private static Activity _mActivity;

    public static void bindWx(String wxid, String openid, Activity activity, BaseUMAuthListener umAuthListener) {
        _mActivity = activity;

        if ((wxid != null && !wxid.equals("")) && (openid != null && !openid.equals(""))) {
            new MaterialDialog.Builder(_mActivity)
                    .title("提示")
                    .content("您确认解除当前微信绑定吗")
                    .negativeText("取消")
                    .positiveText("确认")
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    }).onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    EventBus.getDefault().post("unsetWX");
                }
            }).build().show();
        } else {
            UMShareAPI mShareAPI = UMShareAPI.get(_mActivity);
            mShareAPI.getPlatformInfo(_mActivity, SHARE_MEDIA.WEIXIN, umAuthListener);
        }
    }

    public abstract static class BaseUMAuthListener implements UMAuthListener {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, final Map<String, String> data) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                KLog.e("lizhiguo","key= " + entry.getKey() + " and value= " + entry.getValue());
            }
            KLog.e("onComplete");
            if (SHARE_MEDIA.WEIXIN.equals(platform)) {
                if (TextUtils.isEmpty(Constants.devicestoken)) {
                    Constants.devicestoken = PushAgent.getInstance(_mActivity).getRegistrationId();
                }
                PreferenceUtils.setPrefString(_mActivity, "devicetoken", Constants.devicestoken);
                new MaterialDialog.Builder(_mActivity)
                        .title("提示")
                        .content("您确定使用当前的微信登录吗")
                        .iconRes(R.mipmap.wxzf)
                        .negativeText("取消")
                        .positiveText("确认")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        }).onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        _onComplete(data);
                    }
                }).build().show();
            }
        }

        protected abstract void _onComplete(Map<String, String> data);

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
        }
    };
}