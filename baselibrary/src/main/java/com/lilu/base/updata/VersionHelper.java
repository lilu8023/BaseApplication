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

package com.lilu.base.updata;

import android.content.Context;

import com.lilu.base.updata.entity.DownloadEntity;
import com.lilu.base.updata.entity.UpdateError;
import com.lilu.base.updata.listener.OnInstallListener;
import com.lilu.base.updata.listener.OnUpdateFailureListener;
import com.lilu.base.updata.listener.impl.DefaultInstallListener;
import com.lilu.base.updata.listener.impl.DefaultUpdateFailureListener;
import com.lilu.base.updata.proxy.IUpdateChecker;
import com.lilu.base.updata.proxy.IUpdateDownloader;
import com.lilu.base.updata.proxy.IUpdateHttpService;
import com.lilu.base.updata.proxy.IUpdateParser;
import com.lilu.base.updata.proxy.IUpdatePrompter;
import com.lilu.base.updata.proxy.impl.DefaultFileEncryptor;
import com.lilu.base.utils.logger.Logger;

import androidx.annotation.NonNull;


import java.io.File;
import java.util.Map;

import static com.lilu.base.updata.entity.UpdateError.ERROR.INSTALL_FAILED;


/**
 * 内部版本更新参数的获取
 *
 * @author xuexiang
 * @since 2018/7/10 下午4:27
 */
public final class VersionHelper {

    /**
     * 标志当前更新提示是否已显示
     */
    private static boolean sIsShowUpdatePrompter = false;

    public static void setIsShowUpdatePrompter(boolean isShowUpdatePrompter) {
        VersionHelper.sIsShowUpdatePrompter = isShowUpdatePrompter;
    }

    public static boolean isShowUpdatePrompter() {
        return VersionHelper.sIsShowUpdatePrompter;
    }

    //===========================属性设置===================================//

    public static Map<String, Object> getParams() {
        return VersionUpdate.get().mParams;
    }

    public static IUpdateHttpService getIUpdateHttpService() {
        return VersionUpdate.get().mIUpdateHttpService;
    }

    public static IUpdateChecker getIUpdateChecker() {
        return VersionUpdate.get().mIUpdateChecker;
    }

    public static IUpdateParser getIUpdateParser() {
        return VersionUpdate.get().mIUpdateParser;
    }

    public static IUpdatePrompter getIUpdatePrompter() {
        return VersionUpdate.get().mIUpdatePrompter;
    }

    public static IUpdateDownloader getIUpdateDownLoader() {
        return VersionUpdate.get().mIUpdateDownloader;
    }

    public static boolean isGet() {
        return VersionUpdate.get().mIsGet;
    }

    public static boolean isWifiOnly() {
        return VersionUpdate.get().mIsWifiOnly;
    }

    public static boolean isAutoMode() {
        return VersionUpdate.get().mIsAutoMode;
    }

    public static String getApkCacheDir() {
        return VersionUpdate.get().mApkCacheDir;
    }

    //===========================文件加密===================================//

    /**
     * 加密文件
     *
     * @param file 需要加密的文件
     */
    public static String encryptFile(File file) {
        if (VersionUpdate.get().mIFileEncryptor == null) {
            VersionUpdate.get().mIFileEncryptor = new DefaultFileEncryptor();
        }
        return VersionUpdate.get().mIFileEncryptor.encryptFile(file);
    }

    /**
     * 验证文件是否有效（加密是否一致）
     *
     * @param encrypt 加密值，不能为空
     * @param file    需要校验的文件
     * @return 文件是否有效
     */
    public static boolean isFileValid(String encrypt, File file) {
        if (VersionUpdate.get().mIFileEncryptor == null) {
            VersionUpdate.get().mIFileEncryptor = new DefaultFileEncryptor();
        }
        return VersionUpdate.get().mIFileEncryptor.isFileValid(encrypt, file);
    }

    //===========================apk安装监听===================================//

    public static OnInstallListener getOnInstallListener() {
        return VersionUpdate.get().mOnInstallListener;
    }

    /**
     * 开始安装apk文件
     *
     * @param context 传activity可以获取安装的返回值，
     * @param apkFile apk文件
     */
    public static void startInstallApk(@NonNull Context context, @NonNull File apkFile) {
        startInstallApk(context, apkFile, new DownloadEntity());
    }

    /**
     * 开始安装apk文件
     *
     * @param context        传activity可以获取安装的返回值，
     * @param apkFile        apk文件
     * @param downloadEntity 文件下载信息
     */
    public static void startInstallApk(@NonNull Context context, @NonNull File apkFile, @NonNull DownloadEntity downloadEntity) {
        Logger.d("开始安装apk文件, 文件路径:" + apkFile.getAbsolutePath() + ", 下载信息:" + downloadEntity);
        if (onInstallApk(context, apkFile, downloadEntity)) {
            onApkInstallSuccess(); //静默安装的话，不会回调到这里
        } else {
            onUpdateError(INSTALL_FAILED);
        }
    }

    /**
     * 安装apk
     *
     * @param context        传activity可以获取安装的返回值，
     * @param apkFile        apk文件
     * @param downloadEntity 文件下载信息
     */
    private static boolean onInstallApk(Context context, File apkFile, DownloadEntity downloadEntity) {
        if (VersionUpdate.get().mOnInstallListener == null) {
            VersionUpdate.get().mOnInstallListener = new DefaultInstallListener();
        }
        return VersionUpdate.get().mOnInstallListener.onInstallApk(context, apkFile, downloadEntity);
    }

    /**
     * apk安装完毕
     */
    private static void onApkInstallSuccess() {
        if (VersionUpdate.get().mOnInstallListener == null) {
            VersionUpdate.get().mOnInstallListener = new DefaultInstallListener();
        }
        VersionUpdate.get().mOnInstallListener.onInstallApkSuccess();
    }

    //===========================更新出错===================================//

    public static OnUpdateFailureListener getOnUpdateFailureListener() {
        return VersionUpdate.get().mOnUpdateFailureListener;
    }

    /**
     * 更新出现错误
     *
     * @param errorCode
     */
    public static void onUpdateError(int errorCode) {
        onUpdateError(new UpdateError(errorCode));
    }

    /**
     * 更新出现错误
     *
     * @param errorCode
     * @param message
     */
    public static void onUpdateError(int errorCode, String message) {
        onUpdateError(new UpdateError(errorCode, message));
    }

    /**
     * 更新出现错误
     *
     * @param updateError
     */
    public static void onUpdateError(@NonNull UpdateError updateError) {
        if (VersionUpdate.get().mOnUpdateFailureListener == null) {
            VersionUpdate.get().mOnUpdateFailureListener = new DefaultUpdateFailureListener();
        }
        VersionUpdate.get().mOnUpdateFailureListener.onFailure(updateError);
    }

}
