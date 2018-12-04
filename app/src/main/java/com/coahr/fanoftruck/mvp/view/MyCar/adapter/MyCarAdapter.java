package com.coahr.fanoftruck.mvp.view.MyCar.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.model.Bean.CarListBean;

/**
 * Author： hengzwd on 2018/8/15.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyCarAdapter extends BaseQuickAdapter<CarListBean.JdataBean.MycarBean, BaseViewHolder> {
  private   CarListBean.JdataBean.MycarBean primary_carEntity;
    private int primaryPostion = 0xfffffff;//默认车的位置
    private OnLovelyCarHandleListener onLovelyCarHandleListener;
    private TextView tv_pre;

    public MyCarAdapter() {
        super(R.layout.item_recyclerview_mycar_car, null);
    }


    @Override
    protected void convert(final BaseViewHolder helper, CarListBean.JdataBean.MycarBean item) {
                if (item !=null){
                    helper.setText(R.id.car_frameno,"车架号："+item.getCar_frameno())
                            .setText(R.id.car_no,"车牌号："+item.getCar_no());
                    tv_pre = helper.getView(R.id.tv_primary_car);
                    if (item.getDefault_check().equals("1")) {
                        tv_pre = ((TextView) helper.getView(R.id.tv_primary_car));
                        primaryPostion = helper.getAdapterPosition();
                        setDrawLeft(tv_pre,R.drawable.settle_on);
                        tv_pre.setText("已设为默认车型");
                        primary_carEntity =item;
                    }else {
                        TextView tv_check = ((TextView) helper.getView(R.id.tv_primary_car));
                        setDrawLeft(tv_check,R.drawable.select);
                        tv_check.setText("设为默认车型");
                    }


                    helper.getView(R.id.tv_primary_car).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (primaryPostion!=helper.getAdapterPosition()) {
                                if (tv_pre != null) {
                                    setDrawLeft(tv_pre,R.drawable.select);
                                    tv_pre.setText("设为默认车型");
                                    primary_carEntity.setDefault_check("-1");
                                }
                                primaryPostion = helper.getAdapterPosition();
                                tv_pre = ((TextView) helper.getView(R.id.tv_primary_car));
                                setDrawLeft(tv_pre,R.drawable.settle_on);
                                tv_pre.setText("已设为默认车型");
                                primary_carEntity = item;
                                primary_carEntity.setDefault_check("1");
                                notifyDataSetChanged();
                                onLovelyCarHandleListener.onSetPrimary(item);
                            }
                        }

                    });

                    helper.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            notifyItemRemoved(helper.getAdapterPosition());
                            MyCarAdapter.this.getData().remove(item);
                            onLovelyCarHandleListener.onDeleteCar(item);
                        }
                    });

                    helper.getView(R.id.my_love_rel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (tv_pre != null) {
                                setDrawLeft(tv_pre,R.drawable.select);
                                tv_pre.setText("设为默认车型");
                                primary_carEntity.setDefault_check("-1");
                            }
                            primaryPostion = helper.getAdapterPosition();
                            tv_pre = ((TextView) helper.getView(R.id.tv_primary_car));
                            setDrawLeft(tv_pre,R.drawable.settle_on);
                            tv_pre.setText("已设为默认车型");
                            primary_carEntity = item;
                            primary_carEntity.setDefault_check("1");
                            notifyDataSetChanged();
                            onLovelyCarHandleListener.onSelectMyOrderCar(item);
                        }
                    });

                }

    }


    private void setDrawLeft(TextView view,int res){
        Drawable drawable =view.getResources().getDrawable(res);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        view.setCompoundDrawables(drawable,null,null,null);
    }

    public void setOnLovelyCarHandleListener(OnLovelyCarHandleListener onLovelyCarHandleListener) {
        this.onLovelyCarHandleListener = onLovelyCarHandleListener;
    }

    public interface OnLovelyCarHandleListener {
        void onDeleteCar(CarListBean.JdataBean.MycarBean item);

        void onSetPrimary(CarListBean.JdataBean.MycarBean item);

        void onSelectMyOrderCar(CarListBean.JdataBean.MycarBean item);
    }
}
