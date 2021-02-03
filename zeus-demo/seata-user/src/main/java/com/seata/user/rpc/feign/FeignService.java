package com.seata.user.rpc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * seata-order 生产者服务的 spring.application.name
 * fallback 当下游服务异常时，本服务的回滚处理类
 */
@FeignClient(value = "seata-order", fallback = FeignServiceImpl.class)
public interface FeignService {

    /**
     * 获取用户名
     * @param authorization
     * @param userId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/createOrder")
    String createOrder(@RequestHeader(value = "Authorization") String authorization, @RequestParam("userId") Long userId);

}
