package com.lilu.base.widget.statuslayout.core;


import com.lilu.base.widget.statuslayout.callback.Callback;

/**
 * Description:TODO
 * Create Time:2017/9/4 8:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface Convertor<T> {

   Class<?extends Callback> map(T t);

}
