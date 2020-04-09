package zz.hujing.baseboot.core.dynamic;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * TODO
 **/
@ConfigurationProperties("spring.datasource")
@Data
public class DynamicDataSourceProperties {

    private Map<String, DataSourceProperties> dynamic;

}
