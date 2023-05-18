package by.teachmeskills.homeworks.hw_05052023;

public class Main {
    public static void main(String[] args) {
        QueueClients store = new QueueClients();
        new StreetThread(store).start();
        new OwnerShopThread(store).start();
    }
}
