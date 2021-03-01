package com.security.demo.controller;

import com.security.demo.service.CaptchaService;
import com.zeus.core.models.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    @RequestMapping("/get/{phone}")
    public Result captchaByMobile(@PathVariable String phone){
        if (captchaService.sendCaptcha(phone)){
            return Result.succeed("验证码发送成功");
        }
        return Result.failed("验证码发送失败");
    }
}
