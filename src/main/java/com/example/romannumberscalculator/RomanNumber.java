package com.example.romannumberscalculator;

import java.util.*;

public class RomanNumber {
    private String romanNumber;
//    private ArrayList<String> simpleDigits = new ArrayList<>();
//    private ArrayList<String> complexDigits = new ArrayList<>();

    private static ArrayList<String> simpleDigitList = new ArrayList<>();
    private static ArrayList<String> complexDigitList = new ArrayList<>();
    private static ArrayList<String> tempComplexDigits = new ArrayList<>();
    private static int sortCounter = 0;
    private static boolean resultChecker = true;
    private static String resultString = "";

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

    public static boolean isValidRomanNumberResult() {
        String romanNumber = String.join("", simpleDigitList);
        return isValidRomanNumber(romanNumber);
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
        resultChecker = true;
        String romanDigits = romanNumber.getRomanNumber();
//        ArrayList<String> complexDigits = new ArrayList<>();
//        ArrayList<String> simpleDigits = new ArrayList<>();
        tempComplexDigits.clear();
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
        System.out.println("Ulazni simple: " + simpleDigitList);
        System.out.println("Ulazni complex: " + complexDigitList);
        simpleSort();
        ArrayList<String> temp = new ArrayList<>();
        int countedI = 0;
        int countedV = 0;
        int countedX = 0;
        int countedL = 0;
        int countedC = 0;
        int countedD = 0;
        int countedM = 0;
        int countedIV = 0;
        int countedIX = 0;
        int countedXL = 0;
        int countedXC = 0;
        int countedCD = 0;
        int countedCM = 0;
        System.out.println("Duzina: simple: " + simpleDigitList.size());
        for(String digit : simpleDigitList){
            if(digit.equals("I")) countedI++;
            else if(digit.equals("V")) countedV++;
            else if(digit.equals("X")) countedX++;
            else if(digit.equals("L")) countedL++;
            else if(digit.equals("C")) countedC++;
            else if(digit.equals("D")) countedD++;
            else if(digit.equals("M")) countedM++;
            else if(digit.equals("IV")) countedIV++;
            else if(digit.equals("IX")) countedIX++;
            else if(digit.equals("XL")) countedXL++;
            else if(digit.equals("XC")) countedXC++;
            else if(digit.equals("CD")) countedCD++;
            else if(digit.equals("CM")) countedCM++;
        }

        if(complexDigitList.size() == 0 || isValidRomanNumberResult() )
            sortCounter = 5;

        System.out.println(complexDigitList.size()!=0);
        System.out.println(!isValidRomanNumberResult());
        if(sortCounter!=5 || complexDigitList.size()!=0 || tempComplexDigits.size()!=0 || !isValidRomanNumberResult()) {

            if (countedM != 0)
                if (countedM == 4) complexDigitList.add(HashMapCounter.countM.get(countedM));
                else {
                    if (countedM == 2) {
                        temp.add("M");
                        temp.add("M");
                    } else if (countedM == 3) {
                        temp.add("M");
                        temp.add("M");
                        temp.add("M");
                    } else temp.add(HashMapCounter.countM.get(countedM));
                }
            if (countedD != 0)
                temp.add(HashMapCounter.countD.get(countedD));
            if (countedC != 0)
                if (countedC == 4) complexDigitList.add(HashMapCounter.countC.get(countedC));
                else {
                    if (countedC == 2) {
                        temp.add("C");
                        temp.add("C");
                    } else if (countedC == 3) {
                        temp.add("C");
                        temp.add("C");
                        temp.add("C");
                    } else temp.add(HashMapCounter.countC.get(countedC));
                }
            if (countedL != 0)
                temp.add(HashMapCounter.countL.get(countedL));
            if (countedX != 0)
                if (countedX == 4) complexDigitList.add(HashMapCounter.countX.get(countedX));
                else {
                    if (countedX == 2) {
                        temp.add("X");
                        temp.add("X");
                    } else if (countedX == 3) {
                        temp.add("X");
                        temp.add("X");
                        temp.add("X");
                    } else temp.add(HashMapCounter.countX.get(countedX));
                }
            if (countedV != 0)
                temp.add(HashMapCounter.countV.get(countedV));
            if (countedI != 0)
                if (countedI == 4) complexDigitList.add(HashMapCounter.countI.get(countedI));
                else {
                    if (countedI == 2) {
                        temp.add("I");
                        temp.add("I");
                    } else if (countedI == 3) {
                        temp.add("I");
                        temp.add("I");
                        temp.add("I");
                    } else temp.add(HashMapCounter.countI.get(countedI));
                }
                if(countedIV != 0){
                    for(int i=0; i<countedIV; i++)
                        if(isValidRomanNumberResult())
                            simpleDigitList.add("IV");
                            else
                            complexDigitList.add("IV");
                }
                if(countedIX != 0){
                    for(int i=0; i<countedIX; i++)
                        if(isValidRomanNumberResult())
                            simpleDigitList.add("IX");
                        else
                            complexDigitList.add("IX");
            }
            if(countedXL != 0){
                for(int i=0; i<countedXL; i++)
                    if(isValidRomanNumberResult())
                        simpleDigitList.add("XL");
                    else
                        complexDigitList.add("XL");
            }
            if(countedXC != 0){
                for(int i=0; i<countedXC; i++)
                    if(isValidRomanNumberResult())
                        simpleDigitList.add("XC");
                    else
                        complexDigitList.add("XC");
            }
            if(countedCD != 0){
                for(int i=0; i<countedCD; i++)
                    if(isValidRomanNumberResult())
                        simpleDigitList.add("CD");
                    else
                        complexDigitList.add("CD");
            }
            if(countedCM != 0){
                for(int i=0; i<countedCM; i++)
                    if(isValidRomanNumberResult())
                        simpleDigitList.add("CM");
                    else
                        complexDigitList.add("CM");
            }


            simpleDigitList.clear();
            simpleDigitList.addAll(temp);
            simpleDigitList.addAll(tempComplexDigits);
            tempComplexDigits.clear();
            if(complexDigitList.size()!=0)
                joinComplexDigits();

            if(sortCounter!=5 || (complexDigitList.size()==0 && !isValidRomanNumberResult())) {
                sortCounter++;
                simpleDigitList.addAll(tempComplexDigits);
                romanDigitsSortAndEdit();
            }
        }


        System.out.println("Simple: " + simpleDigitList);
        System.out.println("Complex: " + complexDigitList);


        if(isValidRomanNumberResult() && resultChecker){
            resultChecker = false;
            String konacanRezultat = String.join("", simpleDigitList);
            System.out.println("Konacan rezultat" + konacanRezultat);
            resultString = String.join("", simpleDigitList);
        }

        simpleDigitList.clear();

    }
    private static void joinComplexDigits(){
//        String temp="";
        int countedIV = 0;
        int countedIX = 0;
        int countedXL = 0;
        int countedXC = 0;
        int countedCD = 0;
        int countedCM = 0;

        for(String digit : complexDigitList){
            if(digit.equals("IV")){ countedIV++; }
            else if(digit.equals("IX")) {countedIX++;}
            else if(digit.equals("XL")) {countedXL++;}
            else if(digit.equals("XC")) {countedXC++;}
            else if(digit.equals("CD")) {countedCD++;}
            else if(digit.equals("CM")) {countedCM++;}


//            if(countedIV!=0 || countedIX != 0){
//                if(countedIV == 2){
//                    temp = HashMapCounter.countIV.get(countedIV);
//                    for(char c : temp.toCharArray() )
//                        complexDigitList.add(c + "");
//                } else if(countedIX == 2){
//                    temp = HashMapCounter.countIX.get(countedIX);
//                    for(char c : temp.toCharArray() )
//                        complexDigitList.add(c + "");
//                }else if(countedIV == 1 && countedIX == 1){
//                    temp = "XIII";
//                    for(char c : temp.toCharArray() )
//                        complexDigitList.add(c + "");
//                }
//                else if(countedIV == 3){
//                    temp = HashMapCounter.countIX.get(countedIV);
//                    for(char c : temp.toCharArray() )
//                        complexDigitList.add(c + "");
//                }else if(countedIX == 3){
//                    temp = HashMapCounter.countIX.get(countedIX);
//                    for(char c : temp.toCharArray() )
//                        complexDigitList.add(c + "");
//                }else if(countedIV == 2 && countedIX == 1){
//                    temp = "XVII";
//                    for(char c : temp.toCharArray() )
//                        complexDigitList.add(c + "");
//                }else if(countedIV == 1 && countedIX == 2){
//                    temp = "XXII";
//                    for(char c : temp.toCharArray() )
//                        complexDigitList.add(c + "");
//                }
//            }
        }
        complexDigitList.clear();

        typeComplexDigitsHandler(countedIV, countedIX, 'I', "IV", "IX");
        typeComplexDigitsHandler(countedXL, countedXC, 'X', "XL", "XC");
        typeComplexDigitsHandler(countedCD, countedCM, 'C', "CD", "CM");

        joinSimpleDigits();
    }
//    private static String joinResults(String[] simpleResult, String[] complexResult){
//
//    }
    private static void typeComplexDigitsHandler(int countedA, int countedB, char type, String digitA, String digitB){
        String temp="";
        if(countedA!=0 || countedB != 0){
            if(countedA == 2 && countedB == 0){
                temp = HashMapCounter.countIV.get(countedA);
                complexDigitList.remove(digitA);
                complexDigitList.remove(digitA);
                for(char c : temp.toCharArray() )
                    simpleDigitList.add(c + "");
            } else if(countedB == 2 && countedA == 0){
                temp = HashMapCounter.countIX.get(countedB);
                complexDigitList.remove(digitB);
                complexDigitList.remove(digitB);
                for(char c : temp.toCharArray() )
                    simpleDigitList.add(c + "");
            }else if(countedA == 1 && countedB == 1){
                if(type=='I')
                    temp = "XIII";
                else if(type=='X')
                    temp = "CXXX";
                else if(type=='C')
                    temp = "MCCC";
                complexDigitList.remove(digitA);
                complexDigitList.remove(digitB);
                for(char c : temp.toCharArray() )
                    simpleDigitList.add(c + "");
            }
            else if(countedA == 3){
                temp = HashMapCounter.countIX.get(countedA);
                complexDigitList.remove(digitA);
                complexDigitList.remove(digitA);
                complexDigitList.remove(digitA);
                for(char c : temp.toCharArray() )
                    simpleDigitList.add(c + "");
            }else if(countedB == 3){
                temp = HashMapCounter.countIX.get(countedB);
                complexDigitList.remove(digitB);
                complexDigitList.remove(digitB);
                complexDigitList.remove(digitB);
                for(char c : temp.toCharArray() )
                    simpleDigitList.add(c + "");
            }else if(countedA == 2 && countedB == 1){
                if(type=='I')
                    temp = "XVII";
                else if(type=='C')
                    temp = "CLXX";
                else if(type=='C')
                    temp = "MDCC";
                complexDigitList.remove(digitB);
                complexDigitList.remove(digitB);
                complexDigitList.remove(digitB);
                    temp = "MCCC";
                for(char c : temp.toCharArray() )
                    simpleDigitList.add(c + "");
            }else if(countedA == 1 && countedB == 2){
                if(type=='I')
                    temp = "XXII";
                else if(type=='C')
                    temp = "CCXX";
                else if(type=='C')
                    temp = "MMCC";
                complexDigitList.remove(digitA);
                complexDigitList.remove(digitB);
                complexDigitList.remove(digitB);
                for(char c : temp.toCharArray() )
                    simpleDigitList.add(c + "");
            }else if(countedA == 1 && countedB == 0){
                if(type == 'I'){
                    complexDigitList.remove(digitA);
                    tempComplexDigits.add(HashMapCounter.countIV.get(countedA));
                }
                else if(type == 'X'){
                    complexDigitList.remove(digitA);
                    tempComplexDigits.add(HashMapCounter.countXL.get(countedA));
                }
                else if(type == 'C'){
                    complexDigitList.remove(digitA);
                    tempComplexDigits.add(HashMapCounter.countCD.get(countedA));
                }
            }else if(countedA == 0 && countedB == 1){
                if(type == 'I'){
                    complexDigitList.remove(digitB);
                    tempComplexDigits.add(HashMapCounter.countIX.get(countedB));
                }
                else if(type == 'X'){
                    complexDigitList.remove(digitB);
                    tempComplexDigits.add(HashMapCounter.countXC.get(countedB));
                }
                else if(type == 'C'){
                    complexDigitList.remove(digitB);
                    tempComplexDigits.add(HashMapCounter.countCM.get(countedB));
                }
            }
        }
    }

