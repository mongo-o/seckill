package com.ayl.seckil.exception;

import com.ayl.seckil.result.CodeMsg;
import com.ayl.seckil.result.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;

/**
 * @author AYL    2018/9/3 22:08
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static Logger logger = LogManager.getLogger(GlobalException.class);

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletResponse response, Exception e) {
        e.printStackTrace();
        logger.info(this, e);

        if (e instanceof GlobalException) {
            GlobalException exception = (GlobalException) e;
            return Result.error(exception.getCodeMsg());
        } else {
            return Result.error(CodeMsg.SERVER_ERROR.fillArgs(e.getMessage()));
        }
    }
}
