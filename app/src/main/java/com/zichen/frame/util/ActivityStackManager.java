package com.zichen.frame.util;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

/**
 * author : lyw
 * time : 2017/7/28
 * desc : Activity管理栈工具类
 * version: 1.0
 */
public class ActivityStackManager {

    /**
     * Activity栈
     */
    private Stack<WeakReference<Activity>> mActivityStack;

    private volatile static ActivityStackManager activityStackManager = new ActivityStackManager();

    private ActivityStackManager() {
    }

    public static ActivityStackManager getInstance() {
//        if (activityStackManager == null) {
//            synchronized (ActivityStackManager.class) {
//                if (activityStackManager == null) {
//                    activityStackManager = new ActivityStackManager();
//                }
//            }
//        }
        return activityStackManager;
    }


    /***
     * 栈中Activity数量
     *
     * @return Activity的数
     */
    public int stackSize() {
        return mActivityStack.size();
    }

    /***
     * 获得Activity栈
     *
     * @return Activity栈
     */
    public Stack<WeakReference<Activity>> getStack() {
        return mActivityStack;
    }


    /**
     * 添加Activity到堆栈
     */
    public void addActivity(WeakReference<Activity> activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(activity);
        getStackListObject();
    }

    /**
     * 删除ac
     *
     * @param activity 弱引用的ac
     */
    public void removeActivity(WeakReference<Activity> activity) {
        if (mActivityStack != null) {
            if (mActivityStack.contains(activity)) {
                mActivityStack.remove(activity);
            }
            getStackListObject();
        }
    }

    /***
     * 获取栈顶Activity（堆栈中最后一个压入的）
     *
     * @return Activity
     */
    public Activity getTopActivity() {
        return mActivityStack.lastElement().get();
    }

    /***
     * 通过class获取Activity
     *
     * @param cls
     * @return Activity
     */
    public Activity getActivityByClass(Class<?> cls) {
        Iterator<WeakReference<Activity>> iterator = mActivityStack.iterator();
        WeakReference<Activity> activity;
        while (iterator.hasNext()) {
            activity = iterator.next();
            if (activity.get().getClass().equals(cls)) {
                return activity.get();
            }
        }

        return null;
    }

    /**
     * 结束栈顶Activity
     */
    public void killTopActivity() {
        try {
            WeakReference<Activity> activity = mActivityStack.lastElement();
            killActivity(activity);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
    }

    /***
     * 结束指定的Activity
     *
     * @param activity
     */
    public void killActivity(WeakReference<Activity> activity) {
        killActivity(activity.get().getClass());
    }

    /***
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public void killActivity(Class<?> cls) {
        try {
            ListIterator<WeakReference<Activity>> listIterator = mActivityStack.listIterator();

            while (listIterator.hasNext()) {
                Activity activity = listIterator.next().get();
                if (activity == null) {
                    listIterator.remove();
                    continue;
                }
                LogUtils.d(activity.getClass().getSimpleName());
                if (activity.getClass() == cls) {
                    listIterator.remove();
                    activity.finish();
                    break;
                }
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
    }

    /**
     * 结束所有Activity
     */
    public void killAllActivity() {
        try {
            ListIterator<WeakReference<Activity>> listIterator = mActivityStack.listIterator();
            while (listIterator.hasNext()) {
                Activity activity = listIterator.next().get();
                if (activity != null) {
                    activity.finish();
                }
                listIterator.remove();
            }
            getStackListObject();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
    }

    /**
     * 结束除指定Activity外的所有Activity
     */
    public void killAllActivityExcept(Class<?> clazz) {
        try {
            ListIterator<WeakReference<Activity>> listIterator = mActivityStack.listIterator();
            while (listIterator.hasNext()) {
                Activity activity = listIterator.next().get();
                if (activity != null && activity.getClass() != clazz) {
                    activity.finish();
                }
                listIterator.remove();
            }
            getStackListObject();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApp() {
        killAllActivity();
//        Process.killProcess(Process.myPid());
//      or  System.exit(0);
    }

    /*获取栈内剩余activity*/
    public void getStackListObject() {
        if (mActivityStack != null && mActivityStack.size() > 0) {
            for (WeakReference<Activity> activity : mActivityStack) {
                if (activity.get() != null) {
//                    LogUtils.d("stack container ==" + activity.get().getClass().getName());
                }
            }
        } else {
            LogUtils.d("mActivityStack is null or size = 0");
        }
    }
}
