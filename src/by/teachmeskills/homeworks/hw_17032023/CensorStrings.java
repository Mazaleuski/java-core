package by.teachmeskills.homeworks.hw_17032023;

import java.io.*;

public class CensorStrings {
    static int count = 1;
    static String text = null;
    static String blackList = null;

    public static void main(String[] args) {
        File file1 = new File("data//censorIn.txt");
        File file2 = new File("data//blackList.txt");
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();

        try (BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file1));
             BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file2))) {
            while (bufferedReader1.ready()) {
                stringBuilder1.append(bufferedReader1.readLine());
                text = stringBuilder1.toString();
            }
            while (bufferedReader2.ready()) {
                stringBuilder2.append(bufferedReader2.readLine());
                blackList = stringBuilder2.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        censoringStrings(text);
    }

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
