package org.example;

import org.example.Calculator.StringCalculator;

public class Main {
    public static void main(String[] args){
        String line = "-1,-2";
        StringCalculator calc = new StringCalculator();
        System.out.println(calc.add(line));
    }
}
