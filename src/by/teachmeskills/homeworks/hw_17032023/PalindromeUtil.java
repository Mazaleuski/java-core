package by.teachmeskills.homeworks.hw_17032023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class PalindromeUtil {
    private static final String FILE_PALINDROMES = "data//palindromeStrings.txt.txt";

    private PalindromeUtil() {
    }

    public static void writePalindromes(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName));
             BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PALINDROMES))) {
            while (br.ready()) {
                stringBuilder.append(br.readLine()).append(" ");
            }
            String string1 = stringBuilder.toString().toLowerCase().replaceAll("\\p{P}", "");
            stringBuilder = new StringBuilder(string1);
            String string2 = stringBuilder.reverse().toString().trim();
            String[] strings = string2.split(" ");
            for (String s : strings) {
                if (string1.contains(s)) {
                    bw.write(s + "\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
