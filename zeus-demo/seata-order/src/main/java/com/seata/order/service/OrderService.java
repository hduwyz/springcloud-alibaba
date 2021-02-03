package com.seata.order.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

@Service
@GlobalTransactional(rollbackFor = Exception.class)
public class OrderService {
}
