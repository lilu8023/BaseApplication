package com.lilu.base.utils.permission.and;

import android.content.Context;


import com.lilu.base.utils.permission.IPermission;
import com.lilu.base.utils.permission.PermissionCallBack;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.util.List;

/**
 * Description:
 * AndPermission 请求权限工具类
 * @author lilu
 * @date 2019/12/5
 * I Am The King of This World.
 */
public class AndPermissionUtil implements IPermission {

    @Override
    public void requestPermission(Context context, String msg, final PermissionCallBack callBack, String... permissions) {

        AndPermission.with(context)
                .runtime()
                .permission(permissions)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //权限同意
                        if(callBack != null){
                            callBack.onGranted();
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //权限拒绝
                        if(callBack != null){
                            callBack.onDenied();
                        }
                    }
                })
                .rationale(new Rationale<List<String>>() {
                    @Override
                    public void showRationale(Context context, List<String> data, RequestExecutor executor) {
                        //进行弹窗拦截  使用msg参数
                        executor.execute();
                    }
                }).start();
    }

    @Override
    public void requestPermission(final Context context, String msg, final PermissionCallBack callBack, final int resultCode, String... permissions) {
        AndPermission.with(context)
                .runtime()
                .permission(permissions)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //权限同意
                        if(callBack != null){
                            callBack.onGranted();
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //权限拒绝
                        if(callBack != null){
                            callBack.onDenied();
                        }
                        //跳转到系统设置界面去  需要请求类中做回调处理
                        if(resultCode != 0){
                            AndPermission.with(context)
                                    .runtime()
                                    .setting()
                                    .start(resultCode);
                        }
                    }
                })
                .rationale(new Rationale<List<String>>() {
                    @Override
                    public void showRationale(Context context, List<String> data, RequestExecutor executor) {
                        //进行弹窗拦截  使用msg参数
                        executor.execute();
                    }
                }).start();
    }
}
