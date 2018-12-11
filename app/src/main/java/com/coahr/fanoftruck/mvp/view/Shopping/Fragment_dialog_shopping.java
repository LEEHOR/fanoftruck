package com.coahr.fanoftruck.mvp.view.Shopping;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseDialogFragment;
import com.coahr.fanoftruck.widgets.AddAndSunText;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 10:45
 */
public class Fragment_dialog_shopping extends BaseDialogFragment {

    @BindView(R.id.iv_commodity_img)
    ImageView iv_commodity_img;
    @BindView(R.id.add_commodity_count)
    AddAndSunText add_commodity_count;
    @BindView(R.id.bt_ensure)
    Button bt_ensure;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.tv_commodity_price)
    TextView tv_commodity_price;
    private String edMakes;
    private int mins,maxs;
    private getShoppingDialog Listener;
    public static final int  TO_BUY = 0;
    public static final int  TO_ADD_CART = 1;
    private int type;
    private float price;
    private String img;

    public static  Fragment_dialog_shopping newInstance(float price,String commodityIMG,int type) {
        Fragment_dialog_shopping fragment_dialog_shopping=new Fragment_dialog_shopping();
        Bundle bundle=new Bundle();
        bundle.putString("img",commodityIMG);
        bundle.putFloat("price",price);
        bundle.putInt("type",type);
        fragment_dialog_shopping.setArguments(bundle);
        return fragment_dialog_shopping;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragmentdialog_shoppingdetail;
    }

    @Override
    public void initView() {
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        add_commodity_count.setListener(new AddAndSunText.InterFaceMakes() {
            @Override
            public void getEd_makes(String ed_makes,int min,int max) {
                mins=min;
                maxs=max;
                edMakes = ed_makes;
            }
        });
        add_commodity_count.setEd_makes("1");
        bt_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edMakes==null){
                    ToastUtils.showLong("数量不能为空");
                    return;
                }
                if (Integer.parseInt(edMakes)>maxs){
                    ToastUtils.showLong("超出最大范围");
                    return;
                }
                if (Integer.parseInt(edMakes)<mins){
                    ToastUtils.showLong("超出最小范围");
                    return;
                }
                if (Listener != null) {
                    Listener.getShoppingCount(edMakes);
                    dismiss();
                }
            }
        });
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            type = getArguments().getInt("type");
            price = getArguments().getFloat("price");
            img = getArguments().getString("img");
            tv_commodity_price.setText(String.valueOf(price));
            Imageloader.loadImage(img,iv_commodity_img);
        }
    }

    @Override
    public void initAnimate() {

    }

    @Override
    public void iniWidow(Window window) {
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setWindowAnimations(R.style.bottom_in_out_animation);
    }

    public interface getShoppingDialog{
        void getShoppingCount(String count);
    }

    public void setListener(getShoppingDialog listener) {
        Listener = listener;
    }
}
