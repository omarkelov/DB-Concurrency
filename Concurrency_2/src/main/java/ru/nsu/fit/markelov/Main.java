package ru.nsu.fit.markelov;

public class Main {
    public static void main(String[] args) {
        Thread.currentThread().setName("Parent");

        try {
            Thread childThread = new Thread(Main::printLines, "Child");

            childThread.start();
            childThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " thread is interrupted.");
        }

        printLines();
    }

    private static void printLines() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + " thread: " + i);
        }
    }
}
