package by.teachmeskills.homeworks.hw_17032023;

public class CensorUtil {
    private static int count = 1;
    static String text = "";
    static String blackList = "";

    public static void censoringStrings(String text) {
        String[] strings = text.split("\\.");
        for (String s : strings) {
            String temp = s.toLowerCase().replaceAll("\\p{Punct}", "");
            String[] temps = temp.split(" ");
            for (String str : temps) {
                if (blackList.contains(str)) {
                    System.out.printf(s.replaceAll(str, "***") + ". Необходимо изменить! " +
                            "Не прошло проверку предложений : %d.\n", count);
                    count++;
                }
            }
        }
        if (count == 1) {
            System.out.println("Текст прошёл проверку на цензуру.");
        }
    }
}
