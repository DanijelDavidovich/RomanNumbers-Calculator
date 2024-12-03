package com.example.romannumberscalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HashMapCounter {
    // Definisanje konačnih (final) HashMap objekata sa tipom <Integer, String>
    public static final Map<Integer, String> countI = new HashMap<>();
    public static final Map<Integer, String> countX = new HashMap<>();
    public static final Map<Integer, String> countC = new HashMap<>();
    public static final Map<Integer, String> countM = new HashMap<>();
    public static final Map<Integer, String> countV = new HashMap<>();
    public static final Map<Integer, String> countL = new HashMap<>();
    public static final Map<Integer, String> countD = new HashMap<>();
    public static final Map<Integer, String> countIV = new HashMap<>();
    public static final Map<Integer, String> countIX = new HashMap<>();
    public static final Map<Integer, String> countXL = new HashMap<>();
    public static final Map<Integer, String> countXC = new HashMap<>();
    public static final Map<Integer, String> countCD = new HashMap<>();
    public static final Map<Integer, String> countCM = new HashMap<>();
    public static final Map<String, Integer> romanToValue = new HashMap<>();
    public static final Map<Integer, String> valueToRoman = new HashMap<>();

    // Inicijalizacija Hash mapa
    static {
//        Pri spajanju 2 broja u jedan, svaka od cifara I X C M moze se pojaviti do 6 puta
//        Ovo su njihove sume u zavisnosti koliko puta se pojave

        countI.put(1, "I");
        countI.put(2, "II");
        countI.put(3, "III");
        countI.put(4, "IV");
        countI.put(5, "V");
        countI.put(6, "VI");

        countX.put(1, "X");
        countX.put(2, "XX");
        countX.put(3, "XXX");
        countX.put(4, "XL");
        countX.put(5, "L");
        countX.put(6, "LX");

        countC.put(1, "C");
        countC.put(2, "CC");
        countC.put(3, "CCC");
        countC.put(4, "CD");
        countC.put(5, "D");
        countC.put(6, "DC");

        countM.put(1, "M");
        countM.put(2, "MM");
        countM.put(3, "MMM");

//        Pri spajanju 2 broja u 1, cifre V L i D se mogu pojaviti do 2 puta
//        Ovo su sume za svaki njihov slucaj

        countV.put(1, "V");
        countV.put(2, "X");

        countL.put(1, "L");
        countL.put(2, "C");

        countD.put(1, "D");
        countD.put(2, "M");

//        Ovo su hash mape za slucajeve suma kada se u spojenom broju pojavljuju 1, 2 ili 3 ''slozena'' broja
        countIV.put(1, "IV");
        countIV.put(2, "VIII");
        countIV.put(3, "XII");

        countIX.put(1, "IX");
        countIX.put(2, "XVIII");
        countIX.put(3, "XXVII");

        countXL.put(1, "XL");
        countXL.put(2, "LXXX");
        countXL.put(3, "CXX");

        countXC.put(1, "XC");
        countXC.put(2, "CLXXX");
        countXC.put(3, "CCLXX");

        countCD.put(1, "CD");
        countCD.put(2, "DCCC");
        countCD.put(3, "MCC");

        countCM.put(1, "CM");
        countCM.put(2, "MDCCC");
        countCM.put(3, "MMMDCC");

//        Ovo su hash mape za sortiranje i laksu obradu nevalidnih slucajeva kada se zajedno nadju
//        npr. IX I -> (X), ili IX V -> (X IV)
        valueToRoman.put(1, "I");
        valueToRoman.put(2, "IV");
        valueToRoman.put(3, "V");
        valueToRoman.put(4, "IX");
        valueToRoman.put(5, "X");
        valueToRoman.put(6, "XL");
        valueToRoman.put(7, "L");
        valueToRoman.put(8, "XC");
        valueToRoman.put(9, "C");
        valueToRoman.put(10, "CD");
        valueToRoman.put(11, "D");
        valueToRoman.put(12, "CM");
        valueToRoman.put(13, "M");

        romanToValue.put("I", 1);
        romanToValue.put("IV", 2);
        romanToValue.put("V", 3);
        romanToValue.put("IX", 4);
        romanToValue.put("X", 5);
        romanToValue.put("XL", 6);
        romanToValue.put("L", 7);
        romanToValue.put("XC", 8);
        romanToValue.put("C", 9);
        romanToValue.put("CD", 10);
        romanToValue.put("D", 11);
        romanToValue.put("CM", 12);
        romanToValue.put("M", 13);


    }
}
