/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lilu.base.updata.proxy.impl;

import android.text.TextUtils;

import com.lilu.base.updata.VersionHelper;
import com.lilu.base.updata.entity.UpdateEntity;
import com.lilu.base.updata.listener.IUpdateParseCallback;
import com.lilu.base.updata.proxy.IUpdateChecker;
import com.lilu.base.updata.proxy.IUpdateHttpService;
import com.lilu.base.updata.proxy.IUpdateProxy;
import com.lilu.base.updata.service.DownloadService;
import com.lilu.base.updata.utils.UpdateUtils;

import androidx.annotation.NonNull;


import java.util.Map;

import static com.lilu.base.updata.entity.UpdateError.ERROR.CHECK_JSON_EMPTY;
import static com.lilu.base.updata.entity.UpdateError.ERROR.CHECK_NET_REQUEST;
import static com.lilu.base.updata.entity.UpdateError.ERROR.CHECK_NO_NEW_VERSION;
import static com.lilu.base.updata.entity.UpdateError.ERROR.CHECK_PARSE;
import static com.lilu.base.updata.entity.UpdateError.ERROR.CHECK_UPDATING;


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
    public void checkVersion(boolean isGet, @NonNull String url, @NonNull Map<String, Object> params, final @NonNull IUpdateProxy updateProxy) {
        if (DownloadService.isRunning() || VersionHelper.isShowUpdatePrompter()) {
            updateProxy.onAfterCheck();
            VersionHelper.onUpdateError(CHECK_UPDATING);
            return;
        }

        if (isGet) {
            updateProxy.getIUpdateHttpService().asyncGet(url, params, new IUpdateHttpService.Callback() {
                @Override
                public void onSuccess(String result) {
                    onCheckSuccess(result, updateProxy);
                }

                @Override
                public void onError(Throwable error) {
                    onCheckError(updateProxy, error);
                }
            });
        } else {
            updateProxy.getIUpdateHttpService().asyncPost(url, params, new IUpdateHttpService.Callback() {
                @Override
                public void onSuccess(String result) {
                    onCheckSuccess(result, updateProxy);
                }

                @Override
                public void onError(Throwable error) {
                    onCheckError(updateProxy, error);
                }
            });
        }
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
            if (updateProxy.isAsyncParser()) {
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
            } else {
                //同步解析
                UpdateUtils.processUpdateEntity(updateProxy.parseJson(result), result, updateProxy);
            }
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
