package water.retrofittest;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;

import java.io.File;
import java.util.List;


/**
 * Created by xiongmc on 2015/12/22.
 */
public class App extends Application {
    // 获取到主线程的上下文
    public static App mContext;
    // 主线程的id
    private static int mMainThreadId;
    // 获取到主线程
    private static Thread mMainThread;
    // 获取到主线程的handler
    private static Handler mMainThradHandler;
    // 获取到主线程的looper
    private static Looper mMainThradLooper;

    //用于网络请求是否缓存网络请求的数据
    public static boolean isChach = false;

    public static boolean getIsChach() {
        return isChach;
    }

    public static void setChach(boolean isChach) {
        App.isChach = isChach;
    }



    /**
     * 全局Application，方便调用
     */
    public static App application;
    public static SharedPreferences SP;
    public static SharedPreferences.Editor EDIT;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;


        SP = getSharedPreferences("config", MODE_PRIVATE);
        EDIT = SP.edit();
        this.mContext = this;
        this.mMainThreadId = Process.myTid();
        this.mMainThread = Thread.currentThread();
        this.mMainThradHandler = new Handler();
        this.mMainThradLooper = getMainLooper();


    }

    private boolean shouldInit() {

        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }




    //    public static CustomProgressDialog getDialog(Context context){
//         CustomProgressDialog  dialog=CustomProgressDialog.createDialog(context);
//        return dialog;
//    }
    public static App getApplication() {
        return mContext;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static Handler getMainThreadHandler() {
        return mMainThradHandler;
    }

    public static Looper getMainThreadLooper() {
        return mMainThradLooper;
    }

    /** * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory */
    public static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
}
