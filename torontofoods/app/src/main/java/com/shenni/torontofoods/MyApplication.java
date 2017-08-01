package com.shenni.torontofoods;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MyApplication extends android.app.Application {
//    public static String VIDEO_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "shennidemo";
    public static Context mContext;
    private static MyApplication sInstance = null;


    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize();
        mContext = getApplicationContext();
        sInstance = this;
        setLogger();
        setOkGo();
//        UMAPI();
//        setBugly();
//        setNineGridView();
//        setjiecaovideoplayer();
    }

    private void setLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }


    /**
     * @return 返回 APP的Application
     */
    public static MyApplication getInstance() {
        return sInstance;
    }
//    getApplication():andorid 开发中共享全局数据; 在Activity中

    /**
     * 这个应 用程序的Context
     * 生命周期是整个应用，应用摧毁，它才摧毁。
     * Dialog中不可用
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * OkGo初始化参数设置
     */
    private void setOkGo() {

        //公共参数
        HttpParams params = new HttpParams();
        params.put("channel", "APP");

        //必须调用初始化
        OkGo.init(this);

        //配置参数
        try {
            OkGo.getInstance()
                    // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                    // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
//                    .debug("OkGo", Level.INFO, true)
                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(8 * 1000)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间
                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(3)
                    .addCommonParams(params);
        } catch (Exception e) {
        }
    }


    /**
     * 视频播放的初始化参数设置
     */
    private void setjiecaovideoplayer() {
        //it is public static, you can set this everywhere
//        可以通过这两个变量手动控制是否隐藏ToolBar和ActionBar
//        JCVideoPlayer.TOOL_BAR_EXIST = false;
//        JCVideoPlayer.ACTION_BAR_EXIST = false;
//        设置宽高比例
//        jcVideoPlayer.widthRatio = 16;
//        jcVideoPlayer.heightRatio = 9;
    }




    /**
     * 初始化Bugly
     */
    private void setBugly() {
        Context context = getApplicationContext();
// 获取当前包名
        String packageName = context.getPackageName();
// 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
// 设置是否为上报进程
        UserStrategy strategy = new UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
// 初始化Bugly
//        CrashReport.initCrashReport(context, "注册时申请的APPID", isDebug, strategy);
        CrashReport.initCrashReport(getApplicationContext(), "", false);
// 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
// CrashReport.initCrashReport(context, strategy);
    }


    public static String getApplicationName() {
        String appName = "";
        try {
            PackageManager packManager = sInstance.getPackageManager();
            ApplicationInfo appInfo = sInstance.getApplicationInfo();
            appName = (String) packManager.getApplicationLabel(appInfo);
        } catch (Exception e) {
        }
        return appName;
    }





    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 获取应用程序名称
     */
    public static String getAppName() {
        try {
            return mContext.getResources().getString(getPackageInfo().applicationInfo.labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 版本名
     */
    public static String getVersionName() {
        return getPackageInfo().versionName;
    }

    /**
     * 版本号
     */
    public static int getVersionCode() {
        return getPackageInfo().versionCode;
    }

    private static PackageInfo getPackageInfo() {
        PackageInfo pi = null;
        try {
            PackageManager packageManager = mContext.getPackageManager();
            pi = packageManager.getPackageInfo(mContext.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    /**
     * 设备唯一标示
     */
    public static String getAndroidID() {
        String uuid = "";
        try {
            //Android系统为开发者提供的用于标识手机设备的串号，且唯一性良好。(仅且设备是手机)注意全是0或者星号
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            String device_Id = telephonyManager.getDeviceId();
            //手机设备第一次启动随即产生的一个数字，如果系统改变，该号可能会改变(不适用与Android2.2的版本,当设备被wipe后该值会被重置)
            String android_Id = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);

            if (device_Id != null && -1 == device_Id.indexOf("*") && device_Id.replace("0", "").length() > 0) {
                return uuid = device_Id;
            }
            if (android_Id != null && !"9774d56d682e549c".equals(android_Id)) {
                uuid = android_Id;
            } else {
                uuid = UUID.randomUUID().toString();
            }
            return uuid;
        } catch (Exception e) {
            Log.e("App Error", "getAndroidID");
//            uuid = UUID.randomUUID().toString();
        }

        return uuid;
    }


    /**
     * 当前手机型号
     */
    public static Map<String, Object> getModel() {
        Map<String, Object> map = new HashMap<String, Object>();
        //手机型号
        map.put("MODEL", android.os.Build.MODEL);
        map.put("DEVICE", android.os.Build.DEVICE);
        map.put("PRODUCT", android.os.Build.PRODUCT);
        map.put("SDK", android.os.Build.VERSION.SDK);
        //系统版本
        map.put("RELEASE", android.os.Build.VERSION.RELEASE);
        return map;
    }

    /**
     * 判断当前设备是手机还是平板,App for Android
     *
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isTabletDevice() {
        try {
            return (mContext.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        } catch (Exception e) {
            return false;
        }
    }


}
