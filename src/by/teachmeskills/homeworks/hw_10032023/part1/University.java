package by.teachmeskills.homeworks.hw_10032023.part1;

public class University {
    private int numberStudents;
    private Season currentSeason;

    public University(int numberStudents, Season currentSeason) {
        this.numberStudents = numberStudents;
        this.currentSeason = currentSeason;
    }

    public void printInfo() {
        if (currentSeason.equals(Season.SUMMER)) {
            System.out.printf("Школа №888, учащихся %d, сейчас мы отдыхаем", numberStudents);
        } else {
            System.out.printf("Школа №888, учащихся %d, сейчас мы учимся", numberStudents);
        }
    }
}

