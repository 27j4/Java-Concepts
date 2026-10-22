
import java.util.concurrent.*;

public class ThenApply {

    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newSingleThreadExecutor();

        try {
            CompletableFuture<String> name =
                    getFirstName().thenApplyAsync(firstname -> {
                        System.out.println(
                                "Callback thread: " +
                                Thread.currentThread().getName()
                        );

                        return firstname + getLastName().join();
                    }, executor);

            System.out.println(
                    "Main thread: " +
                    Thread.currentThread().getName()
            );

            System.out.println(name.join());
        } finally {
            executor.shutdown();
        }
    }

    static CompletableFuture<String> getFirstName() {
        return CompletableFuture.completedFuture("Anshul");
    }

    static CompletableFuture<String> getLastName() {
        return CompletableFuture.completedFuture(" Pandey");
    }
}
