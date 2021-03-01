package com.security.demo.service;

public interface CaptchaService {

    boolean sendCaptcha(String phone);

    boolean verifyCaptcha(String phone, String code);
}
