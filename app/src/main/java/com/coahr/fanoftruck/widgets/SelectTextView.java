package com.coahr.fanoftruck.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.Checkable;

import com.coahr.fanoftruck.R;

/**
 * Created by Leehor
 * on 2018/11/13
 * on 11:00
 */
public class SelectTextView extends AppCompatTextView implements Checkable {
    private int selectColor;
    private int unSelectColor;
    private boolean isCheck;
    private Drawable unSelectBackDrawable;
    private Drawable SelectBackDrawable;

    public SelectTextView(Context context) {
        super(context,null);
    }

    public SelectTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        unSelectColor = getCurrentTextColor();
        unSelectBackDrawable = getBackground();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SelectTextView);
        int SetColor = typedArray.getColor(R.styleable.SelectTextView_select_color, getResources().getColor(R.color.text_686868));
        selectColor = SetColor;
        Drawable arrayDrawable = typedArray.getDrawable(R.styleable.SelectTextView_select_background);
        SelectBackDrawable=arrayDrawable;
        isCheck = typedArray.getBoolean(R.styleable.SelectTextView_text_checked, false);
        setChecked(isCheck);
        if (SetColor != 0 && isCheck) {
            setTextColor(selectColor);
        }
        if (SelectBackDrawable !=null && isCheck){
           setBackground(SelectBackDrawable);
        }
    }

    @Override
    public void setChecked(boolean b) {
        this.isCheck = b;
    }

    @Override
    public boolean isChecked() {
        return isCheck;
    }

    @Override
    public void toggle() {
        if (!isChecked()) {
            setTextColor(selectColor);
            setBackground(SelectBackDrawable);
            setChecked(true);
        } else {
            setTextColor(unSelectColor);
            setBackground(unSelectBackDrawable);
            setChecked(false);
        }
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
    }


    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
    }

    /**外部通过调用此方法传入checked参数，然后把值传入给setChecked（）方法改变当前的选中状态*/
    public void toggle(boolean checked){
        setChecked(checked);
        toggle();
    }
}