    public static void romanDigitsSortAndEdit() {
        ArrayList<Integer> valueList = new ArrayList<>();
        System.out.println("Lista za sortiranje: " + simpleDigitList);
        for (String digit : simpleDigitList) {
            valueList.add(HashMapCounter.romanToValue.get(digit));
        }
        System.out.println("Po Hash vriednostima: " + valueList);
        valueList.sort((a, b) -> b.compareTo(a));
        System.out.println("Sortirano: " + valueList);
        if(!isValidRomanNumberResult())
            for (int i = 0; i < valueList.size(); i++) {
                System.out.println(valueList.get(i));
                if (valueList.get(i) == 2 || valueList.get(i) == 4 || valueList.get(i) == 6
                    || valueList.get(i) == 8 || valueList.get(i) == 10 || valueList.get(i) == 12) {
                int nextDigit = valueList.get(i + 1);
                System.out.println(nextDigit);
                if (nextDigit == 1) {
                    System.out.println("Rsjesavanje IV ili IX");
                    if (valueList.get(i) == 2) {
                        valueList.set(i, 3);
                        valueList.remove(i + 1);
                    } else if (valueList.get(i) == 4){
                        valueList.set(i, 5);
                        valueList.remove(i + 1);
                    }
                }
                if (nextDigit == 5) {
                    System.out.println("Rsjesavanje XL ili XC");
                    if (valueList.get(i) == 6){
                        valueList.set(i, 7);
                        valueList.remove(i + 1);
                } else if (valueList.get(i) == 8){
                        valueList.set(i, 9);
                        valueList.remove(i + 1);
                    }
                }
                if (nextDigit == 9) {
                    System.out.println("Rsjesavanje CD ili CM");
                    if (valueList.get(i) == 10){
                        valueList.set(i, 11);
                        valueList.remove(i + 1);
                    }
                    else if (valueList.get(i) == 12){
                        valueList.set(i, 13);
                        valueList.remove(i + 1);
                    }
                }


            }
        }
        System.out.println("Obradjeno: " + valueList);
        tempComplexDigits.clear();
        simpleDigitList.clear();
        for(Integer value : valueList){
            simpleDigitList.add(HashMapCounter.valueToRoman.get(value));
        }
        joinSimpleDigits();
    }

    public static void simpleSort(){
        ArrayList<Integer> valueList = new ArrayList<>();

        for (String digit : simpleDigitList) {
            valueList.add(HashMapCounter.romanToValue.get(digit));
        }

        valueList.sort((a, b) -> b.compareTo(a));
        simpleDigitList.clear();
        for(Integer value : valueList){
            simpleDigitList.add(HashMapCounter.valueToRoman.get(value));
        }
    }

    public static String getResultString(){
        return resultString;
    }





}
