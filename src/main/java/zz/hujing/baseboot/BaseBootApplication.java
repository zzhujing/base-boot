package zz.hujing.baseboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zz.hujing.baseboot.core.result.CommonResult;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RestController
public class BaseBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseBootApplication.class, args);
    }

    @GetMapping("/echo")
    public CommonResult echo(){
        return CommonResult.success();
    }

}
