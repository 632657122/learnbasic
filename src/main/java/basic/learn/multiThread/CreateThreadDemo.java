package basic.learn.multiThread;

import java.util.concurrent.*;

/**
 * @author wangtao
 * @date 2018-12-24
 * @desc
 */
public class CreateThreadDemo {

    public static void main(String[] args) {
        Thread thread = new Thread() {
           @Override
           public void run() {
               System.out.println("Thread");
               super.run();
           }
        };

        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("runnable");
            }
        });

        thread1.start();

        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new Callable<String>() {
            public String call() throws Exception {
                return "callable";
            }
        });

        FutureTask<String> futureTask = new FutureTask<String>(new Callable(){

            public Object call() throws Exception {
                return "futureTask";
            }
        });
        service.submit(futureTask);


        try {
            String result = future.get();
            System.out.println(result);
            String result1 = futureTask.get();
            System.out.println(result1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
