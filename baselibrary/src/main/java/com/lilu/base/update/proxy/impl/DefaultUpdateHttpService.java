package com.lilu.base.update.proxy.impl;

import android.util.Log;

import com.lilu.base.update.entity.NewVersionEntity;
import com.lilu.base.update.proxy.IUpdateHttpService;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * 负责检查是否有最新版本与新版本下载
 * @author lilu on 2020/12/24
 * No one knows this better than me
 */
public class DefaultUpdateHttpService implements IUpdateHttpService {


    @Override
    public void getNewVersion(@NonNull Callback callBack) {

        NewVersionEntity versionEntity = new NewVersionEntity();
        versionEntity.setCode(0);
        versionEntity.setMsg("我曹我是信息");
        versionEntity.setRequireUpgrade(2);
        versionEntity.setVersionCode(12);
        versionEntity.setVersionName("船新版本");
        versionEntity.setModifyContent("1、优化api接口。\\r\\n2、添加使用demo演示。\\r\\n3、新增自定义更新服务API接口。\\r\\n4、优化更新提示界面。");
        versionEntity.setDownloadUrl("https://raw.githubusercontent.com/xuexiangjys/XUpdate/master/apk/xupdate_demo_1.0.2.apk");
        versionEntity.setApkSize(123);
        callBack.onSuccess(versionEntity);
    }

    @Override
    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull DownloadCallback callback) {

        callback.onStart();

        Observable.intervalRange(1,10,1,1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        if(aLong >= 10){
                            callback.onProgress(1f,1);
                        }else{
                            callback.onProgress(aLong/10f,1);
                        }


                        Log.i("Update","long = "+aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void cancelDownload(@NonNull String url) {

    }
}
