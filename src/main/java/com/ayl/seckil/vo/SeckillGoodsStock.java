package com.ayl.seckil.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;

/**
 * @author AYL    2018/9/4 22:23
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SeckillGoodsStock {
    private Long goodsId;
    private int stockCount;
}
