package zz.hujing.baseboot.core.dynamic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface DynamicDS {

    DynamicDataSourceContext.DataSourceType value() default DynamicDataSourceContext.DataSourceType.MASTER;
}
