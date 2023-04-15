package by.teachmeskills.homeworks.hw_14042023.part1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            RegistrationUtils.createUsersFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            NotificationUtils.sendNotification(RegistrationUtils.getFileCustomers());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
