package zz.hujing.baseboot.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 基本脚手架BaseBoot核心配置类
 **/
@ConfigurationProperties(prefix = "base.boot.system")
@Component
@Data
public class BaseBootProperties {

    private boolean openDynamicDataSource = false;
}
