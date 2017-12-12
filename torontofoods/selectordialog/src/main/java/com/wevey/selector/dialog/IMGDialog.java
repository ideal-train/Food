package com.wevey.selector.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.weavey.utils.ScreenSizeUtils;

/**
 * Created by Weavey on 2016/9/4.
 */
public class IMGDialog {

    private Dialog mDialog;
    private View mDialogView;
    private TextView mTitle;
    private TextView mContent;
    private TextView mLeftBtn;
    private TextView mRightBtn;
    private TextView md_dialog_content2;
    private TextView dialog_normal_line, textView2;
    private Builder mBuilder;
    private ImageView img_tip, img_tip_2;
    private ImageView img_close_dialog;

    public IMGDialog(Builder builder) {

        mBuilder = builder;
        mDialog = new Dialog(mBuilder.getContext(), R.style.MyDialogStyle);
        mDialogView = View.inflate(mBuilder.getContext(), R.layout.widget_md_dialog, null);
        mTitle = (TextView) mDialogView.findViewById(R.id.md_dialog_title);
        mContent = (TextView) mDialogView.findViewById(R.id.md_dialog_content);
        mLeftBtn = (TextView) mDialogView.findViewById(R.id.md_dialog_leftbtn);
        mRightBtn = (TextView) mDialogView.findViewById(R.id.md_dialog_rightbtn);
        dialog_normal_line = (TextView) mDialogView.findViewById(R.id.dialog_normal_line2);
        md_dialog_content2 = (TextView) mDialogView.findViewById(R.id.md_dialog_content2);
        textView2 = (TextView) mDialogView.findViewById(R.id.textView2);
        img_tip = (ImageView) mDialogView.findViewById(R.id.img_tip);
        img_tip_2 = (ImageView) mDialogView.findViewById(R.id.img_tip_2);
        img_close_dialog = (ImageView) mDialogView.findViewById(R.id.img_close_dialog);
        mDialogView.setMinimumHeight((int) (ScreenSizeUtils.getInstance(mBuilder.getContext())
                .getScreenHeight() * builder.getHeight()));
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(mBuilder.getContext()).getScreenWidth() *
                builder.getWidth());
//        lp.width =WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        initDialog();

    }

    private void initDialog() {

        mDialog.setCanceledOnTouchOutside(mBuilder.isTouchOutside());
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                //处理监听事件
                if (mBuilder.iscloseActivity())
                    ((Activity) mBuilder.getContext()).finish();
            }
        });

        if (mBuilder.getTitleVisible()) {

            mTitle.setVisibility(View.VISIBLE);
        } else {

            mTitle.setVisibility(View.GONE);
        }

        if (mBuilder.getJustLeftButton()) {
            mTitle.setVisibility(View.INVISIBLE);
            mContent.setVisibility(View.GONE);
            dialog_normal_line.setVisibility(View.GONE);
            mLeftBtn.setBackgroundResource(R.drawable.selector_widget_md_dialog);
            mRightBtn.setVisibility(View.GONE);
            md_dialog_content2.setVisibility(View.VISIBLE);
            img_close_dialog.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            img_tip_2.setVisibility(View.VISIBLE);
            img_tip.setVisibility(View.GONE);
        }

        mTitle.setText(mBuilder.getTitleText());
        textView2.setText(mBuilder.getTitleText2());
        mTitle.setTextColor(mBuilder.getTitleTextColor());
        mTitle.setTextSize(mBuilder.getTitleTextSize());
        md_dialog_content2.setText(mBuilder.getContentText2());
        mContent.setText(mBuilder.getContentText());
        mContent.setTextColor(mBuilder.getContentTextColor());
        mContent.setTextSize(mBuilder.getContentTextSize());
        mLeftBtn.setText(mBuilder.getLeftButtonText());
        mLeftBtn.setTextColor(mBuilder.getLeftButtonTextColor());
        mLeftBtn.setTextSize(mBuilder.getButtonTextSize());
        mRightBtn.setText(mBuilder.getRightButtonText());
        mRightBtn.setTextColor(mBuilder.getRightButtonTextColor());
        mRightBtn.setTextSize(mBuilder.getButtonTextSize());

        mLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuilder.getListener() != null) {

                    mBuilder.getListener().clickLeftButton(IMGDialog.this, mLeftBtn);
                }

            }
        });
        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getListener() != null) {

                    mBuilder.getListener().clickRightButton(IMGDialog.this, mRightBtn);
                }

            }
        });
        img_close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.getListener() != null) {

                    mBuilder.getListener().clickColseButton(IMGDialog.this, img_close_dialog);
                }

            }
        });

    }

    public void show() {

        mDialog.show();
    }

    public void dismiss() {

        mDialog.dismiss();
    }

    public Dialog getDialog() {

        return mDialog;
    }

    public static class Builder {

        private String titleText;
        private String titleText2;
        private int titleTextColor;
        private int titleTextSize;
        private String contentText;
        private String contentText2;
        private int contentTextColor;
        private int contentTextSize;
        private String leftButtonText;
        private int leftButtonTextColor;
        private String rightButtonText;
        private int rightButtonTextColor;
        private int buttonTextSize;
        private boolean isTitleVisible;
        private boolean isTouchOutside;
        private boolean isJustLeftButton;
        private boolean iscloseActivity;
        private float height;
        private float width;
        private SelectDialogInterface.OnLeftAndRightClickListener<IMGDialog> listener;
        private Context mContext;

        public Builder(Context context) {

            mContext = context;
            titleText = "提示";
            titleText2 = "恭喜！今日获得积分";
            titleTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            contentText = "";
            contentText2 = "";
            contentTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            leftButtonText = "取消";
            leftButtonTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            rightButtonText = "确定";
            rightButtonTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            listener = null;
            isTitleVisible = true;
            isTouchOutside = true;
            isJustLeftButton = false;
            iscloseActivity = false;
            height = 0.21f;
            width = 0.73f;
            titleTextSize = 16;
            contentTextSize = 14;
            buttonTextSize = 14;
        }

        public Context getContext() {

            return mContext;
        }

        public String getTitleText() {
            return titleText;
        }

        public Builder setTitleText(String titleText) {
            this.titleText = titleText;
            return this;
        }

        public int getTitleTextColor() {
            return titleTextColor;
        }

        public Builder setTitleTextColor(@ColorRes int titleTextColor) {
            this.titleTextColor = ContextCompat.getColor(mContext, titleTextColor);
            return this;
        }

        public String getContentText() {
            return contentText;
        }

        public Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public String getContentText2() {
            return contentText2;
        }

        public Builder setContentText2(String contentText) {
            this.contentText2 = contentText;
            return this;
        }

        public String getTitleText2() {
            return titleText;
        }

        public Builder setTitleText2(String contentText) {
            this.titleText = contentText;
            return this;
        }

        public int getContentTextColor() {
            return contentTextColor;
        }

        public Builder setContentTextColor(@ColorRes int contentTextColor) {
            this.contentTextColor = ContextCompat.getColor(mContext, contentTextColor);
            return this;
        }

        public boolean iscloseActivity() {
            return iscloseActivity;
        }

        public Builder setIscloseActivity(boolean iscloseActivity) {
            this.iscloseActivity = iscloseActivity;
            return this;
        }

        public String getLeftButtonText() {
            return leftButtonText;
        }

        public Builder setLeftButtonText(String leftButtonText) {
            this.leftButtonText = leftButtonText;
            return this;
        }

        public int getLeftButtonTextColor() {
            return leftButtonTextColor;
        }

        public Builder setLeftButtonTextColor(@ColorRes int leftButtonTextColor) {
            this.leftButtonTextColor = ContextCompat.getColor(mContext, leftButtonTextColor);
            return this;
        }

        public String getRightButtonText() {
            return rightButtonText;
        }

        public Builder setRightButtonText(String rightButtonText) {
            this.rightButtonText = rightButtonText;
            return this;
        }

        public int getRightButtonTextColor() {
            return rightButtonTextColor;
        }

        public Builder setRightButtonTextColor(@ColorRes int rightButtonTextColor) {
            this.rightButtonTextColor = ContextCompat.getColor(mContext, rightButtonTextColor);
            return this;
        }

        public boolean getTitleVisible() {
            return isTitleVisible;
        }

        public Builder setTitleVisible(boolean titleVisible) {
            isTitleVisible = titleVisible;
            return this;
        }

        public boolean getJustLeftButton() {
            return isJustLeftButton;
        }

        public Builder setJustLeftButton(boolean isAJustLeftButton) {
            isJustLeftButton = isAJustLeftButton;
            return this;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean touchOutside) {
            isTouchOutside = touchOutside;
            return this;
        }

        public float getHeight() {
            return height;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public float getWidth() {
            return width;
        }

        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        public int getContentTextSize() {
            return contentTextSize;
        }

        public Builder setContentTextSize(int contentTextSize) {
            this.contentTextSize = contentTextSize;
            return this;
        }

        public int getTitleTextSize() {
            return titleTextSize;
        }

        public Builder setTitleTextSize(int titleTextSize) {
            this.titleTextSize = titleTextSize;
            return this;
        }

        public int getButtonTextSize() {
            return buttonTextSize;
        }

        public Builder setButtonTextSize(int buttonTextSize) {
            this.buttonTextSize = buttonTextSize;
            return this;
        }

        public SelectDialogInterface.OnLeftAndRightClickListener<IMGDialog> getListener() {
            return listener;
        }

        public Builder setOnclickListener(SelectDialogInterface.OnLeftAndRightClickListener<IMGDialog> listener) {
            this.listener = listener;
            return this;
        }

        public IMGDialog build() {

            return new IMGDialog(this);
        }
    }

}
