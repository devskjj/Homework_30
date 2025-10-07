import domain.Order;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // это для занятия
//        var movieCollection = MovieCollection.readFromJson();

        // это для домашки
        // выберите любое количество заказов, какое вам нравится.

        var orders = RestaurantOrders.read("orders_100.json").getOrders();
        //var orders = RestaurantOrders.read("orders_1000.json").getOrders();
        //var orders = RestaurantOrders.read("orders_10_000.json").getOrders();

        // протестировать ваши методы вы можете как раз в этом файле (или в любом другом, в котором вам будет удобно)
        Comparator<Order> descending = Comparator.comparing(Order::getTotal).reversed();
        Comparator<Order> ascending = Comparator.comparing(Order::getTotal);

//        RestaurantOrders.printList(orders);

//        RestaurantOrders.printList(RestaurantOrders.findTopCost(orders, 5, descending));

//        RestaurantOrders.printList(RestaurantOrders.findTopCost(orders, 3, ascending));

//        RestaurantOrders.printList(RestaurantOrders.deliveryToHome(orders));

//        System.out.println(RestaurantOrders.topDeliveryOrders(orders, ascending));

//        System.out.println(RestaurantOrders.topDeliveryOrders(orders, descending));

//        RestaurantOrders.printList(RestaurantOrders.filterOrderTotal(orders));

//        System.out.println("Общая стоимость всех заказов: " + RestaurantOrders.sumOrderTotal(orders));

//        RestaurantOrders.getUniqueEmails(orders).forEach(System.out::println);

//        RestaurantOrders.getUniqueCustomerOrder(orders).forEach((name, order) -> {
//            System.out.println("Имя клиента: " + name);
//            order.forEach(System.out::println);
//        });

//        RestaurantOrders.getUniqueCustomerTotal(orders).forEach((name, order) -> {
//            System.out.println("Имя: " + name + ", Общий сумма заказов: " + order);
//        });

//        Comparator<Map.Entry<String, Double>> max = Map.Entry.comparingByValue();
//        Comparator<Map.Entry<String, Double>> min = Map.Entry.<String, Double>comparingByValue().reversed();
//
//        RestaurantOrders.getSumClient(orders, max).ifPresent(obj ->
//                System.out.println("Клиент с макс. суммой заказов: " + obj.getKey() + " = " + obj.getValue()));
//
//        RestaurantOrders.getSumClient(orders, min).ifPresent(obj ->
//                System.out.println("Клиент с мин. суммой заказов: " + obj.getKey() + " = " + obj.getValue()));

        RestaurantOrders.groupItemsByAmount(orders).forEach((name, amount) -> {
            System.out.println("Товар: " + name + ", Общее кол-во: " + amount);
        });


    }


}
