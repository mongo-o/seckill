package com.ayl.seckil.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author AYL    2018/9/4 10:59
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SeckillGoodsVo {
    private Long goodsId;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private Double goodsPrice;
    private Double seckillPrice;
}
