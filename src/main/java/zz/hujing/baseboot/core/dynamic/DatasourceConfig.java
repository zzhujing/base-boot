package zz.hujing.baseboot.core.dynamic;

import com.google.common.collect.Maps;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Map;

/**
 * Dynamic DataSource Configuration
 **/
@Configuration
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
@ConditionalOnProperty(prefix = "base.boot", name = "system", havingValue = "true")
public class DatasourceConfig {

    private final DynamicDataSourceProperties dynamicDataSourceProperties;

    public DatasourceConfig(DynamicDataSourceProperties dynamicDataSourceProperties) {
        this.dynamicDataSourceProperties = dynamicDataSourceProperties;
    }

    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource() {
        Map<Object, Object> targetDataSource = Maps.newHashMap();
        dynamicDataSourceProperties.getDynamic().forEach((k, v) -> targetDataSource.put(DynamicDataSourceContext.DataSourceType.valueOf(k.trim().toUpperCase()), v.initializeDataSourceBuilder().type(v.getType()).build()));
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSource);
        dynamicDataSource.setDefaultTargetDataSource(targetDataSource.get(DynamicDataSourceContext.DataSourceType.MASTER));
        return dynamicDataSource;
    }
}
