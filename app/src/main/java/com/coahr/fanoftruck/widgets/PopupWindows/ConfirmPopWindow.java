package com.coahr.fanoftruck.widgets.PopupWindows;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.ScreenUtils;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 11:45
 */
public class ConfirmPopWindow extends PopupWindow {
    private Context context;
    private TextView tips;
    private String text;

    public ConfirmPopWindow(Context context) {
        super(context);
        this.context = context;

        initalize();
    }

    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_tips, null);
        tips = view.findViewById(R.id.tv_tips);

        setContentView(view);
        initWindow();
    }

    private void initWindow() {
        this.setWidth(ScreenUtils.getScreenWidth(BaseApplication.mContext));
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       // backgroundAlpha((Activity) context, 0.8f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
              //  backgroundAlpha((Activity) context, 1f);
            }
        });
    }

    //设置添加屏幕的背景透明度
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    public void showAtBottom(View view,String text) {
        this.text=text;
        tips.setText(text);
        //弹窗位置设置
        showAsDropDown(view, Math.abs((view.getWidth() - getWidth()) / 2), -(view.getHeight()*2+this.getHeight()));
      //  showAtLocation(view, Gravity.TOP | Gravity.LEFT, 10, 10);//有偏差
    }

}
