package com.lilu.base.widget.smartrefresh.listener;


import com.lilu.base.widget.smartrefresh.api.RefreshLayout;
import com.lilu.base.widget.smartrefresh.constant.RefreshState;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;
import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;
import static androidx.annotation.RestrictTo.Scope.SUBCLASSES;

/**
 * 刷新状态改变监听器
 * Created by scwang on 2017/5/26.
 */
public interface OnStateChangedListener {
    /**
     * 【仅限框架内调用】状态改变事件
     * @param refreshLayout RefreshLayout
     * @param oldState 改变之前的状态
     * @param newState 改变之后的状态
     */
    @RestrictTo({LIBRARY,LIBRARY_GROUP,SUBCLASSES})
    void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState);
}