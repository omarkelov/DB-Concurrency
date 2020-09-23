package ru.nsu.fit.markelov;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> printStrings("This", "is", "the", "first", "sequence")).start();
        new Thread(() -> printStrings("This", "is", "the", "second", "one")).start();
        new Thread(() -> printStrings("Here", "is", "one", "more")).start();
        new Thread(() -> printStrings("And", "the", "last")).start();
    }

    private synchronized static void printStrings(String... strings) {
        for (String string : strings) {
            System.out.print(string + " ");
        }

        System.out.println();
    }
}
