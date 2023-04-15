package by.teachmeskills.homeworks.hw_14042023.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class NotificationUtils {
    private static List<String> list = new ArrayList<>();
    private static String SHOP_NAME = "JT";
    private static String products = "шорты, плавки, очки";
    private static DateTimeFormatter EMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter DME = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");

    private NotificationUtils() {
    }

    public static void sendNotification(String filePath) throws IOException {
        try (Stream<String> stream = Files.lines(Path.of(filePath));) {
            stream.forEach(s -> list.add(s));
        }
        list.forEach(s -> {
            String[] strings = s.split(" ");
            String name = strings[0] + " " + strings[1] + " " + strings[2];
            String birthday = strings[5];
            LocalDate currentDate = LocalDate.now();
            LocalDate birthDay = LocalDate.parse(birthday, EMD);
            birthDay = birthDay.plusYears(currentDate.getYear() - birthDay.getYear());
            LocalDate weekBeforeBD = birthDay.minusDays(7);
            LocalDate dayBeforeEnd = birthDay.plusDays(6);
            String message;
            if (currentDate.equals(weekBeforeBD)) {
                message = " в Ваш День рождения дарит Вам в скидку 15 % на следующие товары ";
                System.out.println(createMessageForSending(name, message, birthDay));
            } else if (currentDate.equals(birthDay)) {
                message = " поздравляет Вас с Днем рождения и дарит Вам в скидку 15% на следующие товары ";
                System.out.println(createMessageForSending(name, message, birthDay));
            } else if (currentDate.equals(dayBeforeEnd)) {
                message = " напоминает Вам про скидку 15% на следующие товары ";
                System.out.println(createMessageForSending(name, message, birthDay));
            }
        });
    }

    private static String createMessageForSending(String name, String message, LocalDate birthDay) {
        return "Уважаемый(-моя) " + name + ", магазин " + SHOP_NAME +
                message + products + ". Скидка действует с " + birthDay.format(DME) +
                " до " + birthDay.plusDays(7).format(DME) +
                ".Будем рады видеть Вас в нашем магазине.";
    }
}
