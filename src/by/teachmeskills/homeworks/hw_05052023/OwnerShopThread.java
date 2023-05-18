package by.teachmeskills.homeworks.hw_05052023;

public class OwnerShopThread extends Thread {
    private QueueClients store;

    public OwnerShopThread(QueueClients store) {
        this.store = store;
    }

    @Override
    public void run() {
        store.getClientsQueue();
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Shop ready for opening.");
        interrupt();
    }
}
