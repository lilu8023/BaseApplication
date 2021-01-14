package com.lilu.base.update.proxy;

import com.lilu.base.update.entity.NewVersionEntity;

import androidx.annotation.NonNull;

/**
 * Description:
 * 版本更新检查器，检查是否有最新版本
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public interface IUpdateChecker {

    /**
     * 版本检查之前
     */
    void onBeforeCheck(@NonNull IUpdateProxy updateProxy);

    /**
     * 检查应用的版本信息
     * @param updateProxy 版本更新代理
     */
    void checkVersion(@NonNull IUpdateProxy updateProxy);

    /**
     * 版本检查之后
     */
    void onAfterCheck();

    /**
     * 处理版本检查的结果
     *
     * @param updateProxy 版本更新代理
     */
    void processCheckResult(@NonNull NewVersionEntity version, @NonNull IUpdateProxy updateProxy);

    /**
     * 未发现新版本
     *
     * @param throwable 未发现的原因
     */
    void noNewVersion(Throwable throwable);
}
