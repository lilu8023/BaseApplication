package com.lilu.base.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Description:
 * 对象帮助类
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class ObjectUtils {

    /**
     * 判断对象是否为空
     * @param object 对象
     * @return {@code true} yes, {@code false} no
     */
    public static boolean isEmpty(final Object object) {
        if (object == null) return true;
        try {
            if (object.getClass().isArray() && Array.getLength(object) == 0) {
                return true;
            }
            if (object instanceof CharSequence && object.toString().length() == 0) {
                return true;
            }
            if (object instanceof Collection && ((Collection) object).isEmpty()) {
                return true;
            }
            if (object instanceof Map && ((Map) object).isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
