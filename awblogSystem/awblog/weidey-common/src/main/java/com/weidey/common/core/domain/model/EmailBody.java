package com.weidey.common.core.domain.model;
import lombok.Data;

import java.util.Date;


@Data
public class EmailBody {
        /**
         * 邮件标题
         */
        private String title;
        /**
        * 邮件内容
        * */
        private String content;
        /**
         * 邮件接收者
         * */
        private String email;
        /**
         * 邮件验证码
         * */
        private String code;
        /**
         * 邮件类型
         * */
        private String emailType;
        /**
         * 邮件发送时间
         * */
        private Date now;
        /**
         * 邮件类型
         * */
        private String type;

}
