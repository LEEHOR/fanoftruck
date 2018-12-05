package com.coahr.fanoftruck.widgets.AltDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.KeyBoardUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.widgets.Keyboard.KeyboardChangeListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Leehor
 * on 2018/11/29
 * on 13:25
 */
public class Input_keyboardDialog extends AppCompatDialogFragment implements TextView.OnEditorActionListener {

    private Unbinder unbinder;
    @BindView(R.id.input_ediText)
    EditText input_ediText;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.out_re)
    FrameLayout out_empty;
    @BindView(R.id.tv_count)
    TextView tv_count;
    private videoSendDiscuss videoSendDiscussListener;
    private KeyboardChangeListener keyboardChangeListener;
    private Dialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_input_keyboard, container, false);
        unbinder = ButterKnife.bind(this, view);
        KeyBoardUtils.UpdateUI(view.getRootView(), getActivity());
        init();
        return view;
    }

    private void init(){
        input_ediText.setImeOptions(EditorInfo.IME_ACTION_SEND);
        KeyBoardUtils.showKeybord(input_ediText, getActivity());
    /*    keyboardChangeListener = new KeyboardChangeListener(getDialog().getWindow());
        keyboardChangeListener.setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                if (!isShow) {
                   // dismiss();
                }
            }
        });*/
        out_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input_ediText.getText().toString().length()>0 && input_ediText.getText().toString().length()<=50){
                    if (videoSendDiscussListener != null) {
                        videoSendDiscussListener.sendVideoDiscuss(input_ediText.getText().toString(),dialog);
                    }
                } else {
                    ToastUtils.showLong("请控制在1～50个字符");
                }
            }
        });
        input_ediText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                input_ediText.setImeOptions(EditorInfo.IME_ACTION_SEND);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if ( !TextUtils.isEmpty(charSequence) && !charSequence.equals("")) {
                    tv_count.setText(charSequence.length() + "个");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setWindowAnimations(R.style.bottom_in_out_animation);
        }
        return dialog;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEND) {
            KeyBoardUtils.hideKeybord(textView, getActivity());
                if (input_ediText.getText() != null && !input_ediText.getText().equals("")) {
                    if (videoSendDiscussListener != null) {
                        videoSendDiscussListener.sendVideoDiscuss(input_ediText.getText().toString(),getDialog());
                    }
            }
            return true;
        }

        return false;
    }

    public interface  videoSendDiscuss{

        void  sendVideoDiscuss(String s,Dialog dialog);
    }

    public void setVideoSendDiscussListener(videoSendDiscuss videoSendDiscussListener) {
        this.videoSendDiscussListener = videoSendDiscussListener;
    }
}
