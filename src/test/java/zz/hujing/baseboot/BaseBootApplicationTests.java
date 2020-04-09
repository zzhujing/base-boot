package zz.hujing.baseboot;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zz.hujing.baseboot.core.utils.RedisCacheUtil;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
class BaseBootApplicationTests {

    @Autowired
    private RedisCacheUtil cacheUtil;


    @Test
    public void setCacheKV() {
        cacheUtil.setValue("k1", "v1", TimeUnit.MINUTES, 1);
        cacheUtil.setForHash("k2", "h2", "v2");
    }

    @Test
    public void getCache() {
        assertEquals(cacheUtil.getValue("k1"),"v1");
        assertEquals(cacheUtil.getForHash("k2","h2"),"v2");
    }

}
