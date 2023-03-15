package by.teachmeskills.homeworks.hw_17032023;

import java.io.*;

public class PalindromeFile {

    public static void main(String[] args) {
        File file1 = new File("data", "strings.txt");
        palindromeWrite(file1);
    }

    public static void palindromeWrite(File file1) {
        StringBuilder stringBuilder = new StringBuilder();
        File file2 = new File("data", "palindromeStrings.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file1));
             BufferedWriter bw = new BufferedWriter(new FileWriter(file2))) {
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
