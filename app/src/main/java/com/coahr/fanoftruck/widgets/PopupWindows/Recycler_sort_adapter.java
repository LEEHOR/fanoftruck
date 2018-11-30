package com.coahr.fanoftruck.widgets.PopupWindows;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 9:08
 */
public class Recycler_sort_adapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private getSort_adapter listener;
    private int selectedPosition;
    public Recycler_sort_adapter() {
        super(R.layout.item_popupwindows,null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final String item) {
        helper.setText(R.id.tv_city,item);

        helper.getView(R.id.tv_city).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.getSort_adapter(item,helper.getAdapterPosition());
                }
            }
        });
        if (helper.getAdapterPosition() == selectedPosition) {
            helper.setTextColor(R.id.tv_city, R.color.material_blue_500);
        }
    }
    public void setData(List<String> data, int selectedPosition) {
        this.selectedPosition = selectedPosition;
        setNewData(data);
    }

    public void setListener(getSort_adapter listener) {
        this.listener = listener;
    }

    public   interface getSort_adapter{
        void getSort_adapter(String string,int position);
    }
}
