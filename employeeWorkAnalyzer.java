import java.util.*;

class EmployeeWorkHours {
    int employeeId;
    double[] hoursWorkedPerDay;

    public EmployeeWorkHours(int employeeId, double[] hoursWorkedPerDay) {
        this.employeeId = employeeId;
        this.hoursWorkedPerDay = hoursWorkedPerDay;
    }

    public double getTotalHoursWorked() {
        double totalHours = 0;
        for (double hours : hoursWorkedPerDay) {
            totalHours += hours;
        }
        return totalHours;
    }
}

public class employeeWorkAnalyzer {

    public static Map<String, Integer> countEmployeesByWorkHours(List<EmployeeWorkHours> workHoursList) {
        Map<String, Integer> countByWorkHours = new HashMap<>();
        for (EmployeeWorkHours workHours : workHoursList) {
            double totalHours = workHours.getTotalHoursWorked();
            String hoursRange = getHoursRange(totalHours);
            countByWorkHours.put(hoursRange, countByWorkHours.getOrDefault(hoursRange, 0) + 1);
        }
        return countByWorkHours;
    }

    public static Map<String, Double> calculateAverageHoursPerDayByWorkHours(List<EmployeeWorkHours> workHoursList) {
        Map<String, Double> avgHoursPerDayByWorkHours = new HashMap<>();
        Map<String, Double> totalHoursByWorkHours = new HashMap<>();
        Map<String, Integer> countByWorkHours = new HashMap<>();

        for (EmployeeWorkHours workHours : workHoursList) {
            double totalHours = workHours.getTotalHoursWorked();
            String hoursRange = getHoursRange(totalHours);

            double totalHoursForRange = totalHoursByWorkHours.getOrDefault(hoursRange, 0.0);
            int countForRange = countByWorkHours.getOrDefault(hoursRange, 0);

            for (double hours : workHours.hoursWorkedPerDay) {
                totalHoursForRange += hours;
            }

            totalHoursByWorkHours.put(hoursRange, totalHoursForRange);
            countByWorkHours.put(hoursRange, countForRange + 1);
        }

        for (Map.Entry<String, Integer> entry : countByWorkHours.entrySet()) {
            String hoursRange = entry.getKey();
            double totalHoursForRange = totalHoursByWorkHours.get(hoursRange);
            int countForRange = entry.getValue();
            double avgHoursPerDay = totalHoursForRange / (countForRange * 7); // Assuming 7 days in a week
            avgHoursPerDayByWorkHours.put(hoursRange, avgHoursPerDay);
        }

        return avgHoursPerDayByWorkHours;
    }

    public static String getHoursRange(double totalHours) {
        if (totalHours > 40) {
            return "More than 40 hours";
        } else if (totalHours == 40) {
            return "Exactly 40 hours";
        } else {
            return "Less than 40 hours";
        }
    }

    public static void main(String[] args) {
        // Sample employee work hours
        List<EmployeeWorkHours> workHoursList = new ArrayList<>();
        workHoursList.add(new EmployeeWorkHours(1, new double[]{8, 8, 8, 8, 8})); // Total 40 hours
        workHoursList.add(new EmployeeWorkHours(2, new double[]{7, 7, 7, 7, 7})); // Total 35 hours
        workHoursList.add(new EmployeeWorkHours(3, new double[]{9, 9, 8, 8, 8})); // Total 42 hours

        // Count employees by work hours
        Map<String, Integer> employeesByWorkHours = countEmployeesByWorkHours(workHoursList);

        // Calculate average hours per day by work hours
        Map<String, Double> avgHoursPerDayByWorkHours = calculateAverageHoursPerDayByWorkHours(workHoursList);

        // Display results
        System.out.println("Number of employees by work hours:");
        for (Map.Entry<String, Integer> entry : employeesByWorkHours.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " employees");
        }

        System.out.println("\nAverage hours per day by work hours:");
        for (Map.Entry<String, Double> entry : avgHoursPerDayByWorkHours.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " hours");
        }
    }
}
