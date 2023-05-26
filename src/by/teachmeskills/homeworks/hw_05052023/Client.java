package by.teachmeskills.homeworks.hw_05052023;

public class Client extends Thread {
    public Client(String name) {
        setName(name);
    }

    @Override
    public void run() {
        System.out.println(currentThread().getName() + " ready for shopping.");
        try {
            sleep((int) (Math.random() * 8 + 1) * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(currentThread().getName() + " ended shopping.");
        interrupt();
    }
}
