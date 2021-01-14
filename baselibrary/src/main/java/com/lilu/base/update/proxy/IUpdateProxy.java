package com.lilu.base.update.proxy;

import android.content.Context;

import com.lilu.base.update.entity.NewVersionEntity;
import com.lilu.base.update.entity.UpdateEntity;
import com.lilu.base.update.service.OnFileDownloadListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * 版本更新代理
 *
 * @author xuexiang
 * @since 2018/7/1 下午9:45
 */
public interface IUpdateProxy {

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    @Nullable
    Context getContext();

    /**
     * 获取版本更新网络请求服务API
     *
     * @return 网络请求服务API
     */
    IUpdateHttpService getIUpdateHttpService();

    /**
     * 开始版本更新
     */
    void update();

    //============ICheckerProxy=================//

    /**
     * 版本检查之前
     */
    void onBeforeCheck();


    /**
     * 发现新版本
     *
     * @param updateEntity 版本更新信息
     * @param updateProxy  版本更新代理
     */
    void findNewVersion(@NonNull UpdateEntity updateEntity, @NonNull IUpdateProxy updateProxy);

    /**
     * 未发现新版本
     *
     * @param throwable 未发现的原因
     */
    void noNewVersion(Throwable throwable);

    //=============IParserProxy================//


    void entityParse(@NonNull NewVersionEntity version);

    //=============IPrompterProxy================//

    /**
     * 开始下载更新
     *
     * @param updateEntity     更新信息
     * @param downloadListener 文件下载监听
     */
    void startDownload(@NonNull UpdateEntity updateEntity, @Nullable OnFileDownloadListener downloadListener);

    /**
     * 后台下载
     */
    void backgroundDownload();

    /**
     * 取消下载
     */
    void cancelDownload();

    /**
     * 资源回收
     */
    void recycle();

}
