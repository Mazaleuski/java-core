package by.teachmeskills.homeworks.hw_17032023;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SerializationCar {
    private static final String FILE_NAME = "data//car.dat";

    public static void main(String[] args) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            Car carSer = new Car("JT", 300, 100);
            objectOutputStream.writeObject(carSer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Car carDes = (Car) objectInputStream.readObject();
            System.out.printf("Brand : %s.\nSpeed : %d.\nPrice : %d.", carDes.getBrand(), carDes.getSpeed(), carDes.getPrice());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
