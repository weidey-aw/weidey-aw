package com.weidey.framework.web.service;

import com.weidey.common.core.domain.model.EmailBody;
import com.weidey.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.weidey.common.constant.CacheConstants;
import com.weidey.common.constant.Constants;
import com.weidey.common.constant.UserConstants;
import com.weidey.common.core.domain.entity.SysUser;
import com.weidey.common.core.domain.model.RegisterBody;
import com.weidey.common.core.redis.RedisCache;
import com.weidey.common.exception.user.CaptchaException;
import com.weidey.common.exception.user.CaptchaExpireException;
import com.weidey.common.utils.MessageUtils;
import com.weidey.common.utils.SecurityUtils;
import com.weidey.common.utils.StringUtils;
import com.weidey.framework.manager.AsyncManager;
import com.weidey.framework.manager.factory.AsyncFactory;
import com.weidey.system.service.ISysConfigService;
import com.weidey.system.service.ISysUserService;

/**
 * 注册校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 注册
     */
    public String register(RegisterBody registerBody)
    {
        String msg = "",
                username = registerBody.getUsername(),
                password = registerBody.getPassword(),
                nikeName = registerBody.getNikeName(),
                email = registerBody.getEmail();
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setEmail(email);
        // 验证码开关
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled)
        {
            // 使用图片验证码
            // validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());

            // 使用邮箱验证码
            if (!verifyCode("REGISTER", email, registerBody.getEmailCode())){
                 throw new ServiceException("邮箱验证码错误",400);
            }
        }
        if(StringUtils.isEmpty(nikeName)){
            msg = "昵称不能为空";
        }
        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (!userService.checkUserNameUnique(sysUser))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else if (!userService.checkEmailUnique(sysUser))
        {
            msg = "保存用户'" + email + "'失败，注册账号已存在";
        }
        else
        {
            sysUser.setNickName(nikeName);
            sysUser.setPassword(SecurityUtils.encryptPassword(password));
            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     * 
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }


    /**
     * 校验验证码
     */
    public Boolean verifyCode(String emailType, String email, String code) {
        //查询缓存
        try {
            EmailBody emailCache =  redisCache.getCacheObject(emailType + ":" + email);
            return emailCache.getCode().equals(code);
        }catch ( Exception e){
            return  false;
        }
    }















}
