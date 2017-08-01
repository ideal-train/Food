package com.shenni.torontofoods.utils;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.shenni.torontofoods.R;


public class DialogUtil {

    /**
     * Context
     */
//    private static Context mContext = App.getAppContext();
    /**
     * 系统等待提示框
     */
    private static ProgressDialog pd;
    /**
     * 自定义等待提示框
     */
    private static Dialog dialog;
    private static TextView vLoading_text;
    private static String mMsg = "加载中···";

    /**
     * 提示
     *
     * @param context 上下文
     * @param tiele   标题
     * @param content 正文
     * @param tips    按钮内容
     */
    public static void myDialog(Context context, String tiele, String content,
                                String tips) {
        // TODO Auto-generated method stub
        // // THEME_DEVICE_DEFAULT_LIGHT THEME_HOLO_LIGHT
        new AlertDialog.Builder(context).setTitle(tiele).setMessage(content)
                // .setIcon(android.R.drawable.ic_dialog_info)
                .setNegativeButton(tips, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
    }

    /**
     * 错误提示
     *
     * @param content 提示内容无标题
     */
    public static void errorDialog(Context context, String content) {
        // TODO Auto-generated method stub
        // // THEME_DEVICE_DEFAULT_LIGHT THEME_HOLO_LIGHT
        new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT)
                .setTitle("\n" + content + "\n")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
    }

    // new
    // AlertDialog.Builder(MainActivity.this).setTitle("您选择的是").setMessage(ts.trim().substring(0,
    // ts.length()-1).toString()).setPositiveButton("关闭", null).show();

    /**
     * 成功提示
     *
     * @param title 提示标题
     */
    public static void successDialog(final Context context, String title) {
        new AlertDialog.Builder(context)
                .setTitle(" \n" + title + " \n")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setNegativeButton("确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                })
                .setPositiveButton("取消",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // 点击“确认”后的操作
                                ((Activity) context).finish();
                            }
                        }).show();

    }

    /**
     * 系统提示无标题加载框（上传中。。。）
     *
     * @param getActivity
     * @param title
     * @param content
     */
    public static void waitMethod(Context getActivity, String title,
                                  String content) {
        pd = new ProgressDialog(getActivity);
        pd.setTitle(title);
        pd.setMessage(content);
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        // pd = ProgressDialog.show(getActivity, title, content);

    }

    public static void showMyDialog(final Context context) {
        showMyDialog(context, false);
    }


    /**
     * 自定义提示无标题加载框
     *
     * @param context
     */
    public static void showMyDialog(final Context context, boolean isBack) {
        showMyDialog(context, isBack, "");
    }

    public static void showMyDialog(final Context context, boolean isBack, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_loading, null);
        vLoading_text = (TextView) view.findViewById(R.id.loading_text);
        vLoading_text.setText(StringUtil.isNullOrEmpty(msg) ? mMsg : msg);
        if (null == dialog) {
            DialogUtil.dialog = new Dialog(context, R.style.MyDialog);
            //空白是否退出
            dialog.setCanceledOnTouchOutside(isBack);
            dialog.setContentView(view);
            //返回键是否退出
            dialog.setCancelable(true);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    ((Activity) context).finish();
                }
            });
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogs) {
                    dialog = null;
                }
            });
            dialog.show();
        }
    }


    /**
     * 关闭自定义加载框
     */
    public static void closeMyDialog() {
        try {
            if (null != dialog) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
        }
    }

    /**
     * 关闭系统加载框
     */
    public static void closeMethod() {
        try {
            if (null != pd) {
                pd.dismiss();
            }
        } catch (Exception e) {
        }
    }
}
