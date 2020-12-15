package com.lilu.base.widget.banner.indicator;

import android.view.View;


import com.lilu.base.widget.banner.config.IndicatorConfig;
import com.lilu.base.widget.banner.listener.OnPageChangeListener;

import androidx.annotation.NonNull;


public interface Indicator extends OnPageChangeListener {
    @NonNull
    View getIndicatorView();

    IndicatorConfig getIndicatorConfig();

    void onPageChanged(int count, int currentPosition);

}
