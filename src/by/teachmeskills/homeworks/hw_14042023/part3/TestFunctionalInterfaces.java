package by.teachmeskills.homeworks.hw_14042023.part3;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;

public class TestFunctionalInterfaces {
    public static void main(String[] args) {
        Predicate<String> predicate1 = s -> s == null;
        Predicate<String> predicate2 = s -> s.length() > 0;
        Predicate<String> predicate3 = predicate1.and(predicate2);

        record HeavyBox(double i) {
        }

        Consumer<HeavyBox> consumer1 = heavyBox -> System.out.printf("Отгрузили ящик с весом %s.\n", heavyBox.i());
        Consumer<HeavyBox> consumer2 = heavyBox -> System.out.printf("Отправляем ящик с весом %s.\n", heavyBox.i());
        consumer1.andThen(consumer2).accept(new HeavyBox(5));

        Function<Integer, String> function = i -> {
            if (i < 0) {
                return "Отрицательное число";
            } else if (i > 0) {
                return "Положительное число";
            } else return "Ноль";
        };

        IntSupplier intSupplier = () -> (int) (Math.random() * 10);
    }
}


