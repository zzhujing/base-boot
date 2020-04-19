package zz.hujing.baseboot.core.dynamic;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 动态数据源拦截aop
 **/
@Aspect
@Component
@ConditionalOnProperty(prefix = "base.boot", name = "system", havingValue = "true")
public class DynamicDataSourceAspect {

    @Pointcut("execution(* zz.hujing.baseboot.controller..*.*(..))")
    public void dynamicDataSourcePointCut() {
    }

    @Before("dynamicDataSourcePointCut()")
    public void intercept(JoinPoint jp) {
        //获取当前方法上面的@DynamicDS注解
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        //若存在则切换为对应的数据源
        DynamicDS methodDynamicDS = methodSignature.getMethod().getAnnotation(DynamicDS.class);
        if (methodDynamicDS != null) {
            DynamicDataSourceContext.setDataSourceType(methodDynamicDS.value());
            return;
        }
        //不存在获取类上面的@Dynamic注解
        DynamicDS declaringOnClassDynamicDS = methodSignature.getMethod().getDeclaringClass().getAnnotation(DynamicDS.class);
        if (declaringOnClassDynamicDS == null) {
            //不存在设置为主库
            DynamicDataSourceContext.master();
        } else {
            //存在则设置为对应数据源
            DynamicDataSourceContext.setDataSourceType(declaringOnClassDynamicDS.value());
        }
    }
}
