package by.teachmeskills.homeworks.hw_10032023.part2;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Product product1 = new Product(12, "Product1", 100);
        Product product2 = new Product(11, "Product2", 120);
        Product product3 = new Product(9, "Product3", 90);

        shop.addProduct(product1);
        shop.addProduct(product2);
        shop.addProduct(product3);

        shop.getAllProducts().sort(Comparator.comparingInt(Product::getPrice));
        System.out.println(shop.getAllProducts());
        shop.deleteProduct(12);
        shop.updateProduct(9,77,"Product77",77);
    }
}
