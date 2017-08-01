package com.shenni.torontofoods.utils;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.shenni.torontofoods.MyApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressLint("SimpleDateFormat")
public class StringUtil {
    private final static String HEX = "0123456789ABCDEF";

    public static String toMD5(String source) {
        if (null == source || "".equals(source)) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(source.getBytes());
            return toHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    /**
     * 判断是否是null或长度为0
     *
     * @param str 要判断字符串
     * @return 空：true
     */
    public static boolean isNullOrEmpty(String str) {
        try {
            if (str == null)
                return true;
            if (str.length() == 0)
                return true;
            if (str.isEmpty())
                return true;
            if (str.replace(" ", "").equalsIgnoreCase("null"))
                return true;
            if ("".equals(str.replace(" ", "")))
                return true;
        } catch (Exception e) {
            return true;
        }
        return false;
    }


    /**
     * will trim the string
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (null == s) return true;
        if (s.length() == 0) return true;
        if (s.trim().length() == 0) return true;
        return false;
    }


    /**
     * 获取时间
     */
    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd hh:mm");
        Date date = new Date();
        String datestring = sdf.format(date);
        return datestring;
    }

    /**
     * 获取时间
     *
     * @param timetype MM-dd hh:mm
     * @return
     */
    public static String getTime(String timetype) {
        SimpleDateFormat sdf = new SimpleDateFormat(timetype);
        Date date = new Date();
        String datestring = sdf.format(date);
        return datestring;
    }


    public static String toHex(byte[] buf) {
        if (buf == null) return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }


    /**
     * 手机号正则
     */
    public static boolean isPhone(String mobiles) {
        //        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(170))\\d{8}$");
//        Pattern p = Pattern.compile("^\\d{11}$");
        Pattern p = Pattern.compile("^[1]\\d{10}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 验证码正则
     */
    public static boolean isCodes(String codes) {
        Pattern p = Pattern.compile("\\d{6}$");
        Matcher m = p.matcher(codes);
        return m.matches();
    }

    public static String TimeSpanFormat(long time) {
        String date = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        date = df.format(new Date(time));
        return date;
    }

    public static String TimeSpanFormat(String time) {
        long lon = Long.parseLong(time);
        String date = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        date = df.format(new Date(lon));
        return date;
    }


    public static String MSStampHHmmStr(long time) {
        String date = null;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Long aLong = new Long(time);
        date = df.format(aLong);
        return date;
    }


    /**
     * 区间随机数
     *
     * @param min 区间最小值
     * @param max 区间最大值
     * @return 区间正随机数
     */
    public static int intRandom(int min, int max) {
//        Random random = new Random();
//        return random.nextInt(max) % (max - min + 1) + min;

        if (min > max) {
            return 0;
        }
        if (min == max) {
            return min;
        }
        return new Random().nextInt(max - min + 1) + min;
    }

    /**
     * @param min
     * @param max
     * @return 未完成
     */
    public static String douRandom(int min, int max) {

        if (min > max) {
            return "0";
        }
        if (min == max) {
            return String.valueOf(min);
        }
        double money = (new Random().nextDouble() * (20 - 1) + 1);
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(money);
    }


    /**
     * 复制文本到剪贴板
     *
     * @param text 文本
     */
    public static void copyText(CharSequence text) {
        // 得到剪贴板管理器
        ClipboardManager clipboard = (ClipboardManager) MyApplication.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newPlainText("text", text));
    }

}
