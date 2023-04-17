package by.teachmeskills.homeworks.hw_14042023.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;


public class RegistrationUtils {
    private static final String FILE_CUSTOMERS_PATH = "D:\\files\\users.txt";
    private static Map<String, String> customers = new HashMap<>();
    private static List<String> databaseCustomers = new ArrayList<>();

    private RegistrationUtils() {
    }

    public static String getFileCustomersPath() {
        return FILE_CUSTOMERS_PATH;
    }

    private static void readUserData() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter customer data 'Full name, gender, telephone number/mail, date of birth:'");
            String s = scanner.nextLine();
            if (Objects.equals(s, "0")) {
                break;
            } else {
                String[] strings = s.split(" ");
                if (strings.length != 6) {
                    System.out.printf("Invalid data '%s.'\n", s);
                } else {
                    customers.put("name", strings[0]);
                    customers.put("surname", strings[1]);
                    customers.put("second_name", strings[2]);
                    customers.put("gender", strings[3]);
                    customers.put("phone/mail", strings[4]);
                    customers.put("birthday", strings[5]);
                    validateData();
                }
            }
        }
    }

    private static void validateData() {
        class Validator {
            boolean isName() {
                return !customers.get("name").isBlank();
            }

            boolean isDate() {
                return customers.get("birthday").matches("^\\d{4}-\\d{2}-\\d{2}$");
            }

            boolean isTelephoneOrMail() {
                return customers.get("phone/mail").startsWith("+37529") || customers.get("phone/mail").startsWith("+37525")
                        || customers.get("phone/mail").startsWith("+37533") || customers.get("phone/mail").startsWith("+37544")
                        || customers.get("phone/mail").matches("^(.+)@(\\S+)$");
            }
        }
        Validator validator = new Validator();
        if (validator.isName() && validator.isDate() && validator.isTelephoneOrMail()) {
            String customer = customers.get("name") + " " + customers.get("surname") + " " + customers.get("second_name")
                    + " " + customers.get("gender") + " " + customers.get("phone/mail") + " " + customers.get("birthday");
            databaseCustomers.add(customer);
        }
    }

    public static void createUsersFile() throws IOException {
        readUserData();
        Files.write(Path.of(FILE_CUSTOMERS_PATH), databaseCustomers, StandardOpenOption.APPEND);
    }
}
