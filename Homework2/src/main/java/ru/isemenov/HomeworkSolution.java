package ru.isemenov;

import java.util.*;
import java.util.stream.Collectors;

public class HomeworkSolution {

    // 1. Реализовать удаление из листа всех дубликатов
    public static List<?> doTask1(List<?> list) {
        return list.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    // 2. Найти в списке целых чисел третье наибольшее число
    public static Integer doTask2(List<Integer> list) {
        return list.stream()
                .sorted(Collections.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Список содержит менее 3 элементов"));
    }

    // 3. Найти в списке целых чисел третье наибольшее уникальное число (Т.е. повторы считать за одно число).
    public static Integer doTask3(List<Integer> list) {
        return list.stream()
                .distinct()
                .sorted(Collections.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Список содержит менее 3 чисел (если игнорировать повторы)"));
    }

    // 4. Имеется список объектов типа Сотрудник (имя, возраст, должность),
    // Необходимо получить список имен 3 самых старших сотрудников с должностью "Инженер"
    public static List<String> doTask4(List<Employee> list) {
        return list.stream()
                .filter(employee -> "Engineer".equals(employee.getPosition()))
                .sorted((e1, e2) -> Integer.compare(e2.getAge(), e1.getAge()))
                .limit(3)
                .map(Employee::getName)
                .collect(Collectors.toList());
    }

    // 5. Имеется список объектов типа Сотрудник (имя, возраст, должность)
    // необходимо посчитать средний возраст сотрудников с дожностью "Инженер"
    public static Double doTask5(List<Employee> list) {
        return list.stream()
                .filter(e -> "Engineer".equals(e.getPosition()))
                .collect(Collectors.averagingInt(Employee::getAge));
    }

    // 6. Найдите в списке слов самое длинное
    public static String doTask6(List<String> list) {
        return list.stream()
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(() -> new RuntimeException("Список пустой"));
    }

    // 7. Имеется строка с набором слов в нижнем регистре, разделенных пробелом.
    // Постройте хеш-мапу, в которой будут храниться пары: слово - сколько раз оно встречается во
    // входной строке
    public static Map<String, Long> doTask7(String string) {
        return Arrays.stream(string.split(" "))
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

    // 8. Отпечатайте в консоль строки из списка в порядке увеличения длины слова.
    // Если слова имеют одинаковую длину, то должен быть сохранен алфавитный порядок
    public static void doTask8(List<String> list) {
        list.stream()
                .sorted((s1, s2) -> s1.length() == s2.length() ? s1.compareTo(s2) : Integer.compare(s1.length(), s2.length()))
                .forEach(System.out::println);
    }

    // 9. Имеется массив строк, в каждой из которых лежит набор из 5 слов, разделенных пробелом.
    // Найдите среди всех слов самое длинное, а если таких слов несколько - получите любое из них.
    public static String doTask9(String[] string) {
        return Arrays.stream(string)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .max(Comparator.comparingInt(String::length)).orElseThrow(() -> new RuntimeException("something went wrong.."));
    }
}
