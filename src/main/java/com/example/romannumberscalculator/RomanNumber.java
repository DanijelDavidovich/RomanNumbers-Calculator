package com.example.romannumberscalculator;

import java.util.HashMap;
import java.util.Map;

public class RomanNumber {
    private String romanNumber;

    public RomanNumber() {}
    public RomanNumber(String romanNumber) {
        this.romanNumber = romanNumber;
    }
    public String getRomanNumber() {
        return romanNumber;
    }
    public void setRomanNumber(String romanNumber) {
        this.romanNumber = romanNumber;
    }

    public void printRomanNumber() {
        System.out.println(romanNumber);
    }

    public int romanToDecimal(String romanNumber) {
        Map<Character, Integer> romanNumbersMap = new HashMap<Character, Integer>();
        romanNumbersMap.put('I', 1);
        romanNumbersMap.put('V', 5);
        romanNumbersMap.put('X', 10);
        romanNumbersMap.put('L', 50);
        romanNumbersMap.put('C', 100);
        romanNumbersMap.put('D', 500);
        romanNumbersMap.put('M', 1000);

        int decimal = 0;
        for (int i = 0; i < romanNumber.length(); i++) {
            if(i+1 < romanNumber.length() && romanNumbersMap.get(romanNumber.charAt(i)) < romanNumbersMap.get(romanNumber.charAt(i+1))) {
                decimal -= romanNumbersMap.get(romanNumber.charAt(i));
            } else{
                decimal += romanNumbersMap.get(romanNumber.charAt(i));
            }
        }
        return decimal;
    }

    public static boolean isValidRomanNumber(String romanNumber) {
        String romanRegex = "^(M{0,3})" +              // 1000-3000
                "(CM|CD|D?C{0,3})" +        // 900, 400, 0-300
                "(XC|XL|L?X{0,3})" +        // 90, 40, 0-30
                "(IX|IV|V?I{0,3})$";        // 9, 4, 0-3

        return romanNumber != null && romanNumber.matches(romanRegex);
    }

    public static String romanNumberSum(String romanNumberOne, String romanNumberTwo) {}
}
