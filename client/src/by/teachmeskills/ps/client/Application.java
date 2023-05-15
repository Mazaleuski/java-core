package by.teachmeskills.ps.client;

import by.teachmeskills.ps.services.exceptions.MerchantNotFoundException;

public class Application {
    public static void main(String[] args) {
        ApplicationMenu applicationMenu = new ApplicationMenu();
        try {
            applicationMenu.start();
        } catch (MerchantNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

