package com.weavey.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wevey.selector.dialog.R;

import java.util.List;

/**
 * Created by caoxing on 2017/3/16.
 */


public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> list;
    private ContentItemListener contentItemListener;


    private boolean isRight = true;
    private int clickPosition = -1;//最后一次选择的位置
    private int itemcolors;

    public ContentAdapter(Context context, List<String> list, ContentItemListener contentItemListener) {
        this.context = context;
        this.list = list;
        this.contentItemListener = contentItemListener;
    }

    public ContentAdapter(Context context, List<String> list, ContentItemListener contentItemListener, int itemcolor) {
        this.context = context;
        this.list = list;
        this.itemcolors = itemcolor;
        this.contentItemListener = contentItemListener;
    }

    public ContentAdapter(Context context, List<String> list, ContentItemListener contentItemListener, boolean isRightVisible) {
        this.context = context;
        this.list = list;
        this.isRight = isRightVisible;
        this.contentItemListener = contentItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dialog, null);
        ContentViewHolder contentViewHolder = new ContentViewHolder(view, contentItemListener);
        return contentViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentViewHolder) {
            if (0 != itemcolors)
                ((ContentViewHolder) holder).textView.setTextColor(ContextCompat.getColor(context, itemcolors));
            ((ContentViewHolder) holder).textView.setText(list.get(position));

            if (isRight)
                if (clickPosition == position)
                    ((ContentViewHolder) holder).mImageView.setVisibility(View.VISIBLE);
                else
                    ((ContentViewHolder) holder).mImageView.setVisibility(View.GONE);
            ((ContentViewHolder) holder).mImageView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private ImageView mImageView;
        private ContentItemListener contentItemListener;

        public ContentViewHolder(View itemView, ContentItemListener contentItemListener) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.content_item_tv);
            mImageView = (ImageView) itemView.findViewById(R.id.content_item_img);
            this.contentItemListener = contentItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            contentItemListener.onItemClick(v, clickPosition = getPosition());
            notifyDataSetChanged();
        }
    }


}