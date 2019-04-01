package com.coahr.fanoftruck.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.coahr.fanoftruck.mvp.Base.BaseApplication;

import androidx.appcompat.app.AppCompatActivity;

public class ToastUtils {

	private static Toast mToast;
	private static Toast toast2;
	public static void showLong(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(BaseApplication.mContext, text, Toast.LENGTH_LONG);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

	public static void showShort(Context context, String text) {
		if (mToast == null) {
			mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

	public static void Toast_showImage(AppCompatActivity activity, final String tvStr, final int imageResource, int  duration, View view){
		if (toast2 == null) {

			toast2 = new Toast(activity);

		}


		toast2.setView(view);

		toast2.setGravity(Gravity.CENTER, 0, 0);

		toast2.setDuration(duration);

		toast2.show();
	}

}
