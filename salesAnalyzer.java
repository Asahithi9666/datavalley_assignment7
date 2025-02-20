import java.util.*;

class ProductSale {
    double price;
    int quantity;

    public ProductSale(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalRevenue() {
        return price * quantity;
    }
}

public class salesAnalyzer {

    public static Map<String, Integer> countProductsByPriceRange(List<ProductSale> sales) {
        Map<String, Integer> countByPriceRange = new HashMap<>();
        for (ProductSale sale : sales) {
            String priceRange = getPriceRange(sale.price);
            countByPriceRange.put(priceRange, countByPriceRange.getOrDefault(priceRange, 0) + sale.quantity);
        }
        return countByPriceRange;
    }

    public static Map<String, Double> calculateRevenueByPriceRange(List<ProductSale> sales) {
        Map<String, Double> revenueByPriceRange = new HashMap<>();
        for (ProductSale sale : sales) {
            String priceRange = getPriceRange(sale.price);
            revenueByPriceRange.put(priceRange, revenueByPriceRange.getOrDefault(priceRange, 0.0) + sale.getTotalRevenue());
        }
        return revenueByPriceRange;
    }

    public static String getPriceRange(double price) {
        if (price < 50) {
            return "$0-50";
        } else if (price < 100) {
            return "$50-100";
        } else if (price < 200) {
            return "$100-200";
        } else {
            return "Above $200";
        }
    }

    public static void main(String[] args) {
        // Sample product sales
        List<ProductSale> sales = new ArrayList<>();
        sales.add(new ProductSale(25, 3));
        sales.add(new ProductSale(75, 2));
        sales.add(new ProductSale(150, 5));
        sales.add(new ProductSale(210, 1));

        // Count products by price range
        Map<String, Integer> productCounts = countProductsByPriceRange(sales);

        // Calculate revenue by price range
        Map<String, Double> revenueByPriceRange = calculateRevenueByPriceRange(sales);

        // Display results
        System.out.println("Products sold by price range:");
        for (Map.Entry<String, Integer> entry : productCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " products");
        }

        System.out.println("\nRevenue generated by price range:");
        for (Map.Entry<String, Double> entry : revenueByPriceRange.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }
}
