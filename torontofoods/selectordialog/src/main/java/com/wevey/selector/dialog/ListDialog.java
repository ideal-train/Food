package com.wevey.selector.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.weavey.utils.ContentAdapter;

import java.util.List;

/**
 * Created by Weavey on 2016/9/3.
 */
public class ListDialog {

    private Dialog mDialog;
    private View dialogView;

    private Button bottomBtn;
    public RecyclerView mRecyclerView;

    private Builder mBuilder;
    private List<String> datas;


    public ListDialog(Builder builder) {

        this.mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.bottomDialogStyle);
        dialogView = View.inflate(mBuilder.getContext(), R.layout.list_dialog, null);
        mDialog.setContentView(dialogView); // 一定要在setAttributes(lp)之前才有效

        //设置dialog的宽
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.width = (int) (ScreenSizeUtils.getInstance(mBuilder.getContext()).getScreenWidth() *
//                builder.getItemWidth());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);


        bottomBtn = (Button) dialogView.findViewById(R.id.action_dialog_botbtn);
        mRecyclerView = (RecyclerView) dialogView.findViewById(R.id.dialog_content);

        LinearLayoutManager lm = new LinearLayoutManager(mBuilder.getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(lm);

        mRecyclerView.setAdapter(mBuilder.getContentAdapter());

        if (mBuilder.isbottom()) {
            mRecyclerView.setBackgroundResource(R.drawable.selector_widget_actiondialog_middle);
            bottomBtn.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setBackgroundResource(R.drawable.selector_widget_actiondialog_top);
            bottomBtn.setVisibility(View.GONE);
        }
        bottomBtn.setText(mBuilder.getBottom());
        bottomBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                mDialog.dismiss();
            }
        });
        mDialog.setCanceledOnTouchOutside(builder.isTouchOutside());
    }

    public ListDialog setDatas(List<String> datas) {
        return this;
    }

    public ListDialog show() {

        mDialog.show();
        return this;
    }

    public void dismiss() {

        mDialog.dismiss();
    }
    public static class Builder {
        private Context mContext;
        private boolean isTouchOutside;

        private String bottom;
        private boolean isbottom;

        private ContentAdapter contentAdapter;

        public ContentAdapter getContentAdapter() {
            return contentAdapter;
        }

        public Builder setContentAdapter(ContentAdapter contentAdapter) {
            this.contentAdapter = contentAdapter;
            return this;
        }

        public Builder(Context context) {
            isbottom = true;
            bottom = "取消";
            isTouchOutside = true;
            mContext = context;
        }


        public String getBottom() {
            return bottom;
        }

        public Builder setBottom(String bottom) {
            this.bottom = bottom;
            return this;
        }

        public boolean isbottom() {
            return isbottom;
        }

        public Builder setBottomVisivle(boolean isbottom) {
            this.isbottom = isbottom;
            return this;
        }
        public Builder setCanceledOnTouchOutside(boolean isTouchOutside) {
            this.isTouchOutside = isTouchOutside;
            return this;
        }

        public Context getContext() {
            return mContext;
        }



        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public ListDialog build() {

            return new ListDialog(this);
        }

    }


}

