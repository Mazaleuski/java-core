package by.teachmeskills.homeworks.hw_31032023.task2;

import java.io.IOException;

import static by.teachmeskills.homeworks.hw_31032023.task2.DocumentsValidation.*;

public class Main {
    public static void main(String[] args) {
        readFileName();
        try {
            readDocumentsNames();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        validateDocumentsNames();
    }
}
