package ru.isemenov;

import ru.isemenov.annotations.AfterSuite;
import ru.isemenov.annotations.BeforeSuite;
import ru.isemenov.annotations.CsvSource;
import ru.isemenov.annotations.Test;

public class TestClass {

    @BeforeSuite
    public static void doBeforeSuiteMethod() {
        System.out.println("Выполняется метод doBeforeSuiteMethod c аннотацией @BeforeSuite");
    }

    @AfterSuite
    public static void doAfterSuiteMethod() {
        System.out.println("Выполняется метод doAfterSuiteMethod c аннотацией @AfterSuite");
    }

    @Test
    @CsvSource("10, Java, 20, true")
    public void doTestWithParameters(int firstIntValue, String name, int secondIntValue, boolean isOk) {
        System.out.printf("Выполняется метод doTestWithParameters с параметрами %d, %s, %d, %s \n",
                firstIntValue, name, secondIntValue, isOk);
    }

    @Test(priority = Test.TestPriority.PRIORITY_ONE)
    public void doTestMethodWithLowPriority() {
        System.out.println("Выполняется метод doTestMethodWithLowPriority c priority = 1");
    }

    @Test(priority = Test.TestPriority.PRIORITY_TEN)
    public void doTestMethodWithHighPriority() {
        System.out.println("Выполняется метод doTestMethodWithHighPriority c priority = 10");
    }

    @Test(priority = Test.TestPriority.PRIORITY_TWO)
    public void doTestMethodWithPriority2() {
        System.out.println("выполняется метод doTestMethodWithPriority2 с priority = 2");
    }

    @Test(priority = Test.TestPriority.PRIORITY_FOUR)
    public void doTestMethodWithPriority4() {
        System.out.println("выполняется метод doTestMethodWithPriority4 с priority = 4");
    }

    @Test(priority = Test.TestPriority.PRIORITY_TWO)
    public void doTestMethodWithPriorityTwo() {
        System.out.println("выполняется метод doTestMethodWithPriorityTwo с priority = 2");
    }

    @Test(priority = Test.TestPriority.PRIORITY_SEVEN)
    public void doTestMethodWithPriority7() {
        System.out.println("выполняется метод doTestMethodWithPriority7 с priority = 7");
    }

    @Test(priority = Test.TestPriority.PRIORITY_TWO)
    public void doTestMethodWithPriorityII() {
        System.out.println("выполняется метод doTestMethodWithPriorityII с priority = 2");
    }
}
