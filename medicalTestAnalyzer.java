import java.util.*;

class MedicalTestResult {
    double resultValue;

    public MedicalTestResult(double resultValue) {
        this.resultValue = resultValue;
    }

    public double getResultValue() {
        return resultValue;
    }
}

public class medicalTestAnalyzer {

    public static Map<String, Integer> countPatientsByResultRange(List<MedicalTestResult> resultsList) {
        Map<String, Integer> countByResultRange = new HashMap<>();
        for (MedicalTestResult result : resultsList) {
            String resultRange = getResultRange(result.resultValue);
            countByResultRange.put(resultRange, countByResultRange.getOrDefault(resultRange, 0) + 1);
        }
        return countByResultRange;
    }

    public static Map<String, Double> calculateAverageValueByResultRange(List<MedicalTestResult> resultsList) {
        Map<String, Double> avgValueByResultRange = new HashMap<>();
        Map<String, Double> totalValueByResultRange = new HashMap<>();
        Map<String, Integer> countByResultRange = new HashMap<>();

        for (MedicalTestResult result : resultsList) {
            String resultRange = getResultRange(result.resultValue);
            double currentValue = result.getResultValue();

            double totalValue = totalValueByResultRange.getOrDefault(resultRange, 0.0);
            int count = countByResultRange.getOrDefault(resultRange, 0);

            totalValueByResultRange.put(resultRange, totalValue + currentValue);
            countByResultRange.put(resultRange, count + 1);
        }

        for (Map.Entry<String, Integer> entry : countByResultRange.entrySet()) {
            String resultRange = entry.getKey();
            int count = entry.getValue();
            double totalValue = totalValueByResultRange.get(resultRange);
            avgValueByResultRange.put(resultRange, totalValue / count);
        }

        return avgValueByResultRange;
    }

    public static String getResultRange(double resultValue) {
        if (resultValue < 10) {
            return "Normal";
        } else if (resultValue < 20) {
            return "Borderline";
        } else {
            return "High";
        }
    }

    public static void main(String[] args) {
        // Sample medical test results
        List<MedicalTestResult> resultsList = new ArrayList<>();
        resultsList.add(new MedicalTestResult(5));
        resultsList.add(new MedicalTestResult(15));
        resultsList.add(new MedicalTestResult(25));
        resultsList.add(new MedicalTestResult(8));
        resultsList.add(new MedicalTestResult(18));

        // Count patients by result range
        Map<String, Integer> patientsByResultRange = countPatientsByResultRange(resultsList);

        // Calculate average value by result range
        Map<String, Double> avgValueByResultRange = calculateAverageValueByResultRange(resultsList);

        // Display results
        System.out.println("Number of patients with results within each range:");
        for (Map.Entry<String, Integer> entry : patientsByResultRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " patients");
        }

        System.out.println("\nAverage value for each result range:");
        for (Map.Entry<String, Double> entry : avgValueByResultRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
