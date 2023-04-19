package by.teachmeskills.homeworks.hw_14042023.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class NotificationUtils {
    private static List<String> list = new ArrayList<>();
    private static final String SHOP_NAME = "JT";
    private static final String PRODUCTS = "шорты, плавки, очки";
    private static final DateTimeFormatter EMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DME = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
    private static final String MESSAGE_PART_BEFORE_BIRTHDAY = " в Ваш День рождения дарит Вам в скидку 15 % на следующие товары ";
    private static final String BIRTHDAY_MESSAGE_PART = " поздравляет Вас с Днем рождения и дарит Вам в скидку 15% на следующие товары ";
    private static final String AFTER_BIRTHDAY_MESSAGE_PART = " напоминает Вам про скидку 15% на следующие товары ";

    private NotificationUtils() {
    }

    public static void sendNotification(String filePath) throws IOException {
        Map<String, String> userData = new HashMap<>();
        try (Stream<String> stream = Files.lines(Path.of(filePath));) {
            stream.forEach(s -> list.add(s));
        } catch (IOException e) {
            System.out.println("File didn't read.");
        }
        list.forEach(s -> {
            String[] strings = s.split(" ");
            userData.put("name", strings[0] + " " + strings[1] + " " + strings[2]);
            userData.put("birthday", strings[5]);
            LocalDate currentDate = LocalDate.now();
            LocalDate birthDay = LocalDate.parse(userData.get("birthday"), EMD);
            birthDay = birthDay.plusYears(currentDate.getYear() - birthDay.getYear());
            LocalDate weekBeforeBD = birthDay.minusDays(7);
            LocalDate dayBeforeEnd = birthDay.plusDays(6);
            if (currentDate.equals(weekBeforeBD)) {
                System.out.println(createFullMessage(userData.get("name"), MESSAGE_PART_BEFORE_BIRTHDAY, birthDay));
            } else if (currentDate.equals(birthDay)) {
                System.out.println(createFullMessage(userData.get("name"), BIRTHDAY_MESSAGE_PART, birthDay));
            } else if (currentDate.equals(dayBeforeEnd)) {
                System.out.println(createFullMessage(userData.get("name"), AFTER_BIRTHDAY_MESSAGE_PART, birthDay));
            }
        });
    }

    private static String createFullMessage(String name, String message, LocalDate birthDay) {
        return "Уважаемый(-моя) " + name + ", магазин " + SHOP_NAME +
                message + PRODUCTS + ". Скидка действует с " + birthDay.format(DME) +
                " до " + birthDay.plusDays(7).format(DME) +
                ".Будем рады видеть Вас в нашем магазине.";
    }
}
