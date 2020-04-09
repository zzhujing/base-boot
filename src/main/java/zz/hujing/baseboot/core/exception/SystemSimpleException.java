package zz.hujing.baseboot.core.exception;

/**
 * @author hj
 * 2019-07-25 10:53
 */
public class SystemSimpleException extends RuntimeException {

    public SystemSimpleException(String msg, Throwable t) {
        super(msg, t);
    }

    public SystemSimpleException(String msg) {
        super(msg);
    }
}
