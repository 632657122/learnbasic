package basic.learn.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangtao
 * @date 2019-01-08
 * @desc
 */
public class AtomicTest {

    public static AtomicInteger race = new AtomicInteger(0);

    public static void increase() {
        race.incrementAndGet();
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int n = 0; n < 10000; n++) {
                    increase();
                }
            });
            threads[i].start();
        }
        System.out.println(race);
    }
}
