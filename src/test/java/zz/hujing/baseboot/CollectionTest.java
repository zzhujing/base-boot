package zz.hujing.baseboot;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 集合测试
 **/
public class CollectionTest {



    //LRU 最少最近策略
    public static void main(String[] args) throws InterruptedException {

        //ConCurrentModification example
        List<Integer> source = Lists.newCopyOnWriteArrayList();
        for (int i = 0; i < 10000; i++) {
            source.add(i);
        }
        Thread t1 = new Thread(() -> {
            source.forEach(i -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    //ignore
                }
            });
        });

        TimeUnit.MILLISECONDS.sleep(100);

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                source.add(i);
                System.out.println("add...");
            }
        });

        t1.start();
        t2.start();
        System.out.println("main over");
    }

    /**
     * //ConcurrentModificationException
     */
    @Test
    public void testRemoveListElement(){
        List<String> result = Lists.newArrayList("Golang", "Java", "Python");
        for (String l : result) {
            if ("Python".equalsIgnoreCase(l)) {
                result.remove(l);
            }
        }
    }

    @Test
    public void testComparator(){
        List<Integer> source = Arrays.asList(2, 5, 1);
        source.sort(Integer::compareTo);
        System.out.println(source);
    }
}
