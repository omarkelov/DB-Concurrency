package ru.nsu.fit.markelov;

public class Main {
    public static void main(String[] args) {
        Thread.currentThread().setName("Parent");

        printLines();

        new Thread(Main::printLines, "Child").start();
    }

    private static void printLines() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + " thread: " + i);
        }
    }
}
