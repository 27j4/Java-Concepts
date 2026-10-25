import java.util.concurrent.*;

public class CompletableFutureDemoNB {
    public static void main(String[] args) {
        CompletableFuture<String> future =
            CompletableFuture.supplyAsync(() -> {
                sleep(3000);
                return "Data received";
            });

        System.out.println("1. Task started");

        future.thenAccept(result ->
            System.out.println("3. " + result)
        );

        System.out.println("2. Main continues working");

        // Wait only at the end so the demo reliably prints the result
        future.join();
        sleep(100); // Demo only: allow the callback to finish printing
    }

    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}