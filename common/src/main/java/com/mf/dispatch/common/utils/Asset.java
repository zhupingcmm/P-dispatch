package com.mf.dispatch.common.utils;

import com.mf.dispatch.common.base.ResponseEnum;
import com.mf.dispatch.common.exception.DispatchException;

import java.util.List;


public class Asset {

    public static <T> boolean notNull (T data) {
        if (data == null) throw new DispatchException(ResponseEnum.NO_ROWS_AFFECTED);
        return true;
    }

    public static boolean singleRowAffected(int result) {
        if (result == 0) {
            throw new DispatchException(ResponseEnum.NO_ROWS_AFFECTED);
        } else if (result > 1) {
            throw new DispatchException(ResponseEnum.TOO_MANY_ROWS_AFFECTED);
        }
        return true;
    }

    /**
     * 数据操作不全
     *
     * @param result
     * @param list
     * @return
     */
    public static <T> boolean totalRowsAffected(int result, List <T> list) {
        if (list.isEmpty() || list.size() != result) {
            throw new DispatchException(ResponseEnum.NOT_TOTAL_ROWS_AFFECTED);
        }
        return true;
    }
}
