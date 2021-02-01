package com.zeus.usercenter.controller;

import com.zeus.usercenter.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderQueryService orderQueryService;

    @RequestMapping("/getOrder")
    @ResponseBody
    public String queryOrder1(@RequestParam("orderId") String orderId) {

        return orderQueryService.queryOrderInfo(orderId);
    }
}
