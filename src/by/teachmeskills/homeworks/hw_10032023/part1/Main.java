package by.teachmeskills.homeworks.hw_10032023.part1;

import by.teachmeskills.homeworks.hw_10032023.part1.exception.CarStartException;

public class Main {
    public static void main(String[] args) {
        Car car1 = new Car("1", 1, 1);
        try {
            car1.start();
        } catch (CarStartException e) {
            System.out.println(e.getMessage());
        }
        Car car2 = new Car("2", 2, 2);
        try {
            car2.start();
        } catch (CarStartException e) {
            System.out.println(e.getMessage());
        }
    }
}
