package com.coahr.fanoftruck.mvp.view.BusinessOpportunity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.coahr.fanoftruck.R;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 15:44
 */
public class Business_child_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int want_business = 0; //待跟进

    private static final int now_business = 1; //跟进中

    private static final int issure_business = 2; //已成单

    private static final int rakeback_business = 3; //已返佣

    private static final int close_business = 4; //已关闭

    public Business_child_Adapter() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * 待跟进
     */
    public class want_business extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView phone;
        private final TextView mall;
        private final TextView count;

        public want_business(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_bucket_name);
            phone = itemView.findViewById(R.id.tv_business_phone);
            mall = itemView.findViewById(R.id.tv_business_mall);
            count = itemView.findViewById(R.id.tv_business_count);
        }

    }

    /**
     * 跟进中
     */
    public class now_business extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView phone;
        private final TextView mall;
        private final TextView count;

        public now_business(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_bucket_name);
            phone = itemView.findViewById(R.id.tv_business_phone);
            mall = itemView.findViewById(R.id.tv_business_mall);
            count = itemView.findViewById(R.id.tv_business_count);
        }

    }

    /**
     * 已成单
     */
    public class issure_business extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView phone;
        private final TextView mall;
        private final TextView count;

        public issure_business(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_bucket_name);
            phone = itemView.findViewById(R.id.tv_business_phone);
            mall = itemView.findViewById(R.id.tv_business_mall);
            count = itemView.findViewById(R.id.tv_business_count);
        }

    }

    /**
     * 已成单
     */
    public class rakeback_business extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView phone;
        private final TextView mall;
        private final TextView count;

        public rakeback_business(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_bucket_name);
            phone = itemView.findViewById(R.id.tv_business_phone);
            mall = itemView.findViewById(R.id.tv_business_mall);
            count = itemView.findViewById(R.id.tv_business_count);
        }

    }

    /**
     * 已关闭
     */
    public class close_business extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView phone;
        private final TextView mall;
        private final TextView count;

        public close_business(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_bucket_name);
            phone = itemView.findViewById(R.id.tv_business_phone);
            mall = itemView.findViewById(R.id.tv_business_mall);
            count = itemView.findViewById(R.id.tv_business_count);
        }

    }
}
