package zz.hujing.baseboot.core.exception;

import zz.hujing.baseboot.core.result.ResultCode;

/**
 * 权限异常
 **/
public class ForbiddenException extends InternalServiceException {

    public ForbiddenException(ResultCode resultCode) {
        super(resultCode);
    }

    public ForbiddenException(ResultCode resultCode, Throwable t) {
        super(resultCode, t);
    }
}
