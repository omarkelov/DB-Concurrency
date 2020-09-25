package ru.nsu.fit.markelov;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int threadsCount = Runtime.getRuntime().availableProcessors();

        try {
            threadsCount = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Threads count has not been provided through arguments. " +
                threadsCount + " threads will be used.");
        } catch (NumberFormatException e) {
            System.out.println("Threads count could not be parsed as an integer. " +
                threadsCount + " threads will be used.");
        }

        new Founder(threadsCount).start();
    }
}
