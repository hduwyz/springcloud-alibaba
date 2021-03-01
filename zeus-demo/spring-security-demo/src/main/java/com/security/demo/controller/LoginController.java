package com.security.demo.controller;


import com.security.demo.dao.UserDetailsRepository;
import com.zeus.core.models.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    /*** 登录失败返回 401 以及提示信息.
     * @return the rest
     */
    @PostMapping("/failure")
    public Result loginFailure() {
        return Result.failedWith("", HttpStatus.UNAUTHORIZED.value(), "登录失败了，老 哥");
    }

    /*** 登录成功后拿到个人信息.
     * @return the rest
     */
    @PostMapping("/success")
    public Result loginSuccess() {
        // 登录成功后用户的认证信息 UserDetails会存在 安全上下文寄存器 SecurityContextHolder 中
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        UserDetails sysUser = userDetailsRepository.loadUserByUsername(username);
        // todo 脱敏
        return Result.succeed(sysUser,"登录成功"); }
}
