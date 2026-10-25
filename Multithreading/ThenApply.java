
import java.util.concurrent.*;

public class ThenApply {

    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newSingleThreadExecutor();

        try {
            CompletableFuturePrac<String> name =
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

    static CompletableFuturePrac<String> getFirstName() {
        return CompletableFuturePrac.completedFuture("Anshul");
    }

    static CompletableFuturePrac<String> getLastName() {
        return CompletableFuturePrac.completedFuture(" Pandey");
    }
}
