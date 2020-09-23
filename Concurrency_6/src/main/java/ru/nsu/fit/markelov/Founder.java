package ru.nsu.fit.markelov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public final class Founder {
    private final List<Runnable> workers;

    public Founder(final Company company) {
        this.workers = new ArrayList<>(company.getDepartmentsCount());

        CyclicBarrier barrier = new CyclicBarrier(company.getDepartmentsCount(), company::showCollaborativeResult);

        for (int i = 0; i < company.getDepartmentsCount(); i++) {
            int id = i;
            workers.add(() -> {
                try {
                    Department department = company.getFreeDepartment(id);
                    department.performCalculations();
                    System.out.println(department.getIdentifier() + " department has finished");

                    barrier.await();
                } catch (InterruptedException|BrokenBarrierException e) {
                    System.out.println(e.getMessage());
                }
            });
        }
    }

    public void start() {
        for (final Runnable worker : workers) {
            new Thread(worker).start();
        }
    }
}
