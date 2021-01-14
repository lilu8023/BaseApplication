package com.lilu.base.update.proxy;

import com.lilu.base.update.entity.NewVersionEntity;

import androidx.annotation.NonNull;

import java.io.File;

/**
 * Description:
 * 版本更新网络请求服务
 * 1、网络请求获取最新版本
 * 2、下载最新版本
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public interface IUpdateHttpService {



    /**
     * 异步post
     * @param callBack 回调
     */
    void getNewVersion(@NonNull Callback callBack);

    /**
     * 文件下载
     *
     * @param url      下载地址
     * @param path     文件保存路径
     * @param fileName 文件名称
     * @param callback 文件下载回调
     */
    void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull DownloadCallback callback);

    /**
     * 取消文件下载
     *
     * @param url      下载地址
     */
    void cancelDownload(@NonNull String url);

    /**
     * 网络请求回调
     */
    interface Callback {
        /**
         * 结果回调
         *
         * @param version 结果
         */
        void onSuccess(NewVersionEntity version);

        /**
         * 错误回调
         *
         * @param throwable 错误提示
         */
        void onError(Throwable throwable);
    }

    /**
     * 下载回调
     */
    interface DownloadCallback {
        /**
         * 下载之前
         */
        void onStart();

        /**
         * 更新进度
         *
         * @param progress 进度0.00 - 0.50  - 1.00
         * @param total    文件总大小 单位字节
         */
        void onProgress(float progress, long total);

        /**
         * 结果回调
         *
         * @param file 下载好的文件
         */
        void onSuccess(File file);

        /**
         * 错误回调
         *
         * @param throwable 错误提示
         */
        void onError(Throwable throwable);

    }

}
