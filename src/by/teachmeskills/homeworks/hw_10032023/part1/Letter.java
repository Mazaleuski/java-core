package by.teachmeskills.homeworks.hw_10032023.part1;

public enum Letter {
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;

    public static int getLetterPosition(Letter letter) {
        return letter.ordinal() + 1;
    }
}
