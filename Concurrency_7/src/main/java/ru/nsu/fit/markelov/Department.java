package ru.nsu.fit.markelov;

public class Department {
    private final long threadId;
    private final long threadsCount;
    private final long iterations;

    private double calculationResult = 0;

    public Department(long threadId, long threadsCount, long iterations) {
        this.threadId = threadId;
        this.threadsCount = threadsCount;
        this.iterations = iterations;
    }

    public void performCalculations() {
        for (long i = 0; i < iterations; i++) {
            long n = i * threadsCount + threadId; // number of the element in the Leibniz formula
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
