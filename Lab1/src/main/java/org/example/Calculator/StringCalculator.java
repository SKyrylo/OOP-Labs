package org.example.Calculator;

public class StringCalculator {
    public int add(String numbers) throws IllegalArgumentException{
        int result = 0;
        String[] arr = numbers.split(",");
        if(numbers.isEmpty()){
            return 0;
        }
        for (String s : arr) {
            try {
                result += Integer.parseInt(s);
            }
            catch (NumberFormatException ex) {
                throw new NumberFormatException("Can't pass non numerical input");
            }
        }
        return result;
    }
}
