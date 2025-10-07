
import com.google.gson.Gson;
import domain.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class RestaurantOrders {
    // Этот блок кода менять нельзя! НАЧАЛО!
    private List<Order> orders;

    private RestaurantOrders(String fileName) {
        var filePath = Path.of("data", fileName);
        Gson gson = new Gson();
        try {
            orders = List.of(gson.fromJson(Files.readString(filePath), Order[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RestaurantOrders read(String fileName) {
        var ro = new RestaurantOrders(fileName);
        ro.getOrders().forEach(Order::calculateTotal);
        return ro;
    }

    public List<Order> getOrders() {
        return orders;
    }
    // Этот блок кода менять нельзя! КОНЕЦ!

    //----------------------------------------------------------------------
    //------   Реализация ваших методов должна быть ниже этой линии   ------
    //----------------------------------------------------------------------

    // Наполните этот класс решением домашнего задания.
    // Вам необходимо создать все необходимые методы
    // для решения заданий из домашки :)
    // вы можете добавлять все необходимые imports
    //
    public static void printList(List<Order> orders) {
        orders.forEach(System.out::println);
    }

    public static List<Order> findTopCost(List<Order> orders, int n, Comparator<Order> comp) {
        return orders.stream()
                .sorted(comp)
                .limit(n)
                .collect(Collectors.toList());
    }

    public static List<Order> deliveryToHome(List<Order> orders) {
        return orders.stream()
                .filter(Order::isHomeDelivery)
                .collect(Collectors.toList());
    }

    public static Optional<Order> topDeliveryOrders(List<Order> orders, Comparator<Order> comp) {
        return orders.stream()
                .filter((Order::isHomeDelivery))
                .max(comp);
    }

    public static List<Order> filterOrderTotal(List<Order> orders) {
        double maxTotal = findTopCost(orders, 1, Comparator.comparing(Order::getTotal).reversed()).get(0).getTotal();
        double minTotal = findTopCost(orders, 1, Comparator.comparing(Order::getTotal)).get(0).getTotal();

        return orders.stream()
                .filter(order -> order.getTotal() < maxTotal && order.getTotal() > minTotal)
                .sorted(Comparator.comparing(Order::getTotal).reversed())
                .collect(Collectors.toList());
    }

    public static double sumOrderTotal(List<Order> orders) {
        return orders.stream().mapToDouble(Order::getTotal).sum();
    }

    public static List<String> getUniqueEmails(List<Order> orders) {
        return orders.stream()
                .map(order -> order.getCustomer().getEmail())
                .distinct()
                .collect(toList());
    }

    public static Map<String, List<Order>> getUniqueCustomerOrder(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer().getFullName()));
    }

    public static Map<String, Double> getUniqueCustomerTotal(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer().getFullName(),
                        Collectors.summingDouble(Order::getTotal)));

    }

    public static Optional<Map.Entry<String, Double>> getMaxSumClient(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer().getFullName(),
                        Collectors.summingDouble(Order::getTotal)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
    }

    public static Optional<Map.Entry<String, Double>> getMinSumClient(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer().getFullName(),
                        Collectors.summingDouble(Order::getTotal)))
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue());
    }
}
