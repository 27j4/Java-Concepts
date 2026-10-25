import java.util.concurrent.*;

public class CompletableFuturePrac {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<String> myamefuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println(Thread.currentThread().getName());
            return "Anshul Pandey";
        }, executor).thenApply(name -> {
            return name.toUpperCase();
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
