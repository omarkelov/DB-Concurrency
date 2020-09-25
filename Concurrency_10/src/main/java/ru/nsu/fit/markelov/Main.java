package ru.nsu.fit.markelov;

public class Main {
    private static final Object lock = new Object();
    private static int id = 1;

    public static void main(String[] args) {
        Thread.currentThread().setName("Parent");

        new Thread(() -> waitThenPrintThenNotify(2, 1), "Child ").start();

        waitThenPrintThenNotify(1, 2);
    }

    private static void waitThenPrintThenNotify(int currentThreadId, int nextThreadId) {
        try {
            synchronized (lock) {
                for (int i = 1; i <= 10; i++) {
                    while (id != currentThreadId) {
                        lock.wait();
                    }

                    System.out.println(Thread.currentThread().getName() + " " + i);

                    id = nextThreadId;
                    lock.notifyAll();
                }
            }
        } catch (InterruptedException ignored) {}
    }
}
