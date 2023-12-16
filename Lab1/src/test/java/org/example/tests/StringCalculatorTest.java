package org.example.tests;

import org.example.Calculator.StringCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    StringCalculator calc = new StringCalculator();

    @Test
    void emptyStringCheck() {
        assertEquals(0, calc.add(""));
    }

    @Test
    void singleNumberCheck(){
        assertEquals(1, calc.add("1"));
    }

    @Test
    void multipleNumberCheck(){
        assertEquals(3, calc.add("1,2"));
        assertEquals(28, calc.add("1,2,3,4,5,6,7"));
    }

    @Test
    void multipleDelimitersCheck(){
        assertEquals(6, calc.add("1,2\n3"));
    }

    @Test
    void patternCheck(){
        assertEquals(15, calc.add("//;\n1,2;3,4\n5"));
        assertEquals(6, calc.add("//[***]\n1***2***3"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"string",
            "1,2e",
            "1,\n",
            "/;\n1;2",
            "//;;\n4;;3",
            "//;\n4^4",
            "//[\n-1\n2,-3[-4,-5",
            "//[***]\n1***2****3",
            "//[***]]\n1***2***3"
    })
    void testForExceptions(String argument){
        assertThrows(Exception.class, () -> calc.add(argument));
    }
}