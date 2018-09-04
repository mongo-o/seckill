package com.ayl.seckil.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author AYL    2018/9/4 0:24
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SeckillGoodsDetailVo extends SeckillGoodsVo{
    private String goodsDetail;
    private int stockCount;
    private Date startDate;
    private Date endDate;
}
