package com.weidey.framework.web.service;

import com.weidey.common.core.domain.model.EmailBody;
import com.weidey.common.core.redis.RedisCache;
import com.weidey.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@EnableAsync
public class SysAsyncService {

    @Resource
    private JavaMailSender mailSender;

    @Autowired
    private RedisCache redisCache;

    @Value("${spring.mail.username}")
    private String from;

    @Async("threadPoolTaskExecutor")
    public void send(EmailBody emailBody) {
        //发送邮箱
        try {
            SimpleMailMessage mailSenderMsg = new SimpleMailMessage();
            mailSenderMsg.setFrom(from);
            mailSenderMsg.setTo(emailBody.getEmail());
            mailSenderMsg.setSubject(emailBody.getTitle());
            mailSenderMsg.setSentDate(emailBody.getNow());
            mailSenderMsg.setText(emailBody.getContent());
            mailSender.send(mailSenderMsg);
        } catch (Exception e) {
            throw new ServiceException("邮件发送失败");
        }
    }


    @Async("threadPoolTaskExecutor")
    public void saveCode(EmailBody emailBody) {
        try {
            // 保存到数据库
            //save(emailSql);
            // 保存到redis
            redisCache.setCacheObject(emailBody.getEmailType() + ":" + emailBody.getEmail(), emailBody, 10, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
