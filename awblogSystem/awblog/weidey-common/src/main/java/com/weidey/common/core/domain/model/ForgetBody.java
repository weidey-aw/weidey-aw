package com.weidey.common.core.domain.model;

import lombok.Data;

@Data
public class ForgetBody {
    /**
     * 邮箱
     */
    private String email;
    /**
     * 邮件类型
     * */
    private String emailType;
    /**
     * 邮件验证码
     * */
    private String password;
    /**
     * 邮件验证码
     * */
    private String code;

}
