package ru.nsu.fit.markelov;

import sun.misc.Signal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public final class Founder {
    private final List<Calculator> calculators;

    public Founder(int threadsCount) {
        CyclicBarrier barrier = new CyclicBarrier(threadsCount, this::printCollaborativeResult);

        Signal.handle(new Signal("INT"), this::handleSigInt);

        calculators = new ArrayList<>(threadsCount);
        for (int id = 0; id < threadsCount; id++) {
            calculators.add(new Calculator(id, threadsCount, barrier));
        }
    }

    private void printCollaborativeResult() {
        double result = calculators.stream()
            .map(Calculator::getResult)
            .reduce(Double::sum)
            .orElse(Double.POSITIVE_INFINITY);

        System.out.println();
        System.out.println(result + " (" + (result - Math.PI / 4) + ")");
    }

    private void handleSigInt(Signal signal) {
        System.out.println("INT handled");

        int maxIteration = calculators.stream()
            .map(Calculator::getIteration)
            .max(Integer::compare)
            .orElse(-1);

        for (Calculator calculator : calculators) {
            calculator.setMaxIteration(maxIteration);
            calculator.getThread().interrupt();
        }
    }

    public void start() {
        for (Calculator calculator : calculators) {
            calculator.getThread().start();
        }
    }
}
