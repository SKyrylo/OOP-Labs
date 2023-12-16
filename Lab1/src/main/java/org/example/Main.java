package org.example;

import org.example.Calculator.StringCalculator;

public class Main {
    public static void main(String[] args){
        String line = "//;\n1000,999;1001";
        StringCalculator calc = new StringCalculator();
        System.out.println(calc.add(line));
    }
}
