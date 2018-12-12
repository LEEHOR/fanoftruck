package com.coahr.fanoftruck.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.socks.library.KLog;

/**
 * Created by Leehor
 * on 2018/12/10
 * on 9:26
 */
public class AddAndSunText extends LinearLayout {
    private int bgColor;
    private View view;
    private TextView plus;
    private TextView minus;
    private EditText ed_makes;
    private int min;
    private int max;
    private int ed_max;
    private float textSize;
    private InterFaceMakes listener;
    private boolean isTouchText; //防止过多回调

    public AddAndSunText(Context context) {
        super(context, null);
    }

    public AddAndSunText(Context context, AttributeSet attrs) {

        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AddAndSunText);
        view = LayoutInflater.from(context).inflate(R.layout.layout_addandsuntextview, this);
        bgColor = ta.getColor(R.styleable.AddAndSunText_bgColor, getResources().getColor(R.color.colorWhite));

        textSize = ta.getFloat(R.styleable.AddAndSunText_AddTextSize, 14);
        min = ta.getInt(R.styleable.AddAndSunText_add_min, 1);
        max = ta.getInt(R.styleable.AddAndSunText_add_max, 99);

        plus = view.findViewById(R.id.tv_plus);
        minus = view.findViewById(R.id.tv_minus);
        ed_makes = view.findViewById(R.id.ed_makes);
        init();
        ta.recycle();
    }

    private void init() {
        view.setBackgroundColor(bgColor);
        plus.setTextSize(textSize);
        minus.setTextSize(textSize);
        ed_makes.setTextSize(textSize);
        ed_makes.setText(String.valueOf(0));
        addListener();
    }

    private void addListener() {
        plus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isTouchText=true;
                ed_makes.setFocusable(false);
                ed_makes.setFocusableInTouchMode(false);
                if (TextUtils.isEmpty(ed_makes.getText()) || ed_makes.getText().toString().equals("")) {
                    ed_makes.setText(String.valueOf("0"));
                }
                ed_max = Integer.parseInt(ed_makes.getText().toString());
                if (ed_max >=max) {
                    ToastUtils.showLong("已经为最大值");
                } else {
                    ed_max++;
                    ed_makes.setText(String.valueOf(ed_max));
                }
                if (listener != null) {
                    listener.getEd_makes(ed_makes.getText().toString(),min,max);
                }
            }
        });

        minus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isTouchText=true;
                ed_makes.setFocusable(false);
                ed_makes.setFocusableInTouchMode(false);
                if (TextUtils.isEmpty(ed_makes.getText()) || ed_makes.getText().toString().equals("")) {
                    ed_makes.setText(String.valueOf("0"));
                }
                ed_max = Integer.parseInt(ed_makes.getText().toString());
                if (ed_max <= min) {
                    ToastUtils.showLong("已经为最小值");
                } else {
                    ed_max--;
                    ed_makes.setText(String.valueOf(ed_max));
                }
                if (listener != null) {
                    listener.getEd_makes(ed_makes.getText().toString(),min,max);
                }
            }
        });
        ed_makes.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isTouchText=false;
                ed_makes.setFocusable(true);
                ed_makes.setFocusableInTouchMode(true);
                return false;
            }
        });
        ed_makes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!isTouchText) {
                    if (listener != null) {
                        if (!TextUtils.isEmpty(editable) && !editable.toString().equals("")) {
                            listener.getEd_makes(editable.toString(),min,max);
                        } else {
                            listener.getEd_makes(null,min,max);
                        }
                    }
                }

            }
        });
    }

    /**
     * 获取数目
     *
     * @return
     */
    public String getEd_makes() {
        if (TextUtils.isEmpty(ed_makes.getText()) || ed_makes.getText().toString().equals("")) {
            return null;
        } else {
            return ed_makes.getText().toString();
        }

    }

    public void setEd_makes(String Ed_makes) {
        ed_makes.setText(Ed_makes);
        if (listener != null) {
            listener.getEd_makes(Ed_makes,min,max);
        }

    }

    public void setListener(InterFaceMakes listener) {
        this.listener = listener;
    }

    public interface InterFaceMakes {

        void getEd_makes(String ed_makes,int min,int max);
    }

}
