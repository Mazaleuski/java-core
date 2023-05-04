package by.teachmeskills.ps.client;

import by.teachmeskills.ps.domain.Merchant;
import by.teachmeskills.ps.services.MerchantService;
import by.teachmeskills.ps.services.exceptions.BankAccountNotFoundException;
import by.teachmeskills.ps.services.exceptions.MerchantNotFoundException;
import by.teachmeskills.ps.services.exceptions.NoBankAccountsFoundException;

import java.io.IOException;
import java.util.Scanner;

public class ApplicationMenu {
    Scanner scanner = new Scanner(System.in);
    MerchantService ms = new MerchantService();

    public void start() throws MerchantNotFoundException {
        while (true) {
            printMenu();
            int i = scanner.nextInt();
            if (i == 9) {
                break;
            } else if (i < 0 || i > 9) {
                System.out.printf("%d not found in menu\n", i);
            } else {
                switch (i) {
                    case 1 -> {
                        System.out.println("Enter merchant id:");
                        String id = scanner.next();
                        if (id.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
                            try {
                                ms.getMerchantBankAccounts(id).forEach(System.out::print);
                            } catch (NoBankAccountsFoundException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            throw new IllegalArgumentException("Incorrect ID.");
                        }
                    }
                    case 2 -> {
                        System.out.println("Enter merchant id:");
                        String id = scanner.next();
                        System.out.println("Enter account number for add.");
                        String num = scanner.next();
                        try {
                            ms.addBankAccount(id, num);
                        } catch (IOException | MerchantNotFoundException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Bank account with " + num + " not added.");
                        }
                    }
                    case 3 -> {
                        System.out.println("Enter account number for update.");
                        String num = scanner.next();
                        System.out.println("Enter new account number.");
                        String newNum = scanner.next();
                        try {
                            ms.updateBankAccount(num, newNum);
                        } catch (BankAccountNotFoundException | RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 4 -> {
                        System.out.println("Enter merchant id.");
                        String id = scanner.next();
                        System.out.println("Enter account number for delete.");
                        String num = scanner.next();
                        try {
                            ms.deleteBankAccount(id, num);
                        } catch (RuntimeException | MerchantNotFoundException | BankAccountNotFoundException |
                                 IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 5 -> {
                        System.out.println("Enter merchant id:");
                        String id = scanner.next();
                        try {
                            System.out.print(ms.getMerchantById(id));
                        } catch (MerchantNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 6 -> {
                        if (ms.getMerchants().size() == 0) {
                            throw new MerchantNotFoundException("No merchants in system.");
                        } else {
                            ms.getMerchants().forEach(System.out::print);
                        }
                    }
                    case 7 -> {
                        System.out.println("Enter name new merchant.");
                        String name = scanner.next();
                        try {
                            ms.createMerchant(new Merchant(name));
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("Merchant with name " + name + " created.");
                    }
                    case 8 -> {
                        System.out.println("Enter merchant id:");
                        String id = scanner.next();
                        if (id.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
                            try {
                                ms.deleteMerchant(id);
                                System.out.println("Merchant with id " + id + " deleted.");
                            } catch (MerchantNotFoundException | IOException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            throw new IllegalArgumentException("Incorrect ID");
                        }
                    }
                }
            }
        }
    }

    private void printMenu() {
        System.out.println("""
                Select a menu item:
                1 Get info about merchant bank accounts.
                2 Add new bank account.
                3 Update bank account.
                4 Delete bank account.
                5 Get info about merchant.
                6 Get all merchants in system.
                7 Create new merchant.
                8 Delete merchant.
                9 Exit.""");
    }
}
