package com.lilu.base.update;

import android.content.Context;

import com.lilu.base.update.entity.DownloadEntity;
import com.lilu.base.update.entity.UpdateError;
import com.lilu.base.update.listener.OnInstallListener;
import com.lilu.base.update.listener.OnUpdateFailureListener;
import com.lilu.base.update.listener.impl.DefaultInstallListener;
import com.lilu.base.update.listener.impl.DefaultUpdateFailureListener;
import com.lilu.base.update.proxy.IUpdateChecker;
import com.lilu.base.update.proxy.IUpdateDownloader;
import com.lilu.base.update.proxy.IUpdateHttpService;
import com.lilu.base.update.proxy.IUpdateParser;
import com.lilu.base.update.proxy.IUpdatePrompter;
import com.lilu.base.update.proxy.impl.DefaultFileEncryption;
import com.lilu.base.utils.logger.Logger;

import androidx.annotation.NonNull;


import java.io.File;

import static com.lilu.base.update.entity.UpdateError.ERROR.INSTALL_FAILED;


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
        if (VersionUpdate.get().mIFileEncryption == null) {
            VersionUpdate.get().mIFileEncryption = new DefaultFileEncryption();
        }
        return VersionUpdate.get().mIFileEncryption.encryptFile(file);
    }

    /**
     * 验证文件是否有效（加密是否一致）
     *
     * @param encrypt 加密值，不能为空
     * @param file    需要校验的文件
     * @return 文件是否有效
     */
    public static boolean isFileValid(String encrypt, File file) {
        if (VersionUpdate.get().mIFileEncryption == null) {
            VersionUpdate.get().mIFileEncryption = new DefaultFileEncryption();
        }
        return VersionUpdate.get().mIFileEncryption.isFileValid(encrypt, file);
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
     */
    public static void onUpdateError(int errorCode) {
        onUpdateError(new UpdateError(errorCode));
    }

    /**
     * 更新出现错误
     */
    public static void onUpdateError(int errorCode, String message) {
        onUpdateError(new UpdateError(errorCode, message));
    }

    /**
     * 更新出现错误
     */
    public static void onUpdateError(@NonNull UpdateError updateError) {
        if (VersionUpdate.get().mOnUpdateFailureListener == null) {
            VersionUpdate.get().mOnUpdateFailureListener = new DefaultUpdateFailureListener();
        }
        VersionUpdate.get().mOnUpdateFailureListener.onFailure(updateError);
    }

}
