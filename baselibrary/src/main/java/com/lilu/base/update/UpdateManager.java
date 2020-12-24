package com.lilu.base.update;

import android.content.Context;

import com.lilu.base.update.entity.PromptEntity;
import com.lilu.base.update.entity.UpdateEntity;
import com.lilu.base.update.listener.IUpdateParseCallback;
import com.lilu.base.update.proxy.IUpdateChecker;
import com.lilu.base.update.proxy.IUpdateDownloader;
import com.lilu.base.update.proxy.IUpdateHttpService;
import com.lilu.base.update.proxy.IUpdateParser;
import com.lilu.base.update.proxy.IUpdatePrompter;
import com.lilu.base.update.proxy.IUpdateProxy;
import com.lilu.base.update.proxy.impl.DefaultUpdatePrompter;
import com.lilu.base.update.service.OnFileDownloadListener;
import com.lilu.base.update.utils.UpdateUtils;
import com.lilu.base.utils.logger.Logger;

import java.lang.ref.WeakReference;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import static com.lilu.base.update.entity.UpdateError.ERROR.PROMPT_ACTIVITY_DESTROY;

/**
 * Description:
 * 版本更新管理者
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class UpdateManager implements IUpdateProxy {

    /**
     * 更新信息
     */
    private UpdateEntity mUpdateEntity;

    private WeakReference<Context> mContext;

    /**
     * apk缓存的目录
     */
    private String mApkCacheDir;

    //===========更新组件===============//
    /**
     * 版本更新网络请求服务API
     */
    private IUpdateHttpService mIUpdateHttpService;
    /**
     * 版本更新检查器
     */
    private IUpdateChecker mIUpdateChecker;
    /**
     * 版本更新解析器
     */
    private IUpdateParser mIUpdateParser;
    /**
     * 版本更新下载器
     */
    private IUpdateDownloader mIUpdateDownloader;
    /**
     * 文件下载监听
     */
    private OnFileDownloadListener mOnFileDownloadListener;
    /**
     * 版本更新提示器
     */
    private IUpdatePrompter mIUpdatePrompter;
    /**
     * 版本更新提示器参数信息
     */
    private PromptEntity mPromptEntity;

    /**
     * 构造函数
     *
     * @param builder
     */
    private UpdateManager(Builder builder) {
        mContext = new WeakReference<>(builder.context);
        mApkCacheDir = builder.apkCacheDir;

        mIUpdateHttpService = builder.updateHttpService;

        mIUpdateChecker = builder.updateChecker;
        mIUpdateParser = builder.updateParser;
        mIUpdateDownloader = builder.updateDownLoader;
        mOnFileDownloadListener = builder.onFileDownloadListener;

        mIUpdatePrompter = builder.updatePrompter;
        mPromptEntity = builder.promptEntity;
    }


    @Nullable
    @Override
    public Context getContext() {
        return mContext != null ? mContext.get() : null;
    }

    @Override
    public IUpdateHttpService getIUpdateHttpService() {
        return mIUpdateHttpService;
    }

    /**
     * 开始版本更新
     */
    @Override
    public void update() {
        Logger.d("XUpdate.update()启动:" + toString());

        //
        checkVersion();
    }


    /**
     * 版本检查之前
     */
    @Override
    public void onBeforeCheck() {
        mIUpdateChecker.onBeforeCheck();
    }

    /**
     * 执行网络请求，检查应用的版本信息
     */
    @Override
    public void checkVersion() {
        Logger.d("开始检查版本信息...");
        mIUpdateChecker.checkVersion(this);
    }

    /**
     * 版本检查之后
     */
    @Override
    public void onAfterCheck() {
        mIUpdateChecker.onAfterCheck();
    }


    @Override
    public void parseJson(@NonNull String json, final IUpdateParseCallback callback) throws Exception {
        Logger.i("服务端返回的最新版本信息:" + json);
        mIUpdateParser.parseJson(json, new IUpdateParseCallback() {
            @Override
            public void onParseResult(UpdateEntity updateEntity) {
                mUpdateEntity = refreshParams(updateEntity);
                callback.onParseResult(updateEntity);
            }
        });
    }

    /**
     * 刷新本地参数
     *
     * @param updateEntity
     */
    private UpdateEntity refreshParams(UpdateEntity updateEntity) {
        //更新信息（本地信息）
        if (updateEntity != null) {
            updateEntity.setApkCacheDir(mApkCacheDir);
            updateEntity.setIUpdateHttpService(mIUpdateHttpService);
        }
        return updateEntity;
    }

    /**
     * 发现新版本
     *
     * @param updateEntity 版本更新信息
     * @param updateProxy  版本更新代理
     */
    @Override
    public void findNewVersion(@NonNull UpdateEntity updateEntity, @NonNull IUpdateProxy updateProxy) {
        Logger.i("发现新版本:" + updateEntity);
        if (mIUpdatePrompter instanceof DefaultUpdatePrompter) {
            Context context = getContext();
            if (context instanceof FragmentActivity && ((FragmentActivity) context).isFinishing()) {
                VersionHelper.onUpdateError(PROMPT_ACTIVITY_DESTROY);
            } else {
                mIUpdatePrompter.showPrompt(updateEntity, updateProxy, mPromptEntity);
            }
        } else {
            mIUpdatePrompter.showPrompt(updateEntity, updateProxy, mPromptEntity);
        }
    }

    /**
     * 未发现新版本
     *
     * @param throwable 未发现的原因
     */
    @Override
    public void noNewVersion(Throwable throwable) {
        Logger.i(throwable != null ? "未发现新版本:" + throwable.getMessage() : "未发现新版本!");
        mIUpdateChecker.noNewVersion(throwable);
    }

    @Override
    public void startDownload(@NonNull UpdateEntity updateEntity, @Nullable OnFileDownloadListener downloadListener) {
        Logger.i("开始下载更新文件:" + updateEntity);
        updateEntity.setIUpdateHttpService(mIUpdateHttpService);

        mIUpdateDownloader.startDownload(updateEntity, downloadListener);
    }

    /**
     * 后台下载
     */
    @Override
    public void backgroundDownload() {
        Logger.i("点击了后台更新按钮, 在通知栏中显示下载进度...");
        mIUpdateDownloader.backgroundDownload();
    }

    @Override
    public void cancelDownload() {
        Logger.d("正在取消更新文件的下载...");
        mIUpdateDownloader.cancelDownload();
    }

    @Override
    public void recycle() {
        Logger.d("正在回收资源...");
        mIUpdateHttpService = null;
        mIUpdateChecker = null;
        mIUpdateParser = null;
        mIUpdateDownloader = null;
        mOnFileDownloadListener = null;
        mIUpdatePrompter = null;
    }

    //============================对外提供的自定义使用api===============================//

    /**
     * 为外部提供简单的下载功能
     *
     * @param downloadUrl      下载地址
     * @param downloadListener 下载监听
     */
    public void download(String downloadUrl, @Nullable OnFileDownloadListener downloadListener) {
        startDownload(refreshParams(new UpdateEntity().setDownloadUrl(downloadUrl)), downloadListener);
    }

    /**
     * 直接更新，不使用版本更新检查器
     *
     * @param updateEntity 版本更新信息
     */
    public void update(UpdateEntity updateEntity) {
        mUpdateEntity = refreshParams(updateEntity);
        try {
            UpdateUtils.processUpdateEntity(mUpdateEntity, "这里调用的是直接更新方法，因此没有json!", this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //============================构建者===============================//

    /**
     * 版本更新管理构建者
     */
    public static class Builder {
        //=======必填项========//
        Context context;
        /**
         * 版本更新网络请求服务API
         */
        IUpdateHttpService updateHttpService;
        /**
         * 版本更新解析器
         */
        IUpdateParser updateParser;

        //===========更新行为================//
        /**
         * 版本更新检查器
         */
        IUpdateChecker updateChecker;
        /**
         * 版本更新提示器参数信息
         */
        PromptEntity promptEntity;
        /**
         * 版本更新提示器
         */
        IUpdatePrompter updatePrompter;
        /**
         * 下载器
         */
        IUpdateDownloader updateDownLoader;
        /**
         * 下载监听
         */
        OnFileDownloadListener onFileDownloadListener;
        /**
         * apk缓存的目录
         */
        String apkCacheDir;

        /**
         * 构建者
         *
         * @param context
         */
        Builder(@NonNull Context context) {
            this.context = context;

            promptEntity = new PromptEntity();
            updateHttpService = VersionHelper.getIUpdateHttpService();
            updateChecker = VersionHelper.getIUpdateChecker();
            updateParser = VersionHelper.getIUpdateParser();
            updatePrompter = VersionHelper.getIUpdatePrompter();
            updateDownLoader = VersionHelper.getIUpdateDownLoader();

            apkCacheDir = VersionHelper.getApkCacheDir();
        }


        /**
         * 设置网络请求的请求服务API
         */
        public Builder updateHttpService(@NonNull IUpdateHttpService updateHttpService) {
            this.updateHttpService = updateHttpService;
            return this;
        }

        /**
         * 设置apk下载的缓存目录
         */
        public Builder apkCacheDir(@NonNull String apkCacheDir) {
            this.apkCacheDir = apkCacheDir;
            return this;
        }


        /**
         * 设置版本更新检查器
         */
        public Builder updateChecker(@NonNull IUpdateChecker updateChecker) {
            this.updateChecker = updateChecker;
            return this;
        }

        /**
         * 设置版本更新的解析器
         */
        public Builder updateParser(@NonNull IUpdateParser updateParser) {
            this.updateParser = updateParser;
            return this;
        }

        /**
         * 设置版本更新提示器
         */
        public Builder updatePrompter(@NonNull IUpdatePrompter updatePrompter) {
            this.updatePrompter = updatePrompter;
            return this;
        }

        /**
         * 设置文件的下载监听
         */
        public Builder setOnFileDownloadListener(OnFileDownloadListener onFileDownloadListener) {
            this.onFileDownloadListener = onFileDownloadListener;
            return this;
        }

        /**
         * 设置主题颜色
         */
        @Deprecated
        public Builder themeColor(@ColorInt int themeColor) {
            promptEntity.setThemeColor(themeColor);
            return this;
        }

        /**
         * 设置主题颜色
         */
        public Builder promptThemeColor(@ColorInt int themeColor) {
            promptEntity.setThemeColor(themeColor);
            return this;
        }

        /**
         * 设置顶部背景图片
         */
        @Deprecated
        public Builder topResId(@DrawableRes int topResId) {
            promptEntity.setTopResId(topResId);
            return this;
        }

        /**
         * 设置顶部背景图片
         */
        public Builder promptTopResId(@DrawableRes int topResId) {
            promptEntity.setTopResId(topResId);
            return this;
        }

        /**
         * 设置按钮的文字颜色
         */
        public Builder promptButtonTextColor(@ColorInt int buttonTextColor) {
            promptEntity.setButtonTextColor(buttonTextColor);
            return this;
        }

        /**
         * 设置是否支持后台更新
         */
        public Builder supportBackgroundUpdate(boolean supportBackgroundUpdate) {
            promptEntity.setSupportBackgroundUpdate(supportBackgroundUpdate);
            return this;
        }

        /**
         * 设置版本更新提示器宽度占屏幕的比例，默认是-1，不做约束
         */
        public Builder promptWidthRatio(float widthRatio) {
            promptEntity.setWidthRatio(widthRatio);
            return this;
        }

        /**
         * 设置版本更新提示器高度占屏幕的比例，默认是-1，不做约束
         */
        public Builder promptHeightRatio(float heightRatio) {
            promptEntity.setHeightRatio(heightRatio);
            return this;
        }

        /**
         * 设置版本更新提示器的样式
         */
        public Builder promptStyle(@NonNull PromptEntity promptEntity) {
            this.promptEntity = promptEntity;
            return this;
        }

        /**
         * 设备版本更新下载器
         */
        public Builder updateDownLoader(@NonNull IUpdateDownloader updateDownLoader) {
            this.updateDownLoader = updateDownLoader;
            return this;
        }

        /**
         * 构建版本更新管理者
         */
        public UpdateManager build() {
            UpdateUtils.requireNonNull(this.context, "[UpdateManager.Builder] : context == null");
//            UpdateUtils.requireNonNull(this.updateHttpService, "[UpdateManager.Builder] : updateHttpService == null");

//            if (TextUtils.isEmpty(apkCacheDir)) {
//                apkCacheDir = UpdateUtils.getDefaultDiskCacheDirPath();
//            }
            return new UpdateManager(this);
        }

        /**
         * 进行版本更新
         */
        public void update() {
            build().update();
        }
    }

}
