package by.teachmeskills.homeworks.hw_10032023.part1;

import by.teachmeskills.homeworks.hw_10032023.part1.exception.CarStartException;

public class Car {
    private String brand;
    private int speed;
    private int price;

    public Car(String brand, int speed, int price) {
        this.brand = brand;
        this.speed = speed;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void start() throws CarStartException {
        int random = (int) (Math.random() * 20);
        if (random % 2 == 0) {
            throw new CarStartException(String.format("Мотор автомобиля %s не запустился.", brand));
        }
        System.out.printf("Мотор автомобиля %s запустился.\n", brand);
    }
}

