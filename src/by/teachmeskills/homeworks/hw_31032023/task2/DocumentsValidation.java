package by.teachmeskills.homeworks.hw_31032023.task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Objects;
import java.util.regex.Pattern;

public class DocumentsValidation {
    private static List<String> filesNames = new ArrayList<>();
    private static Set<String> documentsNames = new HashSet<>();
    private static Map<String, String> validationReport = new HashMap<>();

    private DocumentsValidation() {
    }

    public static void readFileName() {
        try (Scanner scanner = new Scanner(System.in);) {
            System.out.println("Введите имена файлов:");
            String s;
            while (!Objects.equals(s = scanner.next(), "0")) {
                filesNames.add(s);
            }
        }
    }

    public static void readDocumentsNames() throws IOException {
        if (filesNames.size() > 0) {
            for (String s : filesNames) {
                File file = new File(s);
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                    while (bufferedReader.ready()) {
                        documentsNames.add(bufferedReader.readLine());
                    }
                }
            }
        }
    }

    public static void validateDocumentsNames() {
        char wrongChar;
        for (String s : documentsNames) {
            if (s.length() == 15) {
                if (Pattern.matches("contract[^\\p{Punct}]{7}", s) || Pattern.matches("docnum[^\\p{Punct}]{9}", s)) {
                    validationReport.put(s, "valid");
                } else {
                    String tmp = s.replaceAll("[^\\p{Punct}]", "");
                    if (tmp.length() > 0) {
                        wrongChar = tmp.charAt(0);
                        validationReport.put(s, String.format("incorrect name of the document, illegal character ‘%s’", wrongChar));
                    }
                }
            } else {
                validationReport.put(s, "invalid-incorrect length document name");
            }
        }
    }
}
