import java.util.concurrent.*;

public class FuturePrac {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future<String> myamefuture = executor.submit(() -> {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName());
            return "Anshul Pandey";
        });

        System.out.println("Working on task 1");

        String myname;
        try {
            myname = myamefuture.get();
            System.out.println("Working on task 2 and my name is " + myname);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Task is completed");

        executor.shutdown();

    }
}