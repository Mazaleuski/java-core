package by.teachmeskills.homeworks.hw_14042023.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class RegistrationUtils {
    private static String FILE_CUSTOMERS = "D:\\files\\users.txt";
    private static List<String> customers = new ArrayList<>();
    private static List<String> dataBaseCustomers = new ArrayList<>();

    private RegistrationUtils() {
    }

    public static String getFileCustomers() {
        return FILE_CUSTOMERS;
    }

    private static void readUserData() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter customer data 'Full name, gender, telephone number/mail, date of birth:'");
            String s = scanner.nextLine();
            if (Objects.equals(s, "0")) {
                break;
            } else {
                customers.add(s);
            }
        }
    }

    private static void validateData(String s) {
        String[] strings = s.split(" ");
        if (strings.length != 6) {
            System.out.printf("Invalid data '%s.'\n", s);
        } else {
            Map<String, String> data = new HashMap<>();
            data.put("name", strings[0] + strings[1] + strings[2]);
            data.put("phone/mail", strings[4]);
            data.put("birthday", strings[5]);
            class Validator {
                boolean isName() {
                    return !data.get("name").isBlank();
                }

                boolean isDate() {
                    return data.get("birthday").matches("^\\d{4}-\\d{2}-\\d{2}$");
                }

                boolean isTelephoneOrMail() {
                    return data.get("phone/mail").startsWith("+37529") || data.get("phone/mail").startsWith("+37525")
                            || data.get("phone/mail").startsWith("+37533") || data.get("phone/mail").startsWith("+37544")
                            || data.get("phone/mail").matches("^(.+)@(\\S+)$");
                }
            }
            Validator validator = new Validator();
            if (validator.isName() && validator.isDate() && validator.isTelephoneOrMail()) {
                dataBaseCustomers.add(s);
            }
        }
    }

    public static void createUsersFile() throws IOException {
        readUserData();
        customers.forEach(RegistrationUtils::validateData);
        Files.write(Path.of(FILE_CUSTOMERS), dataBaseCustomers, StandardOpenOption.APPEND);
    }
}
