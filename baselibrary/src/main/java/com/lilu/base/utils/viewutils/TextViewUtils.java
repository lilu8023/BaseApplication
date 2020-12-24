package com.lilu.base.utils.viewutils;


import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.widget.TextView;

import com.lilu.base.utils.StringUtils;

import androidx.annotation.ColorInt;

/**
 * Description:
 * View - TextView帮助类
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class TextViewUtils {

    private TextViewUtils() {
    }


    public static <T extends TextView> T setText(final T textView, final CharSequence text) {
        if (textView != null) {
            textView.setText(text);
        }
        return textView;
    }

    public static <T extends TextView> T setDefaultText(final T textView, final CharSequence text,final CharSequence defaultText) {
        if (textView != null) {
            if(StringUtils.isEmpty(text.toString())){
                textView.setText(defaultText);
            }else{
                textView.setText(text);
            }
        }
        return textView;
    }


    /**
     * 设置字体颜色
     */
    public static <T extends TextView> T setTextColor(final T textView, @ColorInt final int color) {
        if (textView != null) {
            textView.setTextColor(color);
        }
        return textView;
    }

    /**
     * 设置多个 TextView 字体颜色
     */
    public static <T extends TextView> boolean setTextColors(@ColorInt final int color, final T... views) {
        if (views != null) {
            for (T view : views) {
                setTextColor(view, color);
            }
            return true;
        }
        return false;
    }

    // ========
    // = Html =
    // ========

    /**
     * 设置 Html 内容
     */
    public static <T extends TextView> T setHtmlText(final T textView, final String content) {
        if (textView != null && content != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY));
            } else {
                textView.setText(Html.fromHtml(content));
            }
        }
        return textView;
    }


    // ============
    // = 字体相关 =
    // ============
    /**
     * 设置字体
     */
    public static <T extends TextView> T setTypeface(final T textView, final Typeface typeface) {
        if (textView != null && typeface != null) {
            textView.setTypeface(typeface);
        }
        return textView;
    }

    /**
     * 设置字体
     */
    public static <T extends TextView> T setTypeface(final T textView, final Typeface typeface, final int style) {
        if (textView != null && typeface != null) {
            textView.setTypeface(typeface, style);
        }
        return textView;
    }

}
