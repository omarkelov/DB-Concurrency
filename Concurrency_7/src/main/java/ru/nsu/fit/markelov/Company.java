package ru.nsu.fit.markelov;

import java.util.ArrayList;
import java.util.List;

public final class Company {
    private final List<Department> departments;

    public Company(final int threadsCount, int iterations) {
        this.departments = new ArrayList<>(threadsCount);

        for (int i = 0; i < threadsCount; i++) {
            departments.add(i, new Department(i, threadsCount, iterations));
        }
    }

    public void showCollaborativeResult() {
        final double result = departments.stream()
            .map(Department::getCalculationResult)
            .reduce(Double::sum)
            .orElse(Double.POSITIVE_INFINITY);

        System.out.println(result);
        System.out.println("Dif is: " + Math.abs(result - Math.PI / 4));
    }

    public int getDepartmentsCount() {
        return departments.size();
    }

    public Department getFreeDepartment(final int index) {
        return departments.get(index);
    }
}
