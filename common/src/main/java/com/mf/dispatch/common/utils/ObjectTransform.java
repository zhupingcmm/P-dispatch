package com.mf.dispatch.common.utils;

import com.mf.dispatch.common.base.ResponseEnum;
import com.mf.dispatch.common.exception.DispatchException;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ObjectTransform {

    /**
     *
     * @param source 原始数据
     * @param clazz 目标数据类型
     * @return 返回 转换后的数据
     * @param <T> 目标数据类型
     */
    public static <T> T transform (Object source, Class<T> clazz) {
        T target = null;
        try {
            target = clazz.newInstance();
            BeanUtils.copyProperties(target, source);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new DispatchException(ResponseEnum.FAILED_TO_TRANSFORM);
        }

        return target;
    }

    /**
     *
     * @param list 原始数据
     * @param clazz 目标数据类型
     * @return 返回 转换后的数据
     * @param <T> 目标数据类型
     */
    public static <T> List<T> transform(List<?> list, Class<T> clazz){
        if (list == null) list = new ArrayList<>();
        List<T> result = new ArrayList<>();

        list.forEach(x -> result.add(transform(x, clazz)));
        return result;
    }


}
