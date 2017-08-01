package com.shenni.torontofoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shenni.torontofoods.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/21.
 */

public class FirstNearkAdapter extends RecyclerView.Adapter<FirstNearkAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater inflater;
    private List<String> list;


    public FirstNearkAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_near, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemMoney.setText(String.format(String.valueOf(R.string.item_money), list.get(position)));
        Glide.with(mContext)
                .load(R.mipmap.ic_launcher)
                .error(R.color.white)
                .into(holder.item_img_head);
        Glide.with(mContext)
                .load(R.drawable.welcome)
                .error(R.color.white)
                .into(holder.item_img);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_tv_money)
        TextView itemMoney;
        @Bind(R.id.item_img_head)
        ImageView item_img_head;
        @Bind(R.id.item_img)
        ImageView item_img;

        ViewHolder(View view) {
            super(view);
            AutoUtils.autoSize(view);
            ButterKnife.bind(this, view);
        }
    }
}
