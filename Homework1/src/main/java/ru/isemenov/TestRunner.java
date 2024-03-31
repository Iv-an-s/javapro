package ru.isemenov;

import ru.isemenov.annotations.AfterSuite;
import ru.isemenov.annotations.BeforeSuite;
import ru.isemenov.annotations.CsvSource;
import ru.isemenov.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;


public class TestRunner {

    public static void runTests(Class<?> c) {
        Object object = createInstance(c);
        Method[] methods = c.getDeclaredMethods();

        Method beforeSuiteAnnotatedMethod = checkBeforeSuiteAnnotatedMethodIsAloneAndStatic(methods);
        Method afterSuiteAnnotetedMethod = checkAfterSuiteAnnotatedMethodIsAloneAndStatic(methods);

        runMethod(beforeSuiteAnnotatedMethod, object);
        runTestMethods(methods, object);
        runMethod(afterSuiteAnnotetedMethod, object);
    }

    private static void runTestMethods(Method[] methods, Object object) {
        TreeMap<Integer, List<Method>> methodsMap = new TreeMap<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test annotation = method.getAnnotation(Test.class);
                Integer priority = annotation.priority().getValue();
                List<Method> methodsList = methodsMap.get(priority);
                if (methodsList == null) {
                    methodsList = new ArrayList<>();
                }
                methodsList.add(method);
                methodsMap.put(priority, methodsList);
            }
        }

        for (Integer key : methodsMap.descendingKeySet()) {
            List<Method> list = methodsMap.get(key);
            for (Method method : list) {
                runMethod(method, object);
            }
        }
    }

    private static Object createInstance(Class<?> c) {
        Constructor<?> con;
        Object object;
        try {
            con = c.getConstructor();
            object = con.newInstance();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Ошибка при получении конструктора без аргументов", e);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Ошибка при создании объекта", e);
        }
        return object;
    }

    private static void runMethod(Method method, Object object) {
        if (method != null) {
            try {
                if (method.isAnnotationPresent(CsvSource.class)) {
                    runMethodWithParams(method, object);
                } else {
                    method.invoke(object);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Ошибка вызова метода " + method.getName(), e);
            }
        }
    }

    private static void runMethodWithParams(Method method, Object object) throws InvocationTargetException, IllegalAccessException {
        CsvSource annotation = method.getAnnotation(CsvSource.class);
        String[] params = annotation.value().split(",");
        int firstParam = Integer.parseInt(params[0].trim());
        String secondParam = params[1].trim();
        int thirdParam = Integer.parseInt(params[2].trim());
        boolean fourthParam = Boolean.parseBoolean(params[3].trim().toLowerCase());

        method.invoke(object, firstParam, secondParam, thirdParam, fourthParam);
    }

    private static Method checkAfterSuiteAnnotatedMethodIsAloneAndStatic(Method[] methods) {
        Method result = null;
        int count = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterSuite.class)) {
                count++;
                if (count > 1) {
                    throw new RuntimeException("Методов с аннотацией @AfterSuite не может быть больше одного");
                }
                if (!Modifier.isStatic(method.getModifiers())) {
                    throw new RuntimeException("Аннотация @AfterSuite применима только к статическим методам. Метод " + method.getName() + " не является статическим.");
                }
                result = method;
            }
        }
        return result;
    }

    private static Method checkBeforeSuiteAnnotatedMethodIsAloneAndStatic(Method[] methods) {
        Method result = null;
        int count = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                count++;
                if (count > 1) {
                    throw new RuntimeException("Методов с аннотацией @BeforeSuite не может быть больше одного");
                }
                if (!Modifier.isStatic(method.getModifiers())) {
                    throw new RuntimeException("Аннотация @AfterSuite применима только к статическим методам. Метод " + method.getName() + " не является статическим.");
                }
                result = method;
            }
        }
        return result;
    }
}