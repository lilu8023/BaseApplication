package com.lilu.base.update.proxy.impl;

import android.text.TextUtils;

import com.lilu.base.update.VersionHelper;
import com.lilu.base.update.entity.NewVersionEntity;
import com.lilu.base.update.entity.UpdateEntity;
import com.lilu.base.update.proxy.IUpdateChecker;
import com.lilu.base.update.proxy.IUpdateHttpService;
import com.lilu.base.update.proxy.IUpdateProxy;
import com.lilu.base.update.service.DownloadService;
import com.lilu.base.update.utils.UpdateUtils;
import com.lilu.base.utils.StringUtils;

import androidx.annotation.NonNull;

import static com.lilu.base.update.entity.UpdateError.ERROR.CHECK_APK_CACHE_DIR_EMPTY;
import static com.lilu.base.update.entity.UpdateError.ERROR.CHECK_IGNORED_VERSION;
import static com.lilu.base.update.entity.UpdateError.ERROR.CHECK_JSON_EMPTY;
import static com.lilu.base.update.entity.UpdateError.ERROR.CHECK_NET_REQUEST;
import static com.lilu.base.update.entity.UpdateError.ERROR.CHECK_NO_NEW_VERSION;
import static com.lilu.base.update.entity.UpdateError.ERROR.CHECK_PARSE;
import static com.lilu.base.update.entity.UpdateError.ERROR.CHECK_UPDATING;


/**
 * Description:
 * 默认版本更新检查器
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class DefaultUpdateChecker implements IUpdateChecker {

    @Override
    public void onBeforeCheck(@NonNull IUpdateProxy updateProxy) {
        //判断是否已经在下载更新或者更新窗口是否已经弹出了
        if (DownloadService.isRunning() || VersionHelper.isShowUpdatePrompter()) {
            onAfterCheck();
            VersionHelper.onUpdateError(CHECK_UPDATING);
        }else{
            checkVersion(updateProxy);
        }
    }

    @Override
    public void checkVersion(final @NonNull IUpdateProxy updateProxy) {
        //去获取是否有最新版本
        updateProxy.getIUpdateHttpService()
                .getNewVersion(new IUpdateHttpService.Callback() {
                    @Override
                    public void onSuccess(NewVersionEntity version) {
                        onAfterCheck();
                        //去解析服务器返回的数据，是否有最新版本
                        processCheckResult(version, updateProxy);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        //表示查询是否有最新版本接口出错
                        onAfterCheck();
                        VersionHelper.onUpdateError(CHECK_NET_REQUEST, throwable.getMessage());
                    }
                });

    }

    @Override
    public void onAfterCheck() {

    }


    @Override
    public void processCheckResult(final @NonNull NewVersionEntity version, final @NonNull IUpdateProxy updateProxy) {
        updateProxy.entityParse(version);


    }

    @Override
    public void noNewVersion(Throwable throwable) {
        VersionHelper.onUpdateError(CHECK_NO_NEW_VERSION, throwable != null ? throwable.getMessage() : null);
    }
}
