package com.coahr.fanoftruck.mvp.view.MyAddress.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.model.Bean.AddressListBean;

/**
 * Author： hengzwd on 2018/8/16.
 * Email：hengzwdhengzwd@qq.com
 */
public class AddressAdapter extends BaseQuickAdapter<AddressListBean.JdataBean.AddressEntity, BaseViewHolder> {

    private TextView primary_checkbox;
    private int primaryPostion = 0xfffffff;//默认车的位置
    private onAddressHandleListener onAddressHandleListener;
    public AddressAdapter() {
        super(R.layout.item_recyclerview_myaddress, null);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final AddressListBean.JdataBean.AddressEntity item) {
        helper.setText(R.id.tv_user_name, item.getUsername())
                .setText(R.id.tv_telephone, item.getTelephone())
                .setText(R.id.tv_address, item.getAddress());
        if (item.getSelected().equals("1")) {
            primary_checkbox = ((TextView) helper.getView(R.id.tv_primary_address));
            primaryPostion = helper.getAdapterPosition();
            setDrawLeft(primary_checkbox,R.mipmap.select_on);
        }else {
            setDrawLeft((TextView) helper.getView(R.id.tv_primary_address),R.mipmap.select);
        }
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddressHandleListener.onItemClick(item);
            }
        });
        helper.getView(R.id.tv_primary_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primaryPostion!=helper.getAdapterPosition()) {
                    if (primary_checkbox != null) {
                        setDrawLeft(primary_checkbox,R.mipmap.select);
                    }
                    primaryPostion = helper.getAdapterPosition();
                    primary_checkbox = ((TextView) helper.getView(R.id.tv_primary_address));
                    setDrawLeft(primary_checkbox,R.mipmap.select_on);
                    onAddressHandleListener.onSetPrimary(item);
                }
            }
        });
        helper.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddressHandleListener.delete(item);
            }
        });
        helper.getView(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddressHandleListener.edit(item);
            }
        });
    }
    private void setDrawLeft(TextView view,int res){
        Drawable drawable =view.getResources().getDrawable(res);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        view.setCompoundDrawables(drawable,null,null,null);
    }

    public void setOnAddressHandleListener(onAddressHandleListener onAddressHandleListener){
        this.onAddressHandleListener = onAddressHandleListener;
    }
    public interface onAddressHandleListener {

        void onItemClick(AddressListBean.JdataBean.AddressEntity item);
        void edit(AddressListBean.JdataBean.AddressEntity item);

        void delete(AddressListBean.JdataBean.AddressEntity item);

        void onSetPrimary(AddressListBean.JdataBean.AddressEntity item);
    }
}
