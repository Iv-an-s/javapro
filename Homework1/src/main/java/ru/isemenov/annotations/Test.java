package ru.isemenov.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

    TestPriority priority() default TestPriority.PRIORITY_FIVE;

    enum TestPriority{
        PRIORITY_ONE(1),
        PRIORITY_TWO(2),
        PRIORITY_THREE(3),
        PRIORITY_FOUR(4),
        PRIORITY_FIVE(5),
        PRIORITY_SIX(6),
        PRIORITY_SEVEN(7),
        PRIORITY_EIGHT(8),
        PRIORITY_NINE(9),
        PRIORITY_TEN(10);

        private final int value;

        TestPriority(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
