package com.ayl.seckil.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author AYL    2018/9/3 23:40
 */
@Getter
@Setter
@ToString(callSuper = true)
public class Goods {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private int goodsStock;
    private Date gmtCreate;
    private Date gmtModified;
}
