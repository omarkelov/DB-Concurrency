package ru.nsu.fit.markelov;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Calculator {
    private final int id;
    private final int threadsCount;
    private final Thread thread;
    private int iteration;
    private int maxIteration;
    private double result;

    public Calculator(int id, int threadsCount, CyclicBarrier barrier) {
        this.id = id;
        this.threadsCount = threadsCount;

        thread = new Thread(() -> {
            calculate(Integer.MAX_VALUE);
            calculate(maxIteration);

            try {
                barrier.await();
            } catch (InterruptedException|BrokenBarrierException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public void calculate(int iterations) {
        for (; iteration < iterations; iteration++) {
            if (Thread.interrupted()) {
                return;
            }

            long n = ((long) iteration) * threadsCount + id; // number of the element in the Leibniz formula
            double nthElementValue = 1d / (2 * n + 1);

            if (n % 2 == 0) {
                result += nthElementValue;
            } else {
                result -= nthElementValue;
            }
        }
    }

    public Thread getThread() {
        return thread;
    }

    public int getIteration() {
        return iteration;
    }

    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }

    public double getResult() {
        return result;
    }
}
