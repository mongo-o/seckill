package com.ayl.seckil.controller;

import com.ayl.seckil.result.Result;
import com.ayl.seckil.service.GoodsService;
import com.ayl.seckil.vo.SeckillGoodsDetailVo;
import com.ayl.seckil.vo.SeckillGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author AYL    2018/9/3 23:38
 */
@Controller
@ResponseBody
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/list")
    public Result<List<SeckillGoodsVo>> listSeckillGoods( ) {
        return Result.success(goodsService.listSeckillGoods());
    }

    @RequestMapping("/detail/{goodsId}")
    public Result<SeckillGoodsDetailVo> getSeckillGoodsDetail(@PathVariable("goodsId") Long goodsId) {
        return Result.success(goodsService.getSeckillGoodsDetail(goodsId));
    }
}
