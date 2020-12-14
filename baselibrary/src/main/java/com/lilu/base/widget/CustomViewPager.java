package com.lilu.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Description:
 * 一个自定义的viewpager
 * ///ViewPager于ViewPager2有什么区别
 * 1、可设置是否可滑动
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class CustomViewPager extends ViewPager {

    //是否可滑动
    private boolean isCanScroll = true;

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //针对是否可滑动进行拦截处理
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onTouchEvent(ev);
    }

    /**
     * 设置是否可滑动
     * @param isCanScroll 是否可滑动
     */
    public void setCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }
}
