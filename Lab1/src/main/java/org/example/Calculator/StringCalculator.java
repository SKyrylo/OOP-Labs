package org.example.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers) throws IllegalArgumentException{
        int result = 0;

        if(numbers.startsWith("//")){
            if(numbers.charAt(3) == '\n'){
                char toReplace = numbers.charAt(2);
                numbers = numbers.substring(4).replace(toReplace, ',');
            }
            else{
                Pattern pattern = Pattern.compile("//\\[([^]]+)]\\n([\\s\\S]*)");
                Matcher matcher = pattern.matcher(numbers);
                if(matcher.matches()){
                    String customDelimiter = numbers.substring(3, numbers.indexOf("]\n"));
                    numbers = numbers.substring(numbers.indexOf("]\n")+2);
                    numbers = numbers.replace(customDelimiter, ",");
                }
                else{
                    throw new IllegalArgumentException("Wrong delimiter argument!");
                }
            }
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
                if(Integer.parseInt(s) <= 1000){
                    result += Integer.parseInt(s);
                }
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
