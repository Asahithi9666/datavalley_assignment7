import java.util.*;

class WeatherData {
    double temperature;
    double humidity;

    public WeatherData(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }
}

public class weatherAnalyzer {

    public static Map<String, Integer> countDaysByTemperatureRange(List<WeatherData> weatherDataList) {
        Map<String, Integer> countByTemperatureRange = new HashMap<>();
        for (WeatherData data : weatherDataList) {
            String temperatureRange = getTemperatureRange(data.temperature);
            countByTemperatureRange.put(temperatureRange, countByTemperatureRange.getOrDefault(temperatureRange, 0) + 1);
        }
        return countByTemperatureRange;
    }

    public static Map<String, Double> calculateAverageHumidityByTemperatureRange(List<WeatherData> weatherDataList) {
        Map<String, Double> avgHumidityByTemperatureRange = new HashMap<>();
        Map<String, Integer> countByTemperatureRange = new HashMap<>();
        for (WeatherData data : weatherDataList) {
            String temperatureRange = getTemperatureRange(data.temperature);
            double currentHumidity = data.getHumidity();
            double totalHumidity = avgHumidityByTemperatureRange.getOrDefault(temperatureRange, 0.0) * countByTemperatureRange.getOrDefault(temperatureRange, 0);
            totalHumidity += currentHumidity;
            countByTemperatureRange.put(temperatureRange, countByTemperatureRange.getOrDefault(temperatureRange, 0) + 1);
            avgHumidityByTemperatureRange.put(temperatureRange, totalHumidity / countByTemperatureRange.get(temperatureRange));
        }
        return avgHumidityByTemperatureRange;
    }

    public static String getTemperatureRange(double temperature) {
        if (temperature < 0) {
            return "<0°C";
        } else if (temperature < 10) {
            return "0-10°C";
        } else if (temperature < 20) {
            return "10-20°C";
        } else {
            return ">20°C";
        }
    }

    public static void main(String[] args) {
        // Sample weather data
        List<WeatherData> weatherDataList = new ArrayList<>();
        weatherDataList.add(new WeatherData(-5, 80));
        weatherDataList.add(new WeatherData(5, 70));
        weatherDataList.add(new WeatherData(15, 65));
        weatherDataList.add(new WeatherData(25, 75));
        weatherDataList.add(new WeatherData(18, 60));

        // Count days by temperature range
        Map<String, Integer> daysByTemperatureRange = countDaysByTemperatureRange(weatherDataList);

        // Calculate average humidity by temperature range
        Map<String, Double> avgHumidityByTemperatureRange = calculateAverageHumidityByTemperatureRange(weatherDataList);

        // Display results
        System.out.println("Days with temperatures within each range:");
        for (Map.Entry<String, Integer> entry : daysByTemperatureRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " days");
        }

        System.out.println("\nAverage humidity for each temperature range:");
        for (Map.Entry<String, Double> entry : avgHumidityByTemperatureRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "%");
        }
    }
}
