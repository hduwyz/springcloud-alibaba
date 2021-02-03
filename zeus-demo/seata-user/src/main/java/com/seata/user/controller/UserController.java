package com.seata.user.controller;

import com.seata.user.entity.User;
import com.seata.user.mapper.UserMapper;
import com.seata.user.rpc.feign.FeignService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FeignService feignService;

    @GetMapping("/createOrder")
    @GlobalTransactional(name = "创建订单", rollbackFor = Exception.class)
    public String createOrderByUser(){
        User user = new User();
        user.setUserName("test");
        user.setAge(11);
        userMapper.insert(user);
        feignService.createOrder("token", user.getId());
        return "创建成功";
    }
}
