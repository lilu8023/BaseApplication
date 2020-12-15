package com.lilu.base.utils.permission;


import android.content.Context;


/**
 * Description:
 * 请求权限
 * @author lilu
 * @date 2019/12/5
 * I Am The King of This World.
 */
public interface IPermission {


    /**
     * 请求一个权限，不跳转到设置界面
     * @param context 上下文
     * @param callBack 请求结果回调
     * @param permissions 请求的权限名
     */
    void requestPermission(Context context, String msg, PermissionCallBack callBack, String... permissions);

    /**
     * 请求一个权限，拒绝后跳转到设置界面
     * @param context 上下文
     * @param callBack 请求结果回调
     * @param permissions 请求的权限名
     */
    void requestPermission(Context context, String msg, PermissionCallBack callBack, int resultCode, String... permissions);

}
