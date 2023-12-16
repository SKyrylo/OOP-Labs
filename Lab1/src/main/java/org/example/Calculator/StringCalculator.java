package org.example.Calculator;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public int add(String numbers) throws IllegalArgumentException{
        int result = 0;

        if(numbers.startsWith("//") && numbers.charAt(3) == '\n'){
            char toReplace = numbers.charAt(2);
            numbers = numbers.substring(4).replace(toReplace, ',');
        }

        numbers = numbers.replace('\n', ',');

        if(numbers.contains(",,") || numbers.startsWith(",") || numbers.endsWith(",")){
            throw new IllegalArgumentException("Wrong input format");
        }

        String[] arr = numbers.split(",");

        if(numbers.isEmpty()){
            return 0;
        }

        List<Integer> negative = new ArrayList<>();
        boolean error = false;
        for (String s : arr) {
            try {
                if(Integer.parseInt(s) < 0){
                    negative.add(Integer.parseInt(s));
                    error = true;
                }
                result += Integer.parseInt(s);
            }
            catch (NumberFormatException ex) {
                throw new NumberFormatException("Wrong input type!");
            }
        }

        if(error){
            String nums = String.join(" ", String.valueOf(negative));
            throw new NumberFormatException("Negative numbers prohibited!\nList of passed negative numbers: "+nums);
        }
        return result;
    }
}
