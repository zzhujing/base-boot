package zz.hujing.baseboot;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.test.context.junit4.SpringRunner;
import zz.hujing.baseboot.core.dynamic.DynamicDS;
import zz.hujing.baseboot.core.dynamic.DynamicDataSourceContext;
import zz.hujing.baseboot.core.utils.RedisCacheUtil;
import zz.hujing.baseboot.domain.Phone;
import zz.hujing.baseboot.service.PhoneService;

import java.sql.SQLOutput;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.COMPLETABLE_FUTURE;
import static org.junit.Assert.assertEquals;

//@SpringBootTest
//@RunWith(SpringRunner.class)
class BaseBootApplicationTests {

    @Autowired
    private PhoneService phoneService;

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void batchSave() {
        int capacity = 50000;
        List<Phone> source = Lists.newArrayListWithCapacity(capacity);
        for (int i = 0; i < capacity; i++) {
            source.add(Phone.builder().phonePhone("123456" + i).phoneCode(RandomUtil.randomNumbers(6)).build());
        }
        long startTime = System.currentTimeMillis();
        phoneService.saveBatch(source, capacity / 20);
        System.out.println(String.format("直接saveBatch()花费时间：%s ms", System.currentTimeMillis() - startTime));
    }

    @Test
    public void currencyBatchSave() {
        int capacity = 50000;
        int eachCapacity = 1000;
        List<Phone> source = Lists.newArrayListWithCapacity(capacity);
        for (int i = 0; i < capacity; i++) {
            source.add(Phone.builder().phonePhone("123456" + i).phoneCode(RandomUtil.randomNumbers(6)).build());
        }
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        long startTime = System.currentTimeMillis();
        //分线程进行批量添加
        List<? extends Future<?>> futureList = IntStream.rangeClosed(1, capacity / eachCapacity)
                .mapToObj(i -> executorService.submit(() -> {
                    System.out.println(Thread.currentThread().getName());
                    phoneService.saveBatch(source.subList((i - 1) * eachCapacity, Math.min(i * eachCapacity, capacity)),eachCapacity);
                })).collect(Collectors.toList());
        futureList.forEach(this::exec);
        System.out.println("花费时间:" + (System.currentTimeMillis() - startTime) + "ms");
        executorService.shutdown();
    }

    private void exec(Future<?> future) {
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private RedisCacheUtil cacheUtil;


    @Test
    public void setCacheKV() {
        cacheUtil.setValue("k1", "v1", TimeUnit.MINUTES, 1);
        cacheUtil.setForHash("k2", "h2", "v2");
    }

    @Test
    public void getCache() {
        assertEquals(cacheUtil.getValue("k1"), "v1");
        assertEquals(cacheUtil.getForHash("k2", "h2"), "v2");
    }

    @Test
    public void test1() {
        String s = "Hello";
        String s1 = new String("Hello");
        System.out.println(s == s1);
    }

    @Test
    public void testStringTokenizer() {
        StringTokenizer stringTokenizer = new StringTokenizer("Hello,World", ",");
        while (stringTokenizer.hasMoreTokens()) {
            System.out.println(stringTokenizer.nextToken());
        }
    }

}
