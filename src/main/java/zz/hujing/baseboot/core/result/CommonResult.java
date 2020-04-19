package zz.hujing.baseboot.core.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static zz.hujing.baseboot.core.result.CommonEnum.FORBIDDEN;


/**
 * @author hj
 * 2019-05-24 17:29
 */
@Data
@AllArgsConstructor
@Builder
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    //构造私有
    private CommonResult() {
        throw new IllegalStateException("you can't reflect this class Object.");
    }

    /**
     * 分页数据相关
     */
    private T data;

    @JsonInclude(NON_NULL) //为null时不序列化该字段
    private Long total;
    @JsonInclude(NON_NULL) //为null时不序列化该字段
    private Object extend;
    /**
     * 通用枚举信息
     */
    private int code;
    private String message;


    public static <T> CommonResult<T> success(T data) {
        return CommonResult.<T>builder()
                .data(data)
                .code(CommonEnum.SUCCESS.code())
                .message(CommonEnum.SUCCESS.tip())
                .build();
    }

    public static <T> CommonResult<T> success() {
        return CommonResult.<T>builder()
                .code(CommonEnum.SUCCESS.code())
                .message(CommonEnum.SUCCESS.tip())
                .build();
    }


    public static <T> CommonResult<T> fail() {
        return CommonResult.<T>builder()
                .code(CommonEnum.FAIL.code())
                .message(CommonEnum.FAIL.tip())
                .build();
    }

    public static <T> CommonResult<T> fail(String message) {
        return CommonResult.<T>builder()
                .data(null)
                .code(CommonEnum.FAIL.code())
                .message(message)
                .build();
    }

    /**
     * 自定义枚举信息的失败方法
     *
     * @param resultCode 公共枚举
     * @return CommonResult
     */
    public static <T> CommonResult<T>  fail(ResultCode resultCode) {
        return CommonResult.<T>builder()
                .code(resultCode.code())
                .message(resultCode.tip())
                .build();
    }

    public static <T> CommonResult<T> forbidden() {
        return CommonResult.<T>builder()
                .code(FORBIDDEN.code())
                .message(FORBIDDEN.tip())
                .build();
    }

    /**
     * 自定义枚举信息的失败方法
     *
     * @param resultCode 公共枚举
     * @return CommonResult
     */
    public static <T> CommonResult<T> fail(ResultCode resultCode, T data) {
        return CommonResult.<T>builder()
                .data(data)
                .code(resultCode.code())
                .message(resultCode.tip())
                .build();
    }

}
