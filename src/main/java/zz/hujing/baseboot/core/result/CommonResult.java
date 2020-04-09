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
public class CommonResult implements Serializable {

    private static final long serialVersionUID = 1L;


    //构造私有
    private CommonResult() {
        throw new IllegalStateException("you can't reflect this class Object.");
    }

    /**
     * 分页数据相关
     */
    private Object data;

    @JsonInclude(NON_NULL) //为null时不序列化该字段
    private Long total;
    @JsonInclude(NON_NULL) //为null时不序列化该字段
    private Object extend;
    /**
     * 通用枚举信息
     */
    private int code;
    private String message;


    public static CommonResult success(Object data) {
        return CommonResult.builder()
                .data(data)
                .code(CommonEnum.SUCCESS.code())
                .message(CommonEnum.SUCCESS.tip())
                .build();
    }

    public static CommonResult success() {
        return CommonResult.builder()
                .code(CommonEnum.SUCCESS.code())
                .message(CommonEnum.SUCCESS.tip())
                .build();
    }


    public static CommonResult fail() {
        return CommonResult.builder()
                .code(CommonEnum.FAIL.code())
                .message(CommonEnum.FAIL.tip())
                .build();
    }

    public static CommonResult fail(String message) {
        return CommonResult.builder()
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
    public static CommonResult fail(ResultCode resultCode) {
        return CommonResult.builder()
                .code(resultCode.code())
                .message(resultCode.tip())
                .build();
    }

    public static CommonResult forbidden() {
        return CommonResult.builder()
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
    public static CommonResult fail(ResultCode resultCode, Object data) {
        return CommonResult.builder()
                .data(data)
                .code(resultCode.code())
                .message(resultCode.tip())
                .build();
    }

}
