package by.teachmeskills.homeworks.hw_10032023.part2;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ApplicationMenu {
    Scanner scanner = new Scanner(System.in);
    Shop shop = new Shop();

    public void start() {
        while (true) {
            printMenu();
            int i = scanner.nextInt();
            if (i == 5) {
                scanner.close();
                break;
            } else if (i > 5 || i < 0) {
                System.out.println("Ошибка! Нет такого пункта!.");
            } else
                switch (i) {
                    case 1 -> {
                        if (shop.getAllProducts().size() == 0) {
                            break;
                        }
                        System.out.println("""
                                В какой сортировке вывести товары?
                                1 По возрастанию цены.
                                2 По убыванию цены.""");
                        int j = scanner.nextInt();
                        if (j < 1 || j > 2) {
                            System.out.println("Ошибка! Нет такого пункта!");
                        } else {
                            List<Product> list = shop.getAllProducts();
                            list.sort(Comparator.comparingInt(Product::getPrice));
                            if (j == 1) {
                                System.out.println(list);
                            } else {
                                Collections.reverse(list);
                                System.out.println(list);
                            }
                        }
                    }
                    case 2 -> {
                        System.out.println("""
                                Введите параметры добавляемого товара:
                                1 id
                                2 name
                                3 price""");
                        int id = scanner.nextInt();
                        String name = scanner.next();
                        int price = scanner.nextInt();
                        shop.addProduct(new Product(id, name, price));
                    }
                    case 3 -> {
                        if (shop.getAllProducts().size() == 0) {
                            break;
                        }
                        System.out.println("Введите id удаляемого товара:");
                        int id = scanner.nextInt();
                        shop.deleteProduct(id);
                    }
                    case 4 -> {
                        if (shop.getAllProducts().size() == 0) {
                            break;
                        }
                        System.out.println("""
                                Введите id и параметры редактируемого товара:
                                1 id
                                2 newId
                                3 newName
                                4 newPrice""");
                        int id = scanner.nextInt();
                        int newId = scanner.nextInt();
                        String newName = scanner.next();
                        int newPrice = scanner.nextInt();
                        shop.updateProduct(id, newId, newName, newPrice);
                    }
                }
        }
    }

    private void printMenu() {
        System.out.println("""
                Выберите пункт меню:
                1 Вывод всех товаров.
                2 Добавление товара.
                3 Удаление товара.
                4 Редактирование товара.
                5 Выход.""");
    }
}
