package com.lilu.base.update.proxy.impl;

import android.text.TextUtils;

import com.lilu.base.update.VersionHelper;
import com.lilu.base.update.entity.UpdateEntity;
import com.lilu.base.update.listener.IUpdateParseCallback;
import com.lilu.base.update.proxy.IUpdateChecker;
import com.lilu.base.update.proxy.IUpdateHttpService;
import com.lilu.base.update.proxy.IUpdateProxy;
import com.lilu.base.update.service.DownloadService;
import com.lilu.base.update.utils.UpdateUtils;

import androidx.annotation.NonNull;


import static com.lilu.base.update.entity.UpdateError.ERROR.CHECK_JSON_EMPTY;
import static com.lilu.base.update.entity.UpdateError.ERROR.CHECK_NET_REQUEST;
import static com.lilu.base.update.entity.UpdateError.ERROR.CHECK_NO_NEW_VERSION;
import static com.lilu.base.update.entity.UpdateError.ERROR.CHECK_PARSE;
import static com.lilu.base.update.entity.UpdateError.ERROR.CHECK_UPDATING;


/**
 * 默认版本更新检查者
 *
 * @author xuexiang
 * @since 2018/7/2 下午10:21
 */
public class DefaultUpdateChecker implements IUpdateChecker {

    @Override
    public void onBeforeCheck() {

    }

    @Override
    public void checkVersion(final @NonNull IUpdateProxy updateProxy) {
        if (DownloadService.isRunning() || VersionHelper.isShowUpdatePrompter()) {
            updateProxy.onAfterCheck();
            VersionHelper.onUpdateError(CHECK_UPDATING);
            return;
        }

        updateProxy.getIUpdateHttpService().getNewVersion(new IUpdateHttpService.Callback() {
            @Override
            public void onSuccess(String result) {
                onCheckSuccess(result, updateProxy);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }

    @Override
    public void onAfterCheck() {

    }

    /**
     * 查询成功
     *
     * @param result      查询结果
     * @param updateProxy 更新代理
     */
    private void onCheckSuccess(String result, @NonNull IUpdateProxy updateProxy) {
        updateProxy.onAfterCheck();
        if (!TextUtils.isEmpty(result)) {
            processCheckResult(result, updateProxy);
        } else {
            VersionHelper.onUpdateError(CHECK_JSON_EMPTY);
        }
    }

    /**
     * 查询失败
     *
     * @param updateProxy 更新代理
     * @param error       错误
     */
    private void onCheckError(@NonNull IUpdateProxy updateProxy, Throwable error) {
        updateProxy.onAfterCheck();
        VersionHelper.onUpdateError(CHECK_NET_REQUEST, error.getMessage());
    }

    @Override
    public void processCheckResult(final @NonNull String result, final @NonNull IUpdateProxy updateProxy) {
        try {
            //异步解析
            updateProxy.parseJson(result, new IUpdateParseCallback() {
                @Override
                public void onParseResult(UpdateEntity updateEntity) {
                    try {
                        UpdateUtils.processUpdateEntity(updateEntity, result, updateProxy);
                    } catch (Exception e) {
                        e.printStackTrace();
                        VersionHelper.onUpdateError(CHECK_PARSE, e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            VersionHelper.onUpdateError(CHECK_PARSE, e.getMessage());
        }
    }

    @Override
    public void noNewVersion(Throwable throwable) {
        VersionHelper.onUpdateError(CHECK_NO_NEW_VERSION, throwable != null ? throwable.getMessage() : null);
    }
}
