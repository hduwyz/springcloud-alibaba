package com.seata.user.rpc.feign;

import org.springframework.stereotype.Component;

/**
 * @author 刘志强
 * @date 2020/11/17 15:24
 */
@Component
public class FeignServiceImpl implements FeignService {
    @Override
    public String createOrder(String authorization, Long userId) {
        return "下游服务调用失败";
    }
}
