package by.teachmeskills.homeworks.hw_05052023;

public class Main {
    public static void main(String[] args) {
        JewelryShop jewShop = new JewelryShop();
        for (int i = 1; i < 5; i++) {
            JewelryShop.getList().add(new Client("Client " + i));
        }
        new OwnerShopThread(jewShop).start();
    }
}
