package by.teachmeskills.homeworks.hw_03032023.part3;

public abstract class Feline extends Animal {
    public Feline(String picture, String food, int hungerLevel, Boundaries boundaries, Location location) {
        super(picture, food, hungerLevel, boundaries, location);
    }

    protected abstract void roam();

}
