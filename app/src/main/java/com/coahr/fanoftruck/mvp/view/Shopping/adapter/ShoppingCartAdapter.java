package com.coahr.fanoftruck.mvp.view.Shopping.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingCart;
import com.coahr.fanoftruck.widgets.AddAndSunText;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Author： hengzwd on 2018/8/20.
 * Email：hengzwdhengzwd@qq.com
 */
public class ShoppingCartAdapter extends BaseQuickAdapter<ShoppingCart.JdataBean.CommodityBean, BaseViewHolder> {


    private onSelectChangeListener onSelectChangeListener;

    private List<ShoppingCart.JdataBean.CommodityBean> mSelectedPositions = new ArrayList<>();

    public ShoppingCartAdapter() {
        super(R.layout.item_recyclerview_shopping_in_cart, null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShoppingCart.JdataBean.CommodityBean item) {

        helper.setText(R.id.tv_commodity_info, item.getC_name())
                .setText(R.id.tv_commodity_price, "￥" + item.getC_price());
        Imageloader.loadImage(item.getC_thumbnail(), (ImageView) helper.getView(R.id.iv_commodity_img));
        ((AddAndSunText) helper.getView(R.id.ccv_commodity_count)).setEd_makes(item.getC_num());
        ((AddAndSunText) helper.getView(R.id.ccv_commodity_count)).setListener(new AddAndSunText.InterFaceMakes() {
            @Override
            public void getEd_makes(String ed_makes, int min, int max) {
                if (ed_makes !=null){
                if (Integer.parseInt(ed_makes)>max){
                    ToastUtils.showLong("超出最大范围"+max);
                    return;
                }else if (Integer.parseInt(ed_makes)<min){
                    ToastUtils.showLong("超出最小范围"+min);
                    return;
                } else {
                    item.setC_num(ed_makes);
                    onSelectChangeListener.onChange(false);
                }
                } else {
                    item.setC_num("0");
                    onSelectChangeListener.onChange(true);
                }
                KLog.d("数量",ed_makes);
            }
        });
        ((CheckBox) helper.getView(R.id.tv_check)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!mSelectedPositions.contains(item)) {
                        mSelectedPositions.add(item);
                    }
                } else {
                    if (mSelectedPositions.contains(item)) {
                        mSelectedPositions.remove(item);
                    }
                }
                onSelectChangeListener.onChange(false);
            }
        });
        if (mSelectedPositions.contains(item)) {
            ((CheckBox) helper.getView(R.id.tv_check)).setChecked(true);
        } else {
            ((CheckBox) helper.getView(R.id.tv_check)).setChecked(false);
        }
        helper.getView(R.id.rl_commodity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedPositions.contains(item)) {
                    ((CheckBox) helper.getView(R.id.tv_check)).setChecked(false);
                } else {
                    ((CheckBox) helper.getView(R.id.tv_check)).setChecked(true);
                }
            }
        });

    }

    public boolean isAllSelected() {
        if (getItemCount() ==0){
            return false;
        }
        return mSelectedPositions.size() == getItemCount();
    }

    public void setOnSelectChangeListener(onSelectChangeListener onSelectChangeListener) {
        this.onSelectChangeListener = onSelectChangeListener;
    }

    public List<ShoppingCart.JdataBean.CommodityBean> getmSelectedPositions() {
        return mSelectedPositions;
    }


    public void checkAll() {
        mSelectedPositions.clear();
        mSelectedPositions.addAll(getData());
        notifyDataSetChanged();
        onSelectChangeListener.onChange(false);
    }

    public void unCheckAll() {
        mSelectedPositions.clear();
        notifyDataSetChanged();
        onSelectChangeListener.onChange(false);
    }

    public interface onSelectChangeListener {
        void onChange(boolean isError);

    }
}
