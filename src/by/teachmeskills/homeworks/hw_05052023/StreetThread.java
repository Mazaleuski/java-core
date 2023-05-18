package by.teachmeskills.homeworks.hw_05052023;

public class StreetThread extends Thread {
    private QueueClients store;

    public StreetThread(QueueClients store) {
        this.store = store;
    }

    @Override
    public void run() {
        store.generateClients();
        interrupt();
    }
}
