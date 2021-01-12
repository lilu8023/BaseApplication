package com.lilu.base.http.transformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 *
 * @author lilu on 2021/1/4
 * No one knows this better than me
 */
public class Transformer {

    public static <T>ObservableTransformer<T,T> switchSchedulers(ILoadingView loadingView){

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                //订阅开始执行
                                if(loadingView != null){
                                    loadingView.showLoadingView();
                                }

                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                if(loadingView != null){
                                    loadingView.hideLoadingView();
                                }
                            }
                        });
            }
        };
    }
}
