import java.util.List;
import java.util.concurrent.CompletableFuture;

class User {
    private final String name;
    private final Long id;

    public User(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}

class Order {
    private final String orderId;
    private final int quantity;
    private final Long userId;

    public Order(String orderId, int quantity, Long userId) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.userId = userId;
    }
}

class UserSummary {
    private final String name;
    private final Long id;
    private final List<Order> orders;
    private final Integer loyaltyPoints;

    public UserSummary(
            String name,
            Long id,
            List<Order> orders,
            Integer loyaltyPoints) {
        this.name = name;
        this.id = id;
        this.orders = orders;
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    @Override
    public String toString() {
        return "UserSummary{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", orderCount=" + orders.size() +
                ", loyaltyPoints=" + loyaltyPoints +
                '}';
    }
}

public class PracticeQues1 {

    CompletableFuture<User> fetchUser(Long id) {
        return CompletableFuture.completedFuture(
                new User("Anshul", id)
        );
    }

    CompletableFuture<List<Order>> fetchOrders(Long userId) {
        List<Order> orders = List.of(
                new Order("ORD123", 2, userId),
                new Order("ORD124", 3, userId)
        );

        return CompletableFuture.completedFuture(orders);
    }

    CompletableFuture<Integer> loyaltyPoints(Long userId) {
        return CompletableFuture.completedFuture(100);
    }

    public static void main(String[] args) {
        PracticeQues1 service = new PracticeQues1();

        CompletableFuture<UserSummary> summaryFuture =
                service.fetchUser(1L)
                        .thenCompose(user -> {
                            CompletableFuture<List<Order>> ordersFuture =
                                    service.fetchOrders(user.getId());

                            CompletableFuture<Integer> pointsFuture =
                                    service.loyaltyPoints(user.getId());

                            return ordersFuture.thenCombine(
                                    pointsFuture,
                                    (orders, points) ->
                                            new UserSummary(
                                                    user.getName(),
                                                    user.getId(),
                                                    orders,
                                                    points
                                            )
                            );
                        });

        // Blocking only at the final boundary to display the result.
        System.out.println(summaryFuture.join());
    }
}