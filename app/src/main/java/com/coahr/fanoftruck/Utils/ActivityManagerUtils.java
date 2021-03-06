package com.coahr.fanoftruck.Utils;

import java.util.Stack;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 13:32
 * activity堆栈式管理
 */
public class ActivityManagerUtils {
    private static final String TAG = ActivityManagerUtils.class.getSimpleName();
    private static Stack<AppCompatActivity> activityStack;
    private static ActivityManagerUtils instance;

    private ActivityManagerUtils() {
        if (activityStack == null) {
            activityStack = new Stack<AppCompatActivity>();
        }
    }

    /**
     * 单一实例
     */
    public static ActivityManagerUtils getInstance() {
        if (instance == null) {
            instance = new ActivityManagerUtils();
        }
        return instance;
    }

    /**
     * 获取指定的Activity
     *
     * @author kymjs
     */
    public static AppCompatActivity getActivity(Class<?> cls) {
        if (activityStack != null)
            for (AppCompatActivity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        return null;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(AppCompatActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前栈顶Activity（堆栈中最后一个压入的）
     */
    public AppCompatActivity currentActivity() {
        AppCompatActivity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        activityStack.lastElement().finish();
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(AppCompatActivity activity) {
        if (activityStack != null && activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定的Activity
     */
    public void removeActivity(AppCompatActivity activity) {
        if (activityStack != null && activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (AppCompatActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (AppCompatActivity activity : activityStack) {
            if (null != activity)
                activity.finish();
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            finishAllActivity();
//            从操作系统中结束掉当前程序的进程
            android.os.Process.killProcess(android.os.Process.myPid());
            //退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
