package com.lilu.base.update.proxy.impl;

import com.lilu.base.update.proxy.IUpdateHttpService;

import androidx.annotation.NonNull;

/**
 * Description:
 *
 * @author lilu on 2020/12/24
 * No one knows this better than me
 */
public class DefaultUpdateHttpService implements IUpdateHttpService {


    @Override
    public void getNewVersion(@NonNull Callback callBack) {
        callBack.onSuccess("{\n" +
                "\t\"Code\": 0,\n" +
                "\t\"Msg\": \"\",\n" +
                "\t\"UpdateStatus\": 1,\n" +
                "\t\"VersionCode\": 3,\n" +
                "\t\"VersionName\": \"1.0.2\",\n" +
                "\t\"ModifyContent\": \"1、优化api接口。\\r\\n2、添加使用demo演示。\\r\\n3、新增自定义更新服务API接口。\\r\\n4、优化更新提示界面。\",\n" +
                "\t\"DownloadUrl\": \"https://raw.githubusercontent.com/xuexiangjys/XUpdate/master/apk/xupdate_demo_1.0.2.apk\",\n" +
                "\t\"ApkSize\": 2048\n" +
                "}");
    }

    @Override
    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull DownloadCallback callback) {

        callback.onStart();
        callback.onProgress(0.5f,1);
    }

    @Override
    public void cancelDownload(@NonNull String url) {

    }
}
