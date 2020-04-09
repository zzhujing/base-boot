package zz.hujing.baseboot.core.exception;


import lombok.Getter;
import zz.hujing.baseboot.core.result.ResultCode;

@Getter
public  class InternalServiceException extends RuntimeException {
    private ResultCode resultCode;

    public InternalServiceException(ResultCode resultCode) {
        super(resultCode.tip());
        this.resultCode = resultCode;
    }

    public InternalServiceException(ResultCode resultCode, Throwable t) {
        super(resultCode.tip(), t);
        this.resultCode = resultCode;
    }
}
