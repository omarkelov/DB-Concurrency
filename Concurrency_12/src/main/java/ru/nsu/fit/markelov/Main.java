package ru.nsu.fit.markelov;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Deque<String> deque = new LinkedList<>();
    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            for (;;) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    List<String> sortedList = bubbleSort(new ArrayList<>(deque));

                    deque = new LinkedList<>(sortedList);
                }
            }
        }).start();

        Scanner iScanner = new Scanner(System.in);
        while (iScanner.hasNextLine()) {
            String input = iScanner.nextLine();
            synchronized (lock) {
                if (input.isEmpty()) {
                    deque.forEach(System.out::println);
                } else {
                    deque.addFirst(input);
                }
            }
        }

        iScanner.close();
    }

    private static List<String> bubbleSort(ArrayList<String> arr) {
        for (int i = arr.size() - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr.get(j).compareTo(arr.get(j + 1)) > 0) {
                    String tmp = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, tmp);
                }
            }
        }

        return arr;
    }
}
