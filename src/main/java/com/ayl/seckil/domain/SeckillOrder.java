package com.ayl.seckil.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author AYL    2018/9/5 0:28
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SeckillOrder {
    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;
}
