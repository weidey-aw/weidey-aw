package com.weidey.common.utils.email;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;


@Component
public class EmailUtils {
    /**
     *  邮箱校验工具
     * @param str 邮箱
     * @return boolean
     */
    public static boolean checkQQ(String str) {
        // matches()方法告知此字符串是否匹配给定的正则表达式。
        return str.matches("[1-9][0-9]+@qq.com");
    }

    /**
     *  校验邮箱是否正确
     */
    public static boolean checkEmail(String email) {
        return StrUtil.isBlank(email) && checkQQ(email);
    }

}
