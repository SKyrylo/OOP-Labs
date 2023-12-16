package org.example;

import org.example.Calculator.StringCalculator;

public class Main {
    public static void main(String[] args){
        String line = "//[\n-1\n2,-3[-4,-5";
        StringCalculator calc = new StringCalculator();
        System.out.println(calc.add(line));
    }
}
