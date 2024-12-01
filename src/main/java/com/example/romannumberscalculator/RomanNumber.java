package com.example.romannumberscalculator;

import java.util.*;

public class RomanNumber {

    //    ATRIBUTI

    private String romanNumber;

    // Lista za cifre I V X L C D M
    private static ArrayList<String> simpleDigitList = new ArrayList<>();
    // Lista za kompleksne cifre IV IX XL XC CD CM
    private static ArrayList<String> complexDigitList = new ArrayList<>();
    // Privremena pomocna lista za kompleksne cifre
    private static ArrayList<String> tempComplexDigits = new ArrayList<>();

    private static int sortCounter = 0;
    private static boolean resultChecker = true;

    // Konacan rezultat
    private static String resultString = "";

    //  SETERI I GETERI
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

    //  METODE

    // Pomocna metoda koja pretvara rimski broj u decimalni i sluzi samo konverziju prije ispisa
    public static int romanToDecimal(String romanNumber) {
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

    // Metode za validaciju
    public static boolean isValidRomanNumber(String romanNumber) {
        String romanRegex = "^(M{0,3})" +              // 1000-3000
                "(CM|CD|D?C{0,3})" +        // 900, 400, 0-300
                "(XC|XL|L?X{0,3})" +        // 90, 40, 0-30
                "(IX|IV|V?I{0,3})$";        // 9, 4, 0-3

        return romanNumber != null && romanNumber.matches(romanRegex);
    }

    public static boolean lesThenMChecker(String romanNumber) {
        String romanRegex = "^(M{0,1})" +
                "(CM|CD|D?C{0,3})" +
                "(XC|XL|L?X{0,3})" +
                "(IX|IV|V?I{0,3})$";
        return romanNumber != null && romanNumber.matches(romanRegex);
    }

    // Pomocna metoda za validaciju
    public static boolean isValidRomanNumberResult() {
        String romanNumber = String.join("", simpleDigitList);
        return isValidRomanNumber(romanNumber);
    }

//  SUMIRANJE
//    Glavna metoda za sumiranje 2 rimska broja
    public static String romanNumberSum(RomanNumber romanNumberOne, RomanNumber romanNumberTwo) {
//        Brise prethodno stanje listi (kada se izmijene unosi brojeva)
        simpleDigitList.clear();
        complexDigitList.clear();
        resultString = "";

//        Razdvaja oba rimska broja na simple (I V...) i complex (IV IX...)
        separateDigits(romanNumberOne);
        separateDigits(romanNumberTwo);

//        Poziv metode koji ce zapoceti cijeli proces sumiranja
        joinSimpleDigits();

//        Vracanje konacnog rezultata
        return resultString;
    };

//    Metoda za separaciju simple i complex cifri te njihovo smijestanje u odredjene liste
    private static void separateDigits(RomanNumber romanNumber) {
        resultChecker = true;
        String romanDigits = romanNumber.getRomanNumber(); // Vraca broj kao String
        tempComplexDigits.clear();
//      Algoritam koji trazi complex cifre i prebacuje ih u odgovorajucu listu dok ih istovremeno prepisuje sa ""
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
//        Kako su nam sada ostale samo simple cifre, smjestiti ih kao char-ove u njihovu listu
        char[] simpleNumbers = romanDigits.toCharArray();
        for(char c : simpleNumbers){
            simpleDigitList.add(c + "");
        }


    }

//    Metoda koja spaja simple cifre oba broja u jednu listu, potom broji koliko kojih ima. Za slucaj da se
//    pojave nevalidni oblici poput IIII+, ili VV+ itd. pretvori ih u validan: IV, X
//    te ako kao takvi postanu complex oblika, prebaci ih u complex listu, a za slucaj da se
//    u simple listi nadju kompleksne cifre pri validnom obliku broja, ostaju tu.
//    Ukoliko complex lista nije prazna, poziva se metoda za njenu obradu.
//    Ukoliko je prazna, poziva se metoda za sortiranje i dodatna izracunavanja ukoliko se pojave nevalidni oblici.
//    Te na kraju, kada se sva izracunavanja zavrse a broj je validan, smijesta se rezultat i cisti lista
    private static void joinSimpleDigits(){
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

        for(String digit : simpleDigitList){
            if(digit.equals("I")) countedI++;
            else if(digit.equals("V")) countedV++;
            else if(digit.equals("X")) countedX++;
            else if(digit.equals("L")) countedL++;
            else if(digit.equals("C")) countedC++;
            else if(digit.equals("D")) countedD++;
            else if(digit.equals("M")) countedM++;
//            Za slucaj da su jedini ovakvog tipa u validnom poretku:
            else if(digit.equals("IV")) countedIV++;
            else if(digit.equals("IX")) countedIX++;
            else if(digit.equals("XL")) countedXL++;
            else if(digit.equals("XC")) countedXC++;
            else if(digit.equals("CD")) countedCD++;
            else if(digit.equals("CM")) countedCM++;
        }

        if(complexDigitList.size() == 0 || isValidRomanNumberResult() )
            sortCounter = 5;

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
//                Ukoliko je broj nevalidan, ponovo complex cifre prebaci u complex listu na dalju obradu
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

//                Ciscenje simple liste, dodavanje novonapravljene, dodavanje complex cifara ukoliko su jedinie takve
            simpleDigitList.clear();
            simpleDigitList.addAll(temp);
            simpleDigitList.addAll(tempComplexDigits);
            tempComplexDigits.clear();

//          Kada se to sve zavrsi, provjerava se da li je lista comlex cifara prazna. Ako nije, obradi je,
//            saberi sta moze (obicno duple complex cifre istog nivoa), ostavi sta ne moze i sve vrati u simple listu
            if(complexDigitList.size()!=0)
                joinComplexDigits();

//            Ukoliko je complex lista prazna a broj je idalje nevalidan, poziva se metoda za sortiranje i
//            dodatna izracunavanja. Posto cesto taj rpoces mora da se obavi vise puta, pri stalnom azuriranju
//            simple liste i pozivanja joinSimpleDigits, najvise je potrebno 5 puta da se ponovi, a mora da stoji
//            kako se ne bi uslo u beskonacnu petlju
            if(sortCounter!=5 || (complexDigitList.size()==0 && !isValidRomanNumberResult())) {
                sortCounter++;
                simpleDigitList.addAll(tempComplexDigits);
                romanDigitsSortAndEdit();
            }
        }

//        Smijestanje rezultata u staticki atribut za rezultat odakle ce se kasnije ispisati
        if(isValidRomanNumberResult() && resultChecker){
            resultChecker = false;
            String konacanRezultat = String.join("", simpleDigitList);
            System.out.println("Konacan rezultat" + konacanRezultat);
            resultString = String.join("", simpleDigitList);
        }

//        Ciscenje liste za sledece brojeve
        simpleDigitList.clear();

    }

//    Metoda koja se bavi obradom liste complex cifara, te kada ih obradi i vrati u listu simple cifara
//    poziva ponovo petodu za obradu liste simple cifara
    private static void joinComplexDigits(){

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
        }

