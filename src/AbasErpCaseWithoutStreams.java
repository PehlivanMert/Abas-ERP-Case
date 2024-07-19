import java.util.*;

public class AbasErpCaseWithoutStreams {
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
        Map<Integer, Double> totalAmounts = new HashMap<>();

        for (Order order : orders) {
            double amount = order.quantity * order.unitPrice;
            totalAmounts.put(order.orderNumber,
                    totalAmounts.getOrDefault(order.orderNumber, 0.0) + amount);
        }

        for (Map.Entry<Integer, Double> entry : totalAmounts.entrySet()) {
            System.out.printf("Sipariş %d: %.2f TL%n", entry.getKey(), entry.getValue());
        }
    }

    // b. Tüm malların ortalama fiyatı
    private static void calculateOverallAveragePrice() {
        double totalPrice = 0;
        int count = 0;

        for (Order order : orders) {
            totalPrice += order.unitPrice;
            count++;
        }

        double averagePrice = count > 0 ? totalPrice / count : 0;
        System.out.printf("Ortalama fiyat: %.2f TL%n", averagePrice);
    }

    // c. Mal bazlı ortalama fiyatlar
    private static void calculateAveragePricePerProduct() {
        Map<Integer, List<Double>> pricesPerProduct = new HashMap<>();

        for (Order order : orders) {
            pricesPerProduct.computeIfAbsent(order.productNumber, k -> new ArrayList<>())
                    .add(order.unitPrice);
        }

        for (Map.Entry<Integer, List<Double>> entry : pricesPerProduct.entrySet()) {
            int productNumber = entry.getKey();
            List<Double> prices = entry.getValue();
            double averagePrice = calculateAverage(prices);
            System.out.printf("Mal %d: %.2f TL%n", productNumber, averagePrice);
        }
    }

    private static double calculateAverage(List<Double> numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return numbers.size() > 0 ? sum / numbers.size() : 0;
    }

    // d. Mal bazlı sipariş miktarları
    private static void calculateQuantityPerProductAndOrder() {
        Map<Integer, Map<Integer, Integer>> quantityPerProductAndOrder = new HashMap<>();

        for (Order order : orders) {
            quantityPerProductAndOrder.computeIfAbsent(order.productNumber, k -> new HashMap<>())
                    .merge(order.orderNumber, order.quantity, Integer::sum);
        }

        for (Map.Entry<Integer, Map<Integer, Integer>> productEntry : quantityPerProductAndOrder.entrySet()) {
            System.out.printf("Mal %d:%n", productEntry.getKey());
            for (Map.Entry<Integer, Integer> orderEntry : productEntry.getValue().entrySet()) {
                System.out.printf("  Sipariş %d: %d adet%n", orderEntry.getKey(), orderEntry.getValue());
            }
        }
    }
}