package by.teachmeskills.homeworks.hw_05052023;

public class OwnerShopThread extends Thread {
    private JewelryShop jewelryShop;

    public OwnerShopThread(JewelryShop store) {
        this.jewelryShop = store;
    }

    @Override
    public void run() {
        jewelryShop.openShop();
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Shop ready for opening.");
        interrupt();
    }
}
