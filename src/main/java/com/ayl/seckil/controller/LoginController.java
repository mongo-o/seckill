package com.ayl.seckil.controller;

import com.ayl.seckil.result.Result;
import com.ayl.seckil.service.SeckillUserService;
import com.ayl.seckil.vo.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author AYL    2018/8/14 23:53
 */
@Controller
@ResponseBody
@RequestMapping("/login")
public class LoginController {
    @Autowired
    SeckillUserService seckillUserService;

    @RequestMapping(value = "/do_login", method = RequestMethod.POST)
    public Result login(HttpServletResponse response, @RequestBody @Valid UserLoginVo loginVo) {
        Result result = seckillUserService.doLogin(response, loginVo);

        return result;
    }
}
