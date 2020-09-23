package ru.nsu.fit.markelov;

public class Main {
    public static void main(String[] args) {
        printTenLines("Parent");

        new Thread(() -> printTenLines("Child"))
            .start();
    }

    public static void printTenLines(String threadName) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(threadName + " thread: " + i);
        }
    }
}
