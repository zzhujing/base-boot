package zz.hujing.baseboot.core.exception;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import zz.hujing.baseboot.core.result.CommonResult;

import javax.validation.ConstraintViolationException;

/**
 * @author hj
 * 2019-07-25 10:55
 */
@RestControllerAdvice
@Slf4j
public class SystemLGlobalExceptionHandler {

    private static final String SERVER_ERROR = "服务异常";

    @ExceptionHandler(SystemSimpleException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult handler(SystemSimpleException ex, HandlerMethod handlerMethod) {
        print(handlerMethod, ex);
        return CommonResult.fail(ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CommonResult handlerRunTimeException(ForbiddenException ex, HandlerMethod handlerMethod) {
        print(handlerMethod, ex);
        return CommonResult.forbidden();
    }

    @ExceptionHandler(InternalServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult handlerForbiddenException(InternalServiceException ex, HandlerMethod handlerMethod) {
        print(handlerMethod, ex);
        return CommonResult.fail(ex.getResultCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult handlerValidationException(ConstraintViolationException ex, HandlerMethod handlerMethod) {
        print(handlerMethod, ex);
        return CommonResult.fail(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult handlerValidationException(Exception ex, HandlerMethod handlerMethod) {
        print(handlerMethod, ex);
        return CommonResult.fail(SERVER_ERROR);
    }


    private void print(HandlerMethod hm, Exception e) {
        log.error("全局异常处理收到异常, msg : [{}] ", e.getMessage());
        log.error("{}:{} 异常 {}", hm.getBeanType().getName(), hm.getMethod().getName(), e.getMessage());
        log.debug("异常堆栈:{}", ExceptionUtils.getStackTrace(e));
    }
}
