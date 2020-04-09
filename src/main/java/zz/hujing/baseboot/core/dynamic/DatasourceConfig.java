package zz.hujing.baseboot.core.dynamic;

import com.google.common.collect.Maps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * TODO
 **/
@Configuration
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class DatasourceConfig {

    private final DynamicDataSourceProperties dynamicDataSourceProperties;

    public DatasourceConfig(DynamicDataSourceProperties dynamicDataSourceProperties) {
        this.dynamicDataSourceProperties = dynamicDataSourceProperties;
    }

    @Bean
    public DynamicDataSource dynamicDataSource() {
        Map<Object, Object> targetDataSource = Maps.newHashMap();
        dynamicDataSourceProperties.getDynamic().forEach((k, v) -> targetDataSource.put(DynamicDataSourceContext.DataSourceType.valueOf(k.trim().toUpperCase()), v.initializeDataSourceBuilder().type(v.getType()).build()));
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSource);
        dynamicDataSource.setDefaultTargetDataSource(targetDataSource.get(DynamicDataSourceContext.DataSourceType.MASTER));
        return dynamicDataSource;
    }
}
