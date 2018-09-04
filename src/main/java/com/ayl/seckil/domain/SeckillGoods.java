package com.ayl.seckil.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author AYL    2018/9/4 0:16
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SeckillGoods {
    private Long id;
    private Long goodsId;
    private Double seckillPrice;
    private int stockCount;
    private Date startDate;
    private Date endDate;
    private Date gmtCreate;
    private Date gmtModified;
}
