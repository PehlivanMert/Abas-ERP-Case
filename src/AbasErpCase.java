import java.util.*;
import java.util.stream.*;


public class AbasErpCase {
    private static List<Order> orders = Arrays.asList(
            new Order(1000, 2000, 12, 100.51),
            new Order(1000, 2001, 31, 200.00),
            new Order(1000, 2002, 22, 150.86),
            new Order(1000, 2003, 41, 250.00),
            new Order(1000, 2004, 55, 244.00),
            new Order(1001, 2001, 88, 44.531),
            new Order(1001, 2002, 121, 88.11),
            new Order(1001, 2004, 100, 74.00),
            new Order(1001, 2002, 51, 211.00),
            new Order(1002, 2003, 14, 88.11),
            new Order(1002, 2004, 2, 12.10),
            new Order(1002, 2003, 3, 22.30),
            new Order(1002, 2002, 8, 12.10),
            new Order(1002, 2005, 16, 94.00),
            new Order(1002, 2006, 9, 44.10),
            new Order(1002, 2005, 19, 90.00)
    );

    public static void main(String[] args) {
        System.out.println("a. Üç siparişin toplam tutarları:");
        calculateTotalAmountPerOrder();

        System.out.println("\nb. Tüm malların ortalama fiyatı:");
        calculateOverallAveragePrice();

        System.out.println("\nc. Mal bazlı ortalama fiyatlar:");
        calculateAveragePricePerProduct();

        System.out.println("\nd. Mal bazlı sipariş miktarları:");
        calculateQuantityPerProductAndOrder();
    }

    // a. Üç siparişin toplam tutarları
    private static void calculateTotalAmountPerOrder() {
        Map<Integer, Double> totalAmounts = orders.stream()
                .collect(Collectors.groupingBy(o -> o.orderNumber,
                        Collectors.summingDouble(o -> o.quantity * o.unitPrice)));

        totalAmounts.forEach((orderNumber, total) ->
                System.out.printf("Sipariş %d: %.2f TL%n", orderNumber, total));
    }

    // b. Tüm malların ortalama fiyatı
    private static void calculateOverallAveragePrice() {
        double averagePrice = orders.stream()
                .mapToDouble(o -> o.unitPrice)
                .average()
                .orElse(0.0);

        System.out.printf("Ortalama fiyat: %.2f TL%n", averagePrice);
    }

    // c. Mal bazlı ortalama fiyatlar
    private static void calculateAveragePricePerProduct() {
        Map<Integer, Double> averagePrices = orders.stream()
                .collect(Collectors.groupingBy(o -> o.productNumber,
                        Collectors.averagingDouble(o -> o.unitPrice)));

        averagePrices.forEach((productNumber, avgPrice) ->
                System.out.printf("Mal %d: %.2f TL%n", productNumber, avgPrice));
    }

    // d. Mal bazlı sipariş miktarları
    private static void calculateQuantityPerProductAndOrder() {
        Map<Integer, Map<Integer, Integer>> quantityPerProductAndOrder = orders.stream()
                .collect(Collectors.groupingBy(o -> o.productNumber,
                        Collectors.groupingBy(o -> o.orderNumber,
                                Collectors.summingInt(o -> o.quantity))));

        quantityPerProductAndOrder.forEach((productNumber, orderQuantities) -> {
            System.out.printf("Mal %d:%n", productNumber);
            orderQuantities.forEach((orderNumber, quantity) ->
                    System.out.printf("  Sipariş %d: %d adet%n", orderNumber, quantity));
        });
    }
}