package zz.hujing.baseboot.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import java.util.Optional;

/**
 * 监听处理请求完毕事件
 **/
@Component
@Slf4j
public class ServletRequestListener implements ApplicationListener<ServletRequestHandledEvent> {
    @Override
    public void onApplicationEvent(ServletRequestHandledEvent event) {
        log.debug(
                "request  ->  {}\n " +
                        "execute method -> {}\n" +
                        "spend time -> {} ms\n" +
                        "exception msg -> {}", event.getRequestUrl(),event.getMethod(),event.getProcessingTimeMillis(), Optional.ofNullable(event.getFailureCause()).map(Throwable::getMessage).orElse(null));
    }
}
