package com.weidey.web.controller.system;


import com.weidey.common.core.domain.AjaxResult;
import com.weidey.common.core.domain.model.EmailBody;
import com.weidey.common.core.domain.param.EmailCodeParam;
import com.weidey.framework.web.service.SysEmailService;
import com.weidey.system.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.weidey.common.core.domain.AjaxResult.error;

@Api("获取邮箱验证码")
@RestController
public class SysEmailController {

    final String KEY_REGISTER = "REGISTER";
    final String KEY_FORGET_PASS = "FORGET_PASS";

    @Autowired
    SysEmailService emailService;

    @Autowired
    private ISysConfigService configService;


    @ApiOperation("获取注册邮箱验证码")
    @PostMapping("/code")
    public AjaxResult getEmailCode(@Validated @RequestBody EmailCodeParam emailCodeParam){
        if (!("true".equals(configService.selectConfigByKey("sys.account.enable.Email"))))
        {
            return error("当前系统没有开启邮箱功能！");
        }
        EmailBody emailBody = new EmailBody();
        emailBody.setEmail(emailCodeParam.getEmail());
        //1.进入分类
        switch (emailCodeParam.getType()){
            case KEY_REGISTER:
                //2.设置邮箱类型
                emailBody.setEmailType(KEY_REGISTER);
                break;
            case KEY_FORGET_PASS:
                //2.设置邮箱类型
                emailBody.setEmailType(KEY_FORGET_PASS);
            default:
                return error("非法的邮箱请求");
        }
        emailService.sendEmailCode(emailBody);
        return AjaxResult.success("验证码已发送,请查收");
    }
}
