package com.example.romannumberscalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RomanNumber {
    private String romanNumber;
    private ArrayList<String> simpleDigits = new ArrayList<>();
    private ArrayList<String> complexDigits = new ArrayList<>();

    private static ArrayList<String> simpleDigitList = new ArrayList<>();
    private static ArrayList<String> complexDigitList = new ArrayList<>();

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

    private void setSimpleDigits(ArrayList<String> simpleDigits) {
        this.simpleDigits = simpleDigits;
    }
    private void setComplexDigit(ArrayList<String> complexDigits) {
        this.complexDigits = complexDigits;
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

    public static String romanNumberSum(RomanNumber romanNumberOne, RomanNumber romanNumberTwo) {
        simpleDigitList.clear();
        complexDigitList.clear();
        ArrayList<String> simpleResult;
        String complexResult[] = {};
        String result = "";

        separateDigits(romanNumberOne);
        separateDigits(romanNumberTwo);

//
        joinSimpleDigits();

//        complexResult = joinComplexDigits(romanNumberOne.complexDigits, romanNumberTwo.complexDigits);
//        result = joinResults(simpleResult, complexResult);

        return result;
    };

    private static void separateDigits(RomanNumber romanNumber) {
        String romanDigits = romanNumber.getRomanNumber();
        ArrayList<String> complexDigits = new ArrayList<>();
        ArrayList<String> simpleDigits = new ArrayList<>();
        for(int i=0; i<6; i++){
            switch(i){
                case 0:
                    int l = romanDigits.length();
                    romanDigits = romanDigits.replaceFirst("IV", "");
                    if(l != romanDigits.length()){complexDigitList.add("IV");}
                    break;
                    case 1:
                        l = romanDigits.length();
                        romanDigits = romanDigits.replaceFirst("IX", "");
                        if(l != romanDigits.length()){complexDigitList.add("IX");}
                        break;
                case 2:
                    l = romanDigits.length();
                    romanDigits = romanDigits.replaceFirst("XL", "");
                    if(l != romanDigits.length()){complexDigitList.add("XL");}
                    break;
                case 3:
                    l = romanDigits.length();
                    romanDigits = romanDigits.replaceFirst("XC", "");
                    if(l != romanDigits.length()){complexDigitList.add("XC");}
                    break;
                case 4:
                    l = romanDigits.length();
                    romanDigits = romanDigits.replaceFirst("CD", "");
                    if(l != romanDigits.length()){complexDigitList.add("CD");}
                    break;
                case 5:
                    l = romanDigits.length();
                    romanDigits = romanDigits.replaceFirst("CM", "");
                    if(l != romanDigits.length()){complexDigitList.add("CM");}
                    break;
            }
        }
        char[] simpleNumbers = romanDigits.toCharArray();
        for(char c : simpleNumbers){
            simpleDigitList.add(c + "");
        }


    }
    private static void joinSimpleDigits(){
        System.out.println(simpleDigitList);
        System.out.println(complexDigitList);
        int countedI = 0;
        int countedV = 0;
        int countedX = 0;
        int countedL = 0;
        int countedC = 0;
        int countedD = 0;
        int countedM = 0;

        for(String digit : simpleDigitList){
            if(digit.equals("I")) countedI++;
            else if(digit.equals("V")) countedV++;
            else if(digit.equals("X")) countedX++;
            else if(digit.equals("L")) countedL++;
            else if(digit.equals("C")) countedC++;
            else if(digit.equals("D")) countedD++;
            else if(digit.equals("M")) countedM++;
        }
        simpleDigitList.clear();
        if(countedM!=0)
            if(countedM == 4) complexDigitList.add(HashMapCounter.countM.get(countedM));
            else simpleDigitList.add(HashMapCounter.countM.get(countedM));
        if(countedD!=0)
            if(countedD == 4) complexDigitList.add(HashMapCounter.countD.get(countedD));
            else simpleDigitList.add(HashMapCounter.countD.get(countedD));
        if(countedC!=0)
            if(countedC == 4) complexDigitList.add(HashMapCounter.countC.get(countedC));
            else simpleDigitList.add(HashMapCounter.countC.get(countedC));
        if(countedL!=0)
            if(countedL == 4) complexDigitList.add(HashMapCounter.countL.get(countedL));
            else simpleDigitList.add(HashMapCounter.countL.get(countedL));
        if(countedX!=0)
            if(countedX == 4) complexDigitList.add(HashMapCounter.countX.get(countedX));
            else simpleDigitList.add(HashMapCounter.countX.get(countedX));
        if(countedV!=0)
            if(countedV == 4) complexDigitList.add(HashMapCounter.countV.get(countedV));
            else simpleDigitList.add(HashMapCounter.countV.get(countedV));
        if(countedI!=0)
            if(countedI == 4) complexDigitList.add(HashMapCounter.countI.get(countedI));
            else simpleDigitList.add(HashMapCounter.countI.get(countedI));

        System.out.println(simpleDigitList);
        System.out.println(complexDigitList);

    }
    private static void joinComplexDigits(){
        String temp="";
        int countedIV = 0;
        int countedIX = 0;
        int countedXL = 0;
        int countedXC = 0;
        int countedCD = 0;
        int countedCM = 0;
        for(String digit : complexDigitList){
            if(digit.equals("IV")) countedIV++;
            else if(digit.equals("IX")) countedIX++;
            else if(digit.equals("XL")) countedXL++;
            else if(digit.equals("XC")) countedXC++;
            else if(digit.equals("CD")) countedCD++;
            else if(digit.equals("CM")) countedCM++;
            if(countedIV!=0 || countedIX != 0){
                if(countedIV == 2){
                    temp = HashMapCounter.countIV.get(countedIV);
                    for(char c : temp.toCharArray() )
                        complexDigitList.add(c + "");
                } else if(countedIX == 2){
                    temp = HashMapCounter.countIX.get(countedIX);
                    for(char c : temp.toCharArray() )
                        complexDigitList.add(c + "");
                }else if(countedIV == 1 && countedIX == 1){
                    temp = "XIII";
                    for(char c : temp.toCharArray() )
                        complexDigitList.add(c + "");
                }
                else if(countedIV == 3){
                    temp = HashMapCounter.countIX.get(countedIV);
                    for(char c : temp.toCharArray() )
                        complexDigitList.add(c + "");
                }else if(countedIX == 3){
                    temp = HashMapCounter.countIX.get(countedIX);
                    for(char c : temp.toCharArray() )
                        complexDigitList.add(c + "");
                }else if(countedIV == 2 && countedIX == 1){
                    temp = "XVII";
                    for(char c : temp.toCharArray() )
                        complexDigitList.add(c + "");
                }else if(countedIV == 1 && countedIX == 2){
                    temp = "XXII";
                    for(char c : temp.toCharArray() )
                        complexDigitList.add(c + "");
                }
            }
        }
    }
//    private static String joinResults(String[] simpleResult, String[] complexResult){
//
//    }
}
