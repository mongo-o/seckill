package com.ayl.seckil.result;

import lombok.Getter;
import lombok.ToString;
import org.apache.zookeeper.KeeperException;

/**
 * @author AYL    2018/9/3 10:11
 */
@Getter
@ToString(callSuper = true)
public class CodeMsg {
    private Integer code;
    private String errMsg;

    private CodeMsg(Integer code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    private void setCode(Integer code) {
        this.code = code;
    }

    private void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    //成功
    public static final CodeMsg SUCCESS = new CodeMsg(0000, "success");

    //失败
    public static final CodeMsg SERVER_ERROR = new CodeMsg(5001, "Server error: %s.");

    //登陆模块 100
    public static final CodeMsg USER_NOT_EXIST = new CodeMsg(1001, "用户不存在");
    public static final CodeMsg USER_PASSWORD_DISMATCHED = new CodeMsg(1002, "密码错误");
    public static final CodeMsg USER_NOT_LOGIN = new CodeMsg(1003, "用户未登陆");

    public final CodeMsg fillArgs(Object...args) {
        String msg = String.format(this.getErrMsg(),args);
        this.setErrMsg(msg);
        return this;
    }
}
