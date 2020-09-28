package ru.nsu.fit.markelov;

public class Department {
    private final int threadId;
    private final int threadsCount;
    private final int iterations;

    private double calculationResult = 0;

    public Department(int threadId, int threadsCount, int iterations) {
        this.threadId = threadId;
        this.threadsCount = threadsCount;
        this.iterations = iterations;
    }

    public void performCalculations() {
        for (int i = 0; i < iterations; i++) {
            long n = ((long) i) * threadsCount + threadId; // number of the element in the Leibniz formula
            double nthElementValue = 1d / (2 * n + 1);

            if (n % 2 == 0) {
                calculationResult += nthElementValue;
            } else {
                calculationResult -= nthElementValue;
            }
        }
    }

    public double getCalculationResult() {
        return calculationResult;
    }
}
