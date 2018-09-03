package com.ayl.seckil.result;

import lombok.Getter;
import lombok.ToString;

/**
 * @author AYL    2018/9/3 10:18
 */
@Getter
@ToString(callSuper = true)
public class Result<T> {
    private CodeMsg codeMsg;
    private T Data;

    private Result(CodeMsg codeMsg, T data) {
        this.codeMsg = codeMsg;
        Data = data;
    }

    private Result(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }

    public static <T> Result<T> success(T data) {
        return new Result(CodeMsg.SUCCESS, data);
    }

    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result(codeMsg);
    }
}
