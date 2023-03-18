package by.teachmeskills.homeworks.hw_17032023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

import static by.teachmeskills.homeworks.hw_17032023.TextFormatter.isPalindromeString;
import static by.teachmeskills.homeworks.hw_17032023.TextFormatter.stringLength;

public class Main {
    private static final String FILE_IN = "data\\textIn.txt";
    private static final String FILE_OUT = "data\\textOut.txt";

    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_IN));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_OUT))) {
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
