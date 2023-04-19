package by.teachmeskills.homeworks.hw_14042023.part2;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);) {
            if (scanner.nextInt() == 1) {
                MyInterface<String> myInterface = s -> new StringBuilder(s).reverse().toString();
            } else if (scanner.nextInt() == 2) {
                MyInterface<Integer> myInterface = i -> IntStream.rangeClosed(1, i).reduce(1, (int a, int b) -> a * b);
            }
        }
    }
}
