package com.lilu.base.utils.permission;

/**
 * Description:
 * 权限请求回调
 * @author lilu
 * @date 2019/12/5
 * I Am The King of This World.
 */
public interface PermissionCallBack {

    /**
     * 请求同意
     */
    void onGranted();

    /**
     * 权限拒绝
     */
    void onDenied();


}
