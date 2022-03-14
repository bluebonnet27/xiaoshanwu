package com.ti.xiaoshanwu.controller;

import com.ti.xiaoshanwu.service.impl.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Random;

/**
 * 测试类，不用于生产环境
 *
 * @author TiHongsheng
 */
@Controller
public class MailController {

    @Resource
    private MailService mailService;

    @RequestMapping("getCheckCode")
    @ResponseBody
    public String getCheckCode(String email){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为："+checkCode;
        try {
            mailService.sendSimpleMail(email, "注册验证码", message);
        }catch (Exception e){
            e.printStackTrace();
            return "?????????";
        }
        return checkCode;
    }
}
