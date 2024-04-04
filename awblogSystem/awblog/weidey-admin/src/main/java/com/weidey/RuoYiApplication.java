package com.weidey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  后台启动成功   ლ(´ڡ`ლ)ﾞ  \n"
                + " .-------.    ____          ___             __\n"
                + " |       |    \\           //  \\           //\n"
                + " |———————|     \\         //    \\         // \n"
                + " |  (_)  |      \\       //      \\       //  \n"
                + " | |   | |       \\     //        \\     //   \n"
                + " | |   | |        \\   //          \\   //    \n"
                + " |_|   |_|         \\_//            \\_//     \n"
                + " 佛祖保佑       永无BUG  ");
    }
}