        complexDigitList.clear();

//        Kako se mogu sabirati parovi samo istog tipa (IV-IX, XL-xC, CD-CM), ovde se u pomocnu metodu
//        za obradu salje koliko ima kojih cifara, kog su tipa i kako izgledaju
        typeComplexDigitsHandler(countedIV, countedIX, 'I', "IV", "IX");
        typeComplexDigitsHandler(countedXL, countedXC, 'X', "XL", "XC");
        typeComplexDigitsHandler(countedCD, countedCM, 'C', "CD", "CM");

        joinSimpleDigits();
    }

//    Pomocna metoda koja na osnovu tipa i broja cifara vrsi njihovo sabiranje ako je moguce
//    te vraca nazad u listu simple cifara
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

//    Pomocna metoda za sortiranja i dodatne obrade
//    Prvo preko Hash mape napravimo novu listu gdje je svaka rimska cifra ima svoj kljuc u obliku integer-a
//    redom (I-1, IV-2, V-3...). Sada imamo listu integer-a koju sortiramo, potom ulazimo u petlju koja se
//    rjesava nevalidnih oblika ( npr.ako imamo IX i I+ u listi, ponistava ta 2 I, i ostaje samo X), te nakon
//    toga konvertujemo ponovo na osnovu Hash tabele listu nazad da dobijemo rimske cifre, i vracamo ponovo u
//    simple listu te opet pozivamo funkciju za obradu simple liste. Ovo ce se ponavljati sve dok broj ne postane
//    validan.
    public static void romanDigitsSortAndEdit() {

        ArrayList<Integer> valueList = new ArrayList<>();

        for (String digit : simpleDigitList) {
            valueList.add(HashMapCounter.romanToValue.get(digit));
        }

        valueList.sort((a, b) -> b.compareTo(a));

        if(!isValidRomanNumberResult())
            for (int i = 0; i < valueList.size(); i++) {
                System.out.println(valueList.get(i));
                if (valueList.get(i) == 2 || valueList.get(i) == 4 || valueList.get(i) == 6
                    || valueList.get(i) == 8 || valueList.get(i) == 10 || valueList.get(i) == 12) {
                int nextDigit = valueList.get(i + 1);
                System.out.println(nextDigit);
                if (nextDigit == 1) {
                    if (valueList.get(i) == 2) {
                        valueList.set(i, 3);
                        valueList.remove(i + 1);
                    } else if (valueList.get(i) == 4){
                        valueList.set(i, 5);
                        valueList.remove(i + 1);
                    }
                }
                if (nextDigit == 5) {
                    if (valueList.get(i) == 6){
                        valueList.set(i, 7);
                        valueList.remove(i + 1);
                } else if (valueList.get(i) == 8){
                        valueList.set(i, 9);
                        valueList.remove(i + 1);
                    }
                }
                if (nextDigit == 9) {
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

        tempComplexDigits.clear();
        simpleDigitList.clear();
        for(Integer value : valueList){
            simpleDigitList.add(HashMapCounter.valueToRoman.get(value));
        }

        joinSimpleDigits();
    }

//    metoda za jednostavno sortiranje bez dodatne obrade
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

//    metoda koja vraca sam rezultat
    public static String getResultString(){
        return resultString;
    }





}
