package com.lilu.base.utils;

import android.app.Application;
import android.content.Context;

/**
 * Description:
 *
 * @author lilu on 2020/12/17
 * No one knows this better than me
 */
public class ApplicationUtils {

    private static Application sContext;

    private static void testInitialize() {
        if (sContext == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 init() 初始化！");
        }
    }

    public static void init(Context context){
        sContext = (Application) context;
    }

    public static Context getContext(){
        testInitialize();
        return sContext;
    }
}
