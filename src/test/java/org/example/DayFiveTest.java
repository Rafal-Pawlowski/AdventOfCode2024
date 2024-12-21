package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayFiveTest {

    @Test
    void pageOrderCorrectnessChecker() {

        DayFive dayFive = new DayFive();

        long l = dayFive.pageOrderCorrectnessChecker("src/main/resources/day_five_test");

        assertEquals(143, l);

    }
}