package com.seata.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("order_tbl")
public class Order {
    @TableId
    private Long id;
    private String userId;
    private String commodityCode;
    private Integer count;
    private Integer money;
}
