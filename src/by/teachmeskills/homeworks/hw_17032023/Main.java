package by.teachmeskills.homeworks.hw_17032023;

import java.io.*;

import static by.teachmeskills.homeworks.hw_17032023.TextFormatter.isPalindromeString;
import static by.teachmeskills.homeworks.hw_17032023.TextFormatter.stringLength;

public class Main {
    public static void main(String[] args) {
        File file = new File("data\\textIn.txt");
        StringBuilder text = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("data", "textOut.txt")))) {
            while (bufferedReader.ready()) {
                text.append(bufferedReader.readLine());
            }
            String[] strings = text.toString().split("\\.");
            for (String s : strings) {
                if (isPalindromeString(s)) {
                    bufferedWriter.write(s);
                    bufferedWriter.write("\n");
                    bufferedWriter.flush();
                } else if (stringLength(s) >= 3 && stringLength(s) <= 5) {
                    bufferedWriter.write(s);
                    bufferedWriter.write("\n");
                    bufferedWriter.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
