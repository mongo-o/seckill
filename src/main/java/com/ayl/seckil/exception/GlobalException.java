package com.ayl.seckil.exception;

import com.ayl.seckil.result.CodeMsg;
import lombok.Getter;

/**
 * @author AYL    2018/9/3 22:01
 */
@Getter
public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = -8814423804714815389L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.getErrMsg());
        this.codeMsg = codeMsg;
    }
}
