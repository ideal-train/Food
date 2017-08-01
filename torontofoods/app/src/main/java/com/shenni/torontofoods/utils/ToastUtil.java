package com.shenni.torontofoods.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.shenni.torontofoods.MyApplication;


/**
 * Toast工具类
 *
 * @author redkid
 */
public class ToastUtil {
    private static Toast mToast = null;

    //跟随APP生命周期
    public static void toast(int msg) {
        toast(MyApplication.getContext().getResources().getString(msg));
    }

    public static void toast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText((MyApplication.getContext()), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    //以下方法跟随Activity生命周期
    public static void shortToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_SHORT);
    }

    public static void shortToast(int resId) {
        showToast(MyApplication.getContext(), resId, Toast.LENGTH_SHORT);
    }

    public static void shortToast( String text) {
        if (!TextUtils.isEmpty(text) && !"".equals(text.trim())) {
            showToast(MyApplication.getContext(), text, Toast.LENGTH_SHORT);
        }
    } public static void shortToast(Context context, String text) {
        if (!TextUtils.isEmpty(text) && !"".equals(text.trim())) {
            showToast(context, text, Toast.LENGTH_SHORT);
        }
    }

    public static void longToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_LONG);
    }

    public static void longToast(Context context, String text) {
        if (!TextUtils.isEmpty(text) && !"".equals(text.trim())) {
            showToast(context, text, Toast.LENGTH_LONG);
        }
    }

    private static void showToast(Context context, int resId, int duration) {
        if (context == null) {
            return;
        }
        if (context != null && context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        String text = context.getString(resId);
        showToast(context, text, duration);
    }

    private static void showToast(Context context, String text, int duration) {
        if (context == null) {
            return;
        }
        if (context != null && context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (!TextUtils.isEmpty(text) && !"".equals(text.trim())) {
            // Toast toast = Toast.makeText(context, text, duration);
            // toast.setGravity(Gravity.CENTER, 0, 0);
            // toast.show();
//			Toast.makeText(context, text, duration).show();

            if (mToast == null) {
                mToast = Toast.makeText(context, text, duration);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }
}