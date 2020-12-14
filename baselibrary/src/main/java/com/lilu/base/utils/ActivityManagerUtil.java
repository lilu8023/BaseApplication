package com.lilu.base.utils;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

/**
 * Description:
 * activity管理器
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class ActivityManagerUtil {

    // =====================
    // = Activity 管理控制 =
    // =====================

    // ActivityManagerUtil 实例
    private static final ActivityManagerUtil INSTANCE = new ActivityManagerUtil();

    /**
     * 获取 ActivityManagerUtil 管理实例
     * @return {@link ActivityManagerUtil}
     */
    public static ActivityManagerUtil getManager() {
        return INSTANCE;
    }

    // ===================
    // = Activity 栈处理 =
    // ===================

    // Activity 栈 ( 后进先出 )
    private final Stack<Activity> mActivityStacks = new Stack<>();

    /**
     * 获取 Activity 栈
     * @return {@link Stack<Activity>}
     */
    public Stack<Activity> getActivityStacks() {
        return mActivityStacks;
    }

    /**
     * 添加 Activity
     * @param activity {@link Activity}
     * @return {@link ActivityManagerUtil}
     */
    public ActivityManagerUtil addActivity(final Activity activity) {
        if (activity != null) {
            synchronized (mActivityStacks) {
                if (mActivityStacks.contains(activity)) {
                    return this;
                }
                mActivityStacks.add(activity);
            }
        }
        return this;
    }

    /**
     * 移除 Activity
     * @param activity {@link Activity}
     * @return {@link ActivityManagerUtil}
     */
    public ActivityManagerUtil removeActivity(final Activity activity) {
        if (activity != null) {
            synchronized (mActivityStacks) {
                int index = mActivityStacks.indexOf(activity);
                if (index == -1) {
                    return this;
                }
                try {
                    mActivityStacks.remove(index);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    /**
     * 移除多个 Activity
     * @param activitys Activity[]
     * @return {@link ActivityManagerUtil}
     */
    public ActivityManagerUtil removeActivity(final Activity... activitys) {
        if (activitys != null && activitys.length != 0) {
            for (int i = 0, len = activitys.length; i < len; i++) {
                removeActivity(activitys[i]);
            }
        }
        return this;
    }

    /**
     * 获取最后一个 ( 当前 ) Activity
     * @return 最后一个 ( 当前 ) {@link Activity}
     */
    public Activity currentActivity() {
        return mActivityStacks.lastElement();
    }

    /**
     * 关闭最后一个 ( 当前 ) Activity
     * @return {@link ActivityManagerUtil}
     */
    public ActivityManagerUtil finishActivity() {
        return finishActivity(mActivityStacks.lastElement());
    }

    /**
     * 检测是否包含指定的 Activity
     * @param clazzs Class(Activity)[]
     * @return {@code true} yes, {@code false} no
     */
    public boolean existActivities(final Class<?>... clazzs) {
        if (clazzs != null && clazzs.length != 0) {
            synchronized (mActivityStacks) {
                // 保存新的堆栈, 防止出现同步问题
                Stack<Activity> stack = new Stack<>();
                stack.addAll(mActivityStacks);
                try {
                    // 进行遍历判断
                    Iterator<Activity> iterator = stack.iterator();
                    while (iterator.hasNext()) {
                        Activity activity = iterator.next();
                        if (activity != null && !activity.isFinishing()) {
                            for (int i = 0, len = clazzs.length; i < len; i++) {
                                if (clazzs[i] != null && activity.getClass().getName().equals(clazzs[i].getName())) {
                                    return true;
                                }
                            }
                        }
                    }
                } finally {
                    // 移除数据, 并且清空内存
                    stack.clear();
                    stack = null;
                }
            }
        }
        return false;
    }

    /**
     * 关闭指定 Activity
     * @param activity {@link Activity}
     * @return {@link ActivityManagerUtil}
     */
    public ActivityManagerUtil finishActivity(final Activity activity) {
        // 先移除 Activity
        removeActivity(activity);
        // Activity 不为 null, 并且属于未销毁状态
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
        return this;
    }

    /**
     * 关闭多个 Activity
     * @param activitys Activity[]
     * @return {@link ActivityManagerUtil}
     */
    public ActivityManagerUtil finishActivity(final Activity... activitys) {
        if (activitys != null && activitys.length != 0) {
            for (int i = 0, len = activitys.length; i < len; i++) {
                finishActivity(activitys[i]);
            }
        }
        return this;
    }

    /**
     * 关闭指定类名 Activity
     * @param clazz Activity.class
     * @return {@link ActivityManagerUtil}
     */
    public ActivityManagerUtil finishActivity(final Class<?> clazz) {
        if (clazz != null) {
            synchronized (mActivityStacks) {
                // 保存新的堆栈, 防止出现同步问题
                Stack<Activity> stack = new Stack<>();
                stack.addAll(mActivityStacks);
                // 清空全部, 便于后续操作处理
                mActivityStacks.clear();
                // 进行遍历移除
                Iterator<Activity> iterator = stack.iterator();
                while (iterator.hasNext()) {
                    Activity activity = iterator.next();
                    // 判断是否想要关闭的 Activity
                    if (activity != null) {
                        if (activity.getClass() == clazz) {
                            // 如果 Activity 没有 finish 则进行 finish
                            if (!activity.isFinishing()) {
                                activity.finish();
                            }
                            // 删除对应的 Item
                            iterator.remove();
                        }
                    } else {
                        // 删除对应的 Item
                        iterator.remove();
                    }
                }
                // 把不符合条件的保存回去
                mActivityStacks.addAll(stack);
                // 移除数据, 并且清空内存
                stack.clear();
                stack = null;
            }
        }
        return this;
    }

    /**
     * 结束多个类名 Activity
     * @param clazzs Class(Activity)[]
     * @return {@link ActivityManagerUtil}
     */
    public ActivityManagerUtil finishActivity(final Class<?>... clazzs) {
        if (clazzs != null && clazzs.length != 0) {
            synchronized (mActivityStacks) {
                // 保存新的堆栈, 防止出现同步问题
                Stack<Activity> stack = new Stack<>();
                stack.addAll(mActivityStacks);
                // 清空全部, 便于后续操作处理
                mActivityStacks.clear();
                // 判断是否销毁
                boolean isRemove;
                // 进行遍历移除
                Iterator<Activity> iterator = stack.iterator();
                while (iterator.hasNext()) {
                    Activity activity = iterator.next();
                    // 判断是否想要关闭的 Activity
                    if (activity != null) {
                        // 默认不需要销毁
                        isRemove = false;
                        // 循环判断
                        for (int i = 0, len = clazzs.length; i < len; i++) {
                            // 判断是否相同
                            if (activity.getClass() == clazzs[i]) {
                                isRemove = true;
                                break;
                            }
                        }
                        // 判断是否销毁
                        if (isRemove) {
                            // 如果 Activity 没有 finish 则进行 finish
                            if (!activity.isFinishing()) {
                                activity.finish();
                            }
                            // 删除对应的 Item
                            iterator.remove();
                        }
                    } else {
                        // 删除对应的 Item
                        iterator.remove();
                    }
                }
                // 把不符合条件的保存回去
                mActivityStacks.addAll(stack);
                // 移除数据, 并且清空内存
                stack.clear();
                stack = null;
            }
        }
        return this;
    }

    /**
     * 结束全部 Activity 除忽略的 Activity 外
     * @param clazz Activity.class
     * @return {@link ActivityManagerUtil}
     */
    public ActivityManagerUtil finishAllActivityToIgnore(final Class<?> clazz) {
        if (clazz != null) {
            synchronized (mActivityStacks) {
                // 保存新的堆栈, 防止出现同步问题
                Stack<Activity> stack = new Stack<>();
                stack.addAll(mActivityStacks);
                // 清空全部, 便于后续操作处理
                mActivityStacks.clear();
                // 进行遍历移除
                Iterator<Activity> iterator = stack.iterator();
                while (iterator.hasNext()) {
                    Activity activity = iterator.next();
                    // 判断是否想要关闭的 Activity
                    if (activity != null) {
                        if (!(activity.getClass() == clazz)) {
                            // 如果 Activity 没有 finish 则进行 finish
                            if (!activity.isFinishing()) {
                                activity.finish();
                            }
                            // 删除对应的 Item
                            iterator.remove();
                        }
                    } else {
                        // 删除对应的 Item
                        iterator.remove();
                    }
                }
                // 把不符合条件的保存回去
                mActivityStacks.addAll(stack);
                // 移除数据, 并且清空内存
                stack.clear();
                stack = null;
            }
        }
        return this;
    }

    /**
     * 结束全部 Activity 除忽略的 Activity 外
     * @param clazzs Class(Activity)[]
     * @return {@link ActivityManagerUtil}
     */
    public ActivityManagerUtil finishAllActivityToIgnore(final Class<?>... clazzs) {
        if (clazzs != null && clazzs.length != 0) {
            synchronized (mActivityStacks) {
                // 保存新的堆栈, 防止出现同步问题
                Stack<Activity> stack = new Stack<>();
                stack.addAll(mActivityStacks);
                // 清空全部, 便于后续操作处理
                mActivityStacks.clear();
                // 判断是否销毁
                boolean isRemove;
                // 进行遍历移除
                Iterator<Activity> iterator = stack.iterator();
                while (iterator.hasNext()) {
                    Activity activity = iterator.next();
                    // 判断是否想要关闭的 Activity
                    if (activity != null) {
                        // 默认需要销毁
                        isRemove = true;
                        // 循环判断
                        for (int i = 0, len = clazzs.length; i < len; i++) {
                            // 判断是否相同
                            if (activity.getClass() == clazzs[i]) {
                                isRemove = false;
                                break;
                            }
                        }
                        // 判断是否销毁
                        if (isRemove) {
                            // 如果 Activity 没有 finish 则进行 finish
                            if (!activity.isFinishing()) {
                                activity.finish();
                            }
                            // 删除对应的 Item
                            iterator.remove();
                        }
                    } else {
                        // 删除对应的 Item
                        iterator.remove();
                    }
                }
                // 把不符合条件的保存回去
                mActivityStacks.addAll(stack);
                // 移除数据, 并且清空内存
                stack.clear();
                stack = null;
            }
        }
        return this;
    }

    /**
     * 结束所有 Activity
     * @return {@link ActivityManagerUtil}
     */
    public ActivityManagerUtil finishAllActivity() {
        synchronized (mActivityStacks) {
            // 保存新的堆栈, 防止出现同步问题
            Stack<Activity> stack = new Stack<>();
            stack.addAll(mActivityStacks);
            // 清空全部, 便于后续操作处理
            mActivityStacks.clear();
            // 进行遍历移除
            Iterator<Activity> iterator = stack.iterator();
            while (iterator.hasNext()) {
                Activity activity = iterator.next();
                if (activity != null && !activity.isFinishing()) {
                    activity.finish();
                    // 删除对应的 Item
                    iterator.remove();
                }
            }
            // 移除数据, 并且清空内存
            stack.clear();
            stack = null;
        }
        return this;
    }

    // =

    /**
     * 退出应用程序
     * @return {@link ActivityManagerUtil}
     */
    public ActivityManagerUtil exitApplication() {
        try {
            finishAllActivity();
            // 退出 JVM (Java 虚拟机 ) 释放所占内存资源, 0 表示正常退出、非 0 的都为异常退出
            System.exit(0);
            // 从操作系统中结束掉当前程序的进程
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
            // =
            System.exit(-1);
        }
        return this;
    }

}
