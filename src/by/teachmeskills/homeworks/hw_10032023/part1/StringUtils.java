package by.teachmeskills.homeworks.hw_10032023.part1;

import java.util.ArrayList;

public class StringUtils {
    private StringUtils() {
    }

    public static char lastChar(String string) {
        return string.charAt(string.length() - 1);
    }

    public static boolean endsWith(String string) {
        return string.endsWith("!!!");
    }

    public static boolean startsWith(String string) {
        return string.startsWith("Сиреневенький синхрофазотрон");
    }

    public static boolean contains(String processedString, String substring) {
        return processedString.contains(substring);
    }

    public static String upperOrLowerCase(String string, boolean up) {
        return up ? string.toUpperCase() : string.toLowerCase();
    }

    public static String arithmeticExpression(String operand1, String operation, String operand2) {
        StringBuilder stringBuilder = new StringBuilder();
        int result = 0;
        stringBuilder.append(operand1).append(" ").append(operation).append(" ").append(operand2).append(" = ");
        switch (operation) {
            case "-" -> result = Integer.parseInt(operand1) - Integer.parseInt(operand2);
            case "+" -> result = Integer.parseInt(operand1) + Integer.parseInt(operand2);
            case "*" -> result = Integer.parseInt(operand1) * Integer.parseInt(operand2);
            case "/" -> {
                if (Integer.parseInt(operand2) != 0) {
                    double division = (double) Integer.parseInt(operand1) / Integer.parseInt(operand2);
                    return stringBuilder.append(String.format("%.3f", division)).toString();
                } else {
                    return "На ноль делить нельзя!";
                }
            }
        }
        return stringBuilder.append(result).toString();
    }

    public static String replacementEquals(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        int index = stringBuilder.indexOf("=");
        stringBuilder.deleteCharAt(index);
        stringBuilder.insert(index, "равно");
        return stringBuilder.toString();
    }

    public static String middleCharacters(String string1, String string2) {
        StringBuilder stringBuilder = new StringBuilder(string1);
        stringBuilder.append(string2);
        int index = stringBuilder.length() / 2;
        return String.valueOf(stringBuilder.charAt(index - 1)) + (stringBuilder.charAt(index));
    }

    public static String palindromeWords(String string) {
        ArrayList<String> result = new ArrayList<>();
        String string1 = string.toLowerCase().replaceAll("\\p{P}", "");
        StringBuilder stringBuilder = new StringBuilder(string1);
        String string2 = stringBuilder.reverse().toString();
        String[] strings = string2.split(" ");
        for (String s : strings) {
            if (string1.contains(s)) {
                result.add(s);
            }
        }
        return result.toString();
    }

    public static void splitString(String string) {
        String string1 = string.replaceAll("\\p{P}", "");
        String[] strings = string1.split(" ");
        for (String s : strings) {
            System.out.println(s);
        }
    }
}
