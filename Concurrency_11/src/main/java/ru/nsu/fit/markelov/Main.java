package ru.nsu.fit.markelov;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Thread.currentThread().setName("Parent");

        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);

        new Thread(() -> print(s2, s1), "Child ").start();

        print(s1, s2);
    }

    private static void print(Semaphore currentSemaphore, Semaphore nextSemaphore) {
        try {
            for (int i = 1; i <= 10; i++) {
                currentSemaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " " + i);
                nextSemaphore.release();
            }
        } catch (InterruptedException ignored) {}
    }
}
