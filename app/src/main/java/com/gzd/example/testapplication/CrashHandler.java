package com.gzd.example.testapplication;

import android.content.Context;
import android.os.Environment;
import android.os.Process;

import java.io.IOException;

/**
 * Created by gzd on 2019/2/18 0018
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler mCrashHandler =  new CrashHandler();
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/CrashTest/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;

    public static CrashHandler getInstance(){
        return mCrashHandler;
    }

    public void init(Context context){
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }
    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        try{
            dumpExceptionToSDCard(ex);
            uploadExceptionToServer();
        }catch (IOException e){
            e.printStackTrace();
        }
        //如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
        if (mDefaultCrashHandler != null){
            mDefaultCrashHandler.uncaughtException(t,ex);
        }else {
            Process.killProcess(Process.myPid());
        }
    }

    private void dumpExceptionToSDCard(Throwable ex) throws IOException {
        //伪代码 本方法用于实现将错误信息存储到SD卡中
    }
    private void uploadExceptionToServer() {
        //伪代码 本方法用于将错误信息上传至服务
    }
}
