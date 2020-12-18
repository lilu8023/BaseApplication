package com.lilu.base.http.utils;

import com.lilu.base.http.func.HandleFuc;
import com.lilu.base.http.func.HttpResponseFunc;
import com.lilu.base.http.model.ApiResult;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 *
 * @author lilu on 2020/12/18
 * No one knows this better than me
 */
public class TransformerUtils {

    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<ApiResult<T>, T> _io_main() {
        return new ObservableTransformer<ApiResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<ApiResult<T>> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new HandleFuc<T>())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                            }
                        })
                        .onErrorResumeNext(new HttpResponseFunc<T>());
            }
        };
    }


    public static <T> ObservableTransformer<ApiResult<T>, T> _main() {
        return new ObservableTransformer<ApiResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<ApiResult<T>> upstream) {
                return upstream
                        //.observeOn(AndroidSchedulers.mainThread())
                        .map(new HandleFuc<T>())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                            }
                        })
                        .onErrorResumeNext(new HttpResponseFunc<T>());
            }
        };
    }

}
