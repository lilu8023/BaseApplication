package com.lilu.base.widget.smartrefresh.listener;


import com.lilu.base.widget.smartrefresh.api.RefreshLayout;

import androidx.annotation.NonNull;

/**
 * 加载更多监听器
 * Created by scwang on 2017/5/26.
 */
public interface OnLoadMoreListener {
    void onLoadMore(@NonNull RefreshLayout refreshLayout);
}
