package com.weidey.common.core.domain.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
public class EmailCodeParam {

    @NotBlank(message = "邮箱不能为空")
    @Pattern(regexp = "[1-9][0-9]+@qq.com",message = "邮箱格式错误")
    private String email;

    @NotBlank(message = "非法的邮箱请求")
    private String type;
}
