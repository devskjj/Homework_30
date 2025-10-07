import domain.Order;

import java.util.Comparator;
import java.util.List;
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

        RestaurantOrders.printList(RestaurantOrders.findTopCost(orders, 5, descending));

        RestaurantOrders.printList(RestaurantOrders.findTopCost(orders, 3, ascending));
    }


}
