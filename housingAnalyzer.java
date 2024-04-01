import java.util.*;

class HousingSale {
    double price;
    double squareFootage;

    public HousingSale(double price, double squareFootage) {
        this.price = price;
        this.squareFootage = squareFootage;
    }

    public double getPrice() {
        return price;
    }

    public double getSquareFootage() {
        return squareFootage;
    }
}

public class housingAnalyzer {

    public static Map<String, Integer> countHousesByPriceRange(List<HousingSale> sales) {
        Map<String, Integer> countByPriceRange = new HashMap<>();
        for (HousingSale sale : sales) {
            String priceRange = getPriceRange(sale.price);
            countByPriceRange.put(priceRange, countByPriceRange.getOrDefault(priceRange, 0) + 1);
        }
        return countByPriceRange;
    }

    public static Map<String, Double> calculateAverageSquareFootageByPriceRange(List<HousingSale> sales) {
        Map<String, Double> avgSquareFootageByPriceRange = new HashMap<>();
        Map<String, Double> totalSquareFootageByPriceRange = new HashMap<>();
        Map<String, Integer> countByPriceRange = new HashMap<>();

        for (HousingSale sale : sales) {
            String priceRange = getPriceRange(sale.price);
            double squareFootage = sale.getSquareFootage();

            double totalSquareFootage = totalSquareFootageByPriceRange.getOrDefault(priceRange, 0.0);
            int count = countByPriceRange.getOrDefault(priceRange, 0);

            totalSquareFootageByPriceRange.put(priceRange, totalSquareFootage + squareFootage);
            countByPriceRange.put(priceRange, count + 1);
        }

        for (Map.Entry<String, Integer> entry : countByPriceRange.entrySet()) {
            String priceRange = entry.getKey();
            int count = entry.getValue();
            double totalSquareFootage = totalSquareFootageByPriceRange.get(priceRange);
            double avgSquareFootage = totalSquareFootage / count;
            avgSquareFootageByPriceRange.put(priceRange, avgSquareFootage);
        }

        return avgSquareFootageByPriceRange;
    }

    public static String getPriceRange(double price) {
        if (price < 100000) {
            return "<$100,000";
        } else if (price < 200000) {
            return "$100,000-$200,000";
        } else if (price < 300000) {
            return "$200,000-$300,000";
        } else {
            return ">$300,000";
        }
    }

    public static void main(String[] args) {
        // Sample housing sales data
        List<HousingSale> sales = new ArrayList<>();
        sales.add(new HousingSale(90000, 1200));
        sales.add(new HousingSale(150000, 1500));
        sales.add(new HousingSale(250000, 1800));
        sales.add(new HousingSale(320000, 2000));
        sales.add(new HousingSale(180000, 1600));

        // Count houses by price range
        Map<String, Integer> housesByPriceRange = countHousesByPriceRange(sales);

        // Calculate average square footage by price range
        Map<String, Double> avgSquareFootageByPriceRange = calculateAverageSquareFootageByPriceRange(sales);

        // Display results
        System.out.println("Number of houses sold by price range:");
        for (Map.Entry<String, Integer> entry : housesByPriceRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " houses");
        }

        System.out.println("\nAverage square footage for each price range:");
        for (Map.Entry<String, Double> entry : avgSquareFootageByPriceRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " square feet");
        }
    }
}
