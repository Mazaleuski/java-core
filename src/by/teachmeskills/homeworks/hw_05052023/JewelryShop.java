package by.teachmeskills.homeworks.hw_05052023;

import java.util.ArrayList;
import java.util.List;

public class JewelryShop {
    private static List<Client> list = new ArrayList<>();

    public static List<Client> getList() {
        return list;
    }

    synchronized void openShop() {
        if (list.size() == 4) {
            System.out.println("Shop is open.");
            list.forEach(Client::start);
            try {
                wait(10000);
                System.out.println("Shop closed.");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
