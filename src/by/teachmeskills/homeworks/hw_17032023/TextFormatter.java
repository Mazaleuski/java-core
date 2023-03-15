package by.teachmeskills.homeworks.hw_17032023;

public class TextFormatter {

    public static int stringLength(String string) {
        String s = string.replaceAll("\\p{Punct}", "");
        String[] strings = s.split(" ");
        return strings.length;
    }

    public static boolean isPalindromeString(String string) {
        String string1 = string.toLowerCase().replaceAll("\\p{Punct}", "");
        StringBuilder stringBuilder = new StringBuilder(string1);
        String string2 = stringBuilder.reverse().toString();
        String[] strings = string2.split(" ");
        for (String s : strings) {
            if (string1.contains(s)) {
                return true;
            }
        }
        return false;
    }
}
