package com.weidey.common.core.domain.model;

import lombok.Data;

/**
 * 用户注册对象
 * 
 * @author ruoyi
 */

@Data
public class RegisterBody extends LoginBody
{

    private  String email;

    private  String emailCode;

    private  String emailType;

    private  String nikeName;

}
