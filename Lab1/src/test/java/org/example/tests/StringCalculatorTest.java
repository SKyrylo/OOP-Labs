package org.example.tests;

import org.example.Calculator.StringCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    StringCalculator calc = new StringCalculator();

    @Test
    void add() {
        assertEquals(0, calc.add(""));
        assertEquals(1, calc.add("1"));
        assertEquals(3, calc.add("1,2"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"string", "1,2,3", "1,2e"})
    void testForExceptions(String argument){
        assertThrows(Exception.class, () -> calc.add(argument));
    }
}