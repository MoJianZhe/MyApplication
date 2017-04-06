package com.dyoon.myapplication;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 该类主要是收集程序的崩溃信息
 * 以后实现把崩溃信息发送到服务器上去
 * Created by jun on 2017/3/23.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private static final boolean DEBUG=true;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

/*
    public static final String PATH=Environment.getExternalStorageDirectory()
        + "/PlmCrash/log/";
*/
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";
    private static  CrashHandler instance=new CrashHandler();
    public  static  CrashHandler getInstance() {
        return instance;
    }
    private CrashHandler() {

    }

    public void init(Context context) {
        mContext=context.getApplicationContext();
        mDefaultExceptionHandler=Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            dumpExceptionToSDCard(e);
        } catch (IOException e1) {
            e1.printStackTrace();
            Log.i(TAG, "没有写入文件 ");
        }
        uploadExceptionToServer();
        e.printStackTrace();
        //如果系统提供了默认的异常处理器，则交给系统，否则由自己去结束程序
        if (mDefaultExceptionHandler != null) {
            Log.i(TAG, "uncaughtException: "+"由系统去处理");
            mDefaultExceptionHandler.uncaughtException(t, e);
        } else {
            Process.killProcess(Process.myPid());
        }

    }


    private void dumpExceptionToSDCard(Throwable ex) throws IOException {
        String PATH=mContext.getFilesDir().toString()+"/PlmCrash/log/";
        Log.i(TAG, "dumpExceptionToSDCard: "+PATH);
        //如果sc卡不存在或无法使用，则无法把异常信息写入sd卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                Log.w(TAG, "dumpExceptionToSDCard: sdcard not exit,skip dump exception");
                return;
            }
        }


        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Long current=System.currentTimeMillis();
        String time = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(PATH+FILE_NAME +time+FILE_NAME_SUFFIX);
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            dumPhoneInfo(pw);
            pw.println();
            ex.printStackTrace(pw);
            pw.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "dumpExceptionToSDCard: dump crash info failed");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void dumPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager pm=mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),PackageManager
        .GET_ACTIVITIES);
        pw.print("os version:");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        //打印收集制造 商
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);

        //收集型号
        pw.print("model: ");
        pw.println(Build.MODEL);

        //cpu
        pw.print("cpu abi:");
        pw.println(Build.CPU_ABI);
    }

    private void uploadExceptionToServer() {

    }

}
