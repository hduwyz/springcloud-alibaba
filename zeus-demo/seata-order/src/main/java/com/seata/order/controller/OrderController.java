package com.seata.order.controller;

import com.seata.order.entity.Order;
import com.seata.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping("/createOrder")
    public String createOrder(@RequestParam("userId") Long userId, HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        log.info(authorization);
        Order order = new Order();
        order.setUserId(String.valueOf(userId));
        order.setCount(1);
        order.setMoney(1);
        order.setCommodityCode(UUID.randomUUID().toString());
        orderMapper.insert(order);
        int count = 1/0;
        return "创建订单成功,订单号：" + order.getId();
    }
}
