package zz.hujing.baseboot.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author hj
 * 2019-05-27 8:45
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum CommonEnum implements ResultCode {

    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    FORBIDDEN(403,"没权限访问"),
    REDIS_NULL_RETURN(500,"redis执行返回null")
    ;

    private int code;
    private String tip;

    @Override
    public int code() {
        return code;
    }

    @Override
    public String tip() {
        return tip;
    }
}
