package com.ayl.seckil.controller;

import com.ayl.seckil.domain.SeckillUser;
import com.ayl.seckil.result.Result;
import com.ayl.seckil.needlogin.NeedLogin;
import com.ayl.seckil.vo.SeckillGoodsVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author AYL    2018/9/3 23:38
 */
@Controller
@ResponseBody
@RequestMapping("/goods")
@NeedLogin
public class GoodsController {

    @RequestMapping("/list")
    public String listSeckillGoods( ) {

        return "success";
    }
}
