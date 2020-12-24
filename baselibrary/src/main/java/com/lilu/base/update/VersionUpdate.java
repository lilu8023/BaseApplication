package com.lilu.base.update;

import android.app.Application;
import android.content.Context;

import com.lilu.base.update.entity.UpdateError;
import com.lilu.base.update.listener.OnInstallListener;
import com.lilu.base.update.listener.OnUpdateFailureListener;
import com.lilu.base.update.listener.impl.DefaultInstallListener;
import com.lilu.base.update.listener.impl.DefaultUpdateFailureListener;
import com.lilu.base.update.proxy.IFileEncryption;
import com.lilu.base.update.proxy.IUpdateChecker;
import com.lilu.base.update.proxy.IUpdateDownloader;
import com.lilu.base.update.proxy.IUpdateHttpService;
import com.lilu.base.update.proxy.IUpdateParser;
import com.lilu.base.update.proxy.IUpdatePrompter;
import com.lilu.base.update.proxy.impl.DefaultFileEncryption;
import com.lilu.base.update.proxy.impl.DefaultUpdateChecker;
import com.lilu.base.update.proxy.impl.DefaultUpdateDownloader;
import com.lilu.base.update.proxy.impl.DefaultUpdateHttpService;
import com.lilu.base.update.proxy.impl.DefaultUpdateParser;
import com.lilu.base.update.proxy.impl.DefaultUpdatePrompter;
import com.lilu.base.update.utils.ApkInstallUtils;
import com.lilu.base.utils.logger.Logger;

import androidx.annotation.NonNull;


/**
 * Description:
 * 版本更新
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */

public class VersionUpdate {

    private Application mContext;
    private static VersionUpdate sInstance;

    /**
     * 下载的apk文件缓存目录
     */
    String mApkCacheDir;
    //========全局更新实现接口==========//
    /**
     * 版本更新网络请求服务API
     */
    IUpdateHttpService mIUpdateHttpService;
    /**
     * 版本更新检查器【有默认】
     */
    IUpdateChecker mIUpdateChecker;
    /**
     * 版本更新解析器【有默认】
     */
    IUpdateParser mIUpdateParser;
    /**
     * 版本更新提示器【有默认】
     */
    IUpdatePrompter mIUpdatePrompter;
    /**
     * 版本更新下载器【有默认】
     */
    IUpdateDownloader mIUpdateDownloader;
    /**
     * 文件加密器【有默认】
     */
    IFileEncryption mIFileEncryption;
    /**
     * APK安装监听【有默认】
     */
    OnInstallListener mOnInstallListener;
    /**
     * 更新出错监听【有默认】
     */
    OnUpdateFailureListener mOnUpdateFailureListener;

    //===========================初始化===================================//

    private VersionUpdate() {

        mIUpdateHttpService = new DefaultUpdateHttpService();
        mIUpdateChecker = new DefaultUpdateChecker();
        mIUpdateParser = new DefaultUpdateParser();
        mIUpdateDownloader = new DefaultUpdateDownloader();
        mIUpdatePrompter = new DefaultUpdatePrompter();
        mIFileEncryption = new DefaultFileEncryption();
        mOnInstallListener = new DefaultInstallListener();
        mOnUpdateFailureListener = new DefaultUpdateFailureListener();
    }

    /**
     * 获取版本更新的入口
     *
     * @return 版本更新的入口
     */
    public static VersionUpdate get() {
        if (sInstance == null) {
            synchronized (VersionUpdate.class) {
                if (sInstance == null) {
                    sInstance = new VersionUpdate();
                }
            }
        }
        return sInstance;
    }

    /**
     * 初始化
     *
     */
    public void init(Application application) {
        mContext = application;
        UpdateError.init(mContext);
    }

    private Application getApplication() {
        testInitialize();
        return mContext;
    }

    private void testInitialize() {
        if (mContext == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 XUpdate.get().init() 初始化！");
        }
    }

    public static Context getContext() {
        return get().getApplication();
    }

    //===========================对外版本更新api===================================//

    /**
     * 获取版本更新构建者
     */
    public static UpdateManager.Builder newBuild(@NonNull Context context) {
        return new UpdateManager.Builder(context);
    }

    //===========================属性设置===================================//


    /**
     * 设置全局版本更新网络请求服务API
     */
    public VersionUpdate setIUpdateHttpService(@NonNull IUpdateHttpService updateHttpService) {
        Logger.d("设置全局更新网络请求服务:" + updateHttpService.getClass().getCanonicalName());
        mIUpdateHttpService = updateHttpService;
        return this;
    }

    /**
     * 设置全局版本更新检查
     */
    public VersionUpdate setIUpdateChecker(@NonNull IUpdateChecker updateChecker) {
        mIUpdateChecker = updateChecker;
        return this;
    }

    /**
     * 设置全局版本更新的解析器
     */
    public VersionUpdate setIUpdateParser(@NonNull IUpdateParser updateParser) {
        mIUpdateParser = updateParser;
        return this;
    }

    /**
     * 设置全局版本更新提示器
     */
    public VersionUpdate setIUpdatePrompter(IUpdatePrompter updatePrompter) {
        mIUpdatePrompter = updatePrompter;
        return this;
    }

    /**
     * 设置全局版本更新下载器
     */
    public VersionUpdate setIUpdateDownLoader(@NonNull IUpdateDownloader updateDownLoader) {
        mIUpdateDownloader = updateDownLoader;
        return this;
    }


    /**
     * 设置apk的缓存路径
     */
    public VersionUpdate setApkCacheDir(String apkCacheDir) {
        Logger.d("设置全局apk的缓存路径:" + apkCacheDir);
        mApkCacheDir = apkCacheDir;
        return this;
    }

    /**
     * 设置是否支持静默安装
     */
    public VersionUpdate supportSilentInstall(boolean supportSilentInstall) {
        ApkInstallUtils.setSupportSilentInstall(supportSilentInstall);
        return this;
    }


    //===========================apk安装监听===================================//


    /**
     * 设置文件加密器
     */
    public VersionUpdate setIFileEncryption(IFileEncryption fileEncryption) {
        mIFileEncryption = fileEncryption;
        return this;
    }

    /**
     * 设置安装监听
     */
    public VersionUpdate setOnInstallListener(OnInstallListener onInstallListener) {
        mOnInstallListener = onInstallListener;
        return this;
    }

    //===========================更新出错===================================//

    /**
     * 设置更新出错的监听
     */
    public VersionUpdate setOnUpdateFailureListener(@NonNull OnUpdateFailureListener onUpdateFailureListener) {
        mOnUpdateFailureListener = onUpdateFailureListener;
        return this;
    }


}
