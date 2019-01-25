package basic.learn.multiThread.taskexec;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author wangtao
 * @date 2019-01-16
 * @desc
 */
public class TaskExecutionWebServer {

    private static final int NTHREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
    private static CountDownLatch countDownLatch = new CountDownLatch(5);

     ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,1000, TimeUnit.MINUTES, new LinkedBlockingDeque<>(20));

    public static void main(String[] args) throws IOException {
//        ServerSocket socket = new ServerSocket(80);
//        while (true) {
//            final Socket connection = socket.accept();
//            Runnable task = new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(connection);//do something
//                }
//            };
//            exec.execute(task);
//        }
        Thread thread = new Thread(() -> System.out.println("some"));
        countDownLatch.countDown();
        thread.setName("some_name");

    }
}
