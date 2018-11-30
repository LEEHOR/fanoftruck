package com.coahr.fanoftruck.widgets.PopupWindows;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.model.Bean.CityBean;
import com.coahr.fanoftruck.mvp.model.Bean.CityInfoBean;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/10/29
 * on 15:10
 */
public class RecyclerViewCity_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private getCity_adapter listener;
    private Context context;
    private List<CityBean> list;
    public RecyclerViewCity_adapter(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<CityBean> list){
        this.list=list;
        getItemCount();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CityHolder(LayoutInflater.from(context).inflate(R.layout.item_popupwindows, viewGroup, false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof CityHolder){
            ((CityHolder)viewHolder).tv_city.setText(list.get(i).getCity_name());
            ((CityHolder)viewHolder).tv_city.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.getCity_adapter(list.get(i).getCity_name());
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class CityHolder extends RecyclerView.ViewHolder{
        private TextView tv_city;
        public CityHolder(@NonNull View itemView) {
            super(itemView);
            tv_city= itemView.findViewById(R.id.tv_city);
        }

    }

    public void setListener(getCity_adapter listener) {
        this.listener = listener;
    }

  public   interface getCity_adapter{
        void getCity_adapter(String string);
}

}
