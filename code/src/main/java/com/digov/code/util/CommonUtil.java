package com.digov.code.util;

import java.util.*;

/**
 * 公共 有关的
 *
 * @author yyx
 * @version 2.0
 * @date 2016年11月7日 14:55:27
 */

public class CommonUtil {

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(List<?> list) {
        if (list == null || list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
