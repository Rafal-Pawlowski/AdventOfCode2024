package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DayFourTest {



@Test
void wordSearch() {

        DayFour df = new DayFour();
        Long result = df.wordSearch("src/main/resources/day_four_test");

        Assertions.assertEquals(18, result);

    }
}