
import java.util.concurrent.CompletableFuture;

public class CompletableFutureBasics {
    public static void main(String[] args) {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            return "Anshul" + " Pandey";
        });

        // join() waits for the result and throws an unchecked CompletionException on failure.
        // get() waits for the result and throws checked exceptions.

        // System.out.println(cf.join());

        try {
            System.out.println(cf.get());
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
