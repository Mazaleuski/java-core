package by.teachmeskills.homeworks.hw_05052023;

import java.util.ArrayList;
import java.util.List;

public class QueueClients {
    private List<Client> list = new ArrayList<>();

    synchronized void generateClients() {
        while (list.size() != 4) {
            list.add(new Client("Client " + (list.size() + 1)));
            System.out.println("Clients in queue : " + list.size());
        }
    }

    synchronized void getClientsQueue() {
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
