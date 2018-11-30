package com.coahr.fanoftruck.widgets.AltDialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.coahr.fanoftruck.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Leehor
 * on 2018/11/27
 * on 22:45
 * 分享dialog
 */
public class Dialog_share extends AppCompatDialogFragment {

    @BindView(R.id.tv_wx_share)
    TextView tv_wx_share;
    @BindView(R.id.tv_pyq_share)
    TextView tv_pyq_share;
    @BindView(R.id.share_dialog)
    FrameLayout share_dialog;
    private Unbinder bind;
    private shareListener share_Listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_share_dialog, container, false);
        bind = ButterKnife.bind(this, view);
        addListener();
        return view;
    }

    private void addListener(){
        share_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
                tv_wx_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (share_Listener != null) {
                            share_Listener.share_wx();
                        }
                    }
                });

                tv_pyq_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (share_Listener != null) {
                            share_Listener.share_pyq();
                        }
                    }
                });
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setWindowAnimations(R.style.bottom_in_out_animation);
        }
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
    public interface shareListener{

        void  share_wx();

        void share_pyq();
    }

    public void setShareListener(Dialog_share.shareListener shareListener) {
        this.share_Listener = shareListener;
    }
}
