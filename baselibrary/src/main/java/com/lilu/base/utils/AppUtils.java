package com.lilu.base.utils;

import android.app.Application;
import android.content.Context;

/**
 * Description:
 *
 * @author lilu on 2020/12/25
 * No one knows this better than me
 */
public class AppUtils {

    /**
     * 全局上下文
     */
    private static Application sContext;


    /**
     * 初始化工具【注册activity的生命回调】
     */
    public static void init(Application application) {
        sContext = application;
    }

    /**
     * 获取全局上下文
     *
     * @return 全局上下文
     */
    public static Context getContext() {
        testInitialize();
        return sContext;
    }

    private static void testInitialize() {
        if (sContext == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 XUtil.init() 初始化！");
        }
    }
}
