package ru.isemenov;

import java.util.*;

import static ru.isemenov.HomeworkSolution.*;


public class TaskRunner {
    private static final List<Integer> testData = Arrays.asList(1, 1, 2, 2, 5, 5, 5, 7, 9, 9);
    private static final List<Integer> testDataForTask2Fail = Arrays.asList(1, 2);
    private static final List<Integer> testDataForTask3Fail = Arrays.asList(3, 3, 3, 5, 5, 3, 3, 5);
    private static final List<String> testDataForTask6 = Arrays.asList("в", "лесу", "родилась", "елочка");
    private static final String testDataForTask7 = "в лесу родилась елочка в лесу она росла";
    private static final List<String> testDataForTask8 = Arrays.asList("в", "лесу", "родилась", "елочка", "в", "лесу", "она", "росла", "зимой", "и", "летом");
    private static final String[] testDataForTask9 = new String[]{
            "Один два три четрые пять",
            "шесть семь восемь девять десять",
            "ааа бббб вввввввввввввв ггг дддд ее"
    };

    public static void main(String[] args) {

        System.out.println("Task 1: " + doTask1(testData));

        System.out.println("Task 2: " + doTask2(testData));

        try {
            System.out.println(doTask2(testDataForTask2Fail));
        } catch (RuntimeException e) {
            System.out.println("Task 2: было выброшено исключение: "
                    + e.getClass().getSimpleName() + ", " + e.getMessage());
        }

        System.out.println("Task 3: " + doTask3(testData));

        try {
            System.out.println(doTask3(testDataForTask3Fail));
        } catch (RuntimeException e) {
            System.out.println("Task 3: было выброшено исключение: "
                    + e.getClass().getSimpleName() + ", " + e.getMessage());
        }

        System.out.println("Task 4: " + doTask4(getTestDataForTask4AndTask5()));

        System.out.println("Task 5: " + doTask5(getTestDataForTask4AndTask5()));

        System.out.println("Task 6: " + doTask6(testDataForTask6));

        Map<String, Long> taskResult = doTask7(testDataForTask7);
        System.out.println("Task 7:");
        for (Map.Entry<String, Long> entry : taskResult.entrySet()) {
            System.out.println("Слово [" + entry.getKey() + "] встречается " + entry.getValue() + " раз.");
        }

        System.out.println("Task 8: ");
        doTask8(testDataForTask8);

        System.out.println("Task 9: " + doTask9(testDataForTask9));
    }

    private static List<Employee> getTestDataForTask4AndTask5() {
        return List.of(
                new Employee("Bob1", 25, "Manager"),
                new Employee("Bob2", 30, "Driver"),
                new Employee("OldBob", 60, "Engineer"),
                new Employee("Bob3", 40, "Engineer"),
                new Employee("Bob4", 32, "Manager"),
                new Employee("Bob5", 55, "Driver"),
                new Employee("VeryOldBob", 80, "Engineer"),
                new Employee("SuperOldBob", 100, "Engineer"),
                new Employee("Bob6", 20, "Engineer")
        );
    }
}


