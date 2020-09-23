package ru.nsu.fit.markelov;

public class Main {
    public static void main(String[] args) {
        Thread.currentThread().setName("Parent");

        Thread childThread = new Thread(() -> {
            for (byte i = 0; ; i++) {
                if (Thread.interrupted()) { // We've been interrupted
                    return;
                }

                System.out.println(i);
            }
        }, "Child");

        childThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}

        childThread.interrupt();
    }
}
