package zz.hujing.baseboot.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hujing
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "phone_id", type = IdType.AUTO)
    private Integer phoneId;

    /**
     * 号码
     */
    private String phonePhone;

    /**
     * 验证码
     */
    private String phoneCode;

    /**
     * 验证码发送时间
     */
    private LocalDateTime phoneTime;


}
