package by.teachmeskills.homeworks.hw_10032023.part2;

import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EmptyProductListException;
import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EntityAlreadyExistsException;
import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EntityNotFoundException;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Product product1 = new Product(12, "Product1", 100);
        Product product2 = new Product(11, "Product2", 120);
        Product product3 = new Product(9, "Product3", 90);

        try {
            shop.addProduct(product1);
            shop.addProduct(product2);
            shop.addProduct(product3);
        } catch (EntityAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        try {
            shop.getAllProducts().sort(Comparator.comparingInt(Product::getPrice));
            System.out.println(shop.getAllProducts());
        } catch (EmptyProductListException e) {
            System.out.println(e.getMessage());
        }

        try {
            shop.deleteProduct(12);
            shop.updateProduct(9, 77, "Product77", 77);
        } catch (EmptyProductListException | EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
