package by.teachmeskills.homeworks.hw_17032023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static by.teachmeskills.homeworks.hw_17032023.CensorUtil.censoringStrings;

public class Application {
    private static final String FILE_TEXT = "data//censorIn.txt";
    private static final String FILE_BLACKLIST = "data//blackList.txt";

    public static void main(String[] args) {
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();

        try (BufferedReader bufferedReader1 = new BufferedReader(new FileReader(FILE_TEXT));
             BufferedReader bufferedReader2 = new BufferedReader(new FileReader(FILE_BLACKLIST))) {
            while (bufferedReader1.ready()) {
                stringBuilder1.append(bufferedReader1.readLine());
                CensorUtil.text = stringBuilder1.toString();
            }
            while (bufferedReader2.ready()) {
                stringBuilder2.append(bufferedReader2.readLine());
                CensorUtil.blackList = stringBuilder2.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        censoringStrings(CensorUtil.text);
    }
}
