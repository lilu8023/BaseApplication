package com.lilu.base.widget.smartrefresh.listener;


import com.lilu.base.widget.smartrefresh.api.RefreshLayout;

import androidx.annotation.NonNull;

/**
 * 刷新监听器
 * Created by scwang on 2017/5/26.
 */
public interface OnRefreshListener {
    void onRefresh(@NonNull RefreshLayout refreshLayout);
}
