package com.weidey.framework.web.service;

import cn.hutool.core.util.RandomUtil;
import com.weidey.common.core.domain.entity.SysUser;
import com.weidey.common.core.domain.model.EmailBody;
import com.weidey.common.core.redis.RedisCache;
import com.weidey.common.exception.ServiceException;
import com.weidey.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;


@Component
public class SysEmailService {
      @Autowired
      SysUserMapper userMapper;

      @Autowired
      SysAsyncService emailAsyncService;

      @Autowired
      private RedisCache redisCache;


    public void sendEmailCode(EmailBody emailBody){

        final String KEY_REGISTER = "REGISTER";

        final String KEY_FORGET_PASS = "FORGET_PASS";

        SysUser user =  userMapper.checkEmailUnique(emailBody.getEmail());

        //发送邮件
        switch (emailBody.getEmailType()){
            case KEY_REGISTER:  {
                // 注册
                //查询用户是否存在
                if (user != null){
                    throw new ServiceException("该邮箱已被注册",400);
                }
                //查询缓存是否过期
                if(redisCache.getCacheObject(emailBody.getEmailType() + ":" + emailBody.getEmail()) != null){
                    throw new ServiceException("邮箱："+ emailBody.getEmail() + " 验证码已发送，请勿频繁操作",400);
                }
                //设置发送内容
                emailBody.setCode(RandomUtil.randomNumbers(6));
                emailBody.setTitle("欢迎注册阿伟博客");
                emailBody.setContent("您的验证码为：" + emailBody.getCode() + "，请在10分钟内填写，如非本人操作，请忽略本邮件。");
                emailBody.setNow(new Date());
                //发送邮箱
                emailAsyncService.send(emailBody);
                //保存验证码
                emailAsyncService.saveCode(emailBody);
            }
            case KEY_FORGET_PASS:{
                // 忘记密码
                // 查询用户是否存在
                if (user == null){
                    throw new ServiceException("该用户不存在",400);
                }
                //查询缓存是否过期
                if(redisCache.getCacheObject(emailBody.getEmailType() + ":" + emailBody.getEmail()) != null){
                    throw new ServiceException("邮箱："+ emailBody.getEmail() + " 验证码已发送，请勿频繁操作",400);
                }
                //设置发送内容
                emailBody.setCode(RandomUtil.randomNumbers(6));
                emailBody.setTitle("阿伟博客密码重置");
                emailBody.setContent("您的验证码为：" + emailBody.getCode() + "，请在10分钟内填写，如非本人操作，请忽略本邮件。");
                emailBody.setNow(new Date());
                //发送邮箱
                emailAsyncService.send(emailBody);
                //保存验证码
                emailAsyncService.saveCode(emailBody);
            }

        }

    }


}
