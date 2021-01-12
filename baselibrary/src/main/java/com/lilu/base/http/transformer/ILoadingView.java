package com.lilu.base.http.transformer;

import com.lilu.base.utils.logger.Logger;

/**
 * Description:
 *
 * @author lilu on 2021/1/4
 * No one knows this better than me
 */
public interface ILoadingView {

    default void showLoadingView(){
        Logger.i("加载中");
    };

    void hideLoadingView();
}
