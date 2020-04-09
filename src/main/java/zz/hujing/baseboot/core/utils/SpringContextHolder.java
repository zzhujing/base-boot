package zz.hujing.baseboot.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContext依赖查找工具类
 **/
@Component
public class SpringContextHolder implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> beanClass, String beanName) {
        return applicationContext.getBean(beanClass, beanName);
    }

    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }
}
