package ru.isemenov.homework3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyThreadPool {
    private final LinkedList<Runnable> tasks;
    private final List<Thread> threadPool;
    private volatile boolean isShutdown;

    public MyThreadPool(int capacity) {
        tasks = new LinkedList<>();
        threadPool = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            Thread thread = new WorkerThread();
            thread.start();
            threadPool.add(thread);
        }
    }

    public void execute(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("The shutdown method has already been invoked. You can't add any more tasks");
        }
        synchronized (tasks) {
            tasks.add(task);
            tasks.notify();
        }
    }

    /**
     * После выполнения метода новые задачи больше не принимаются пулом. Задачи, присутствующие в пуле,
     * выполняются. Все потоки, для которых больше нет задач, завершают свою работу.
     * При попытке добавить новую задачу будет выброшено IllegalStateException.
     */
    public void shutdown() {
        isShutdown = true;
    }

    private class WorkerThread extends Thread {
        Runnable task;

        @Override
        public void run() {
            outerLoop:
            while (true) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        if (isShutdown) {
                            break outerLoop;
                        }
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    task = tasks.remove(0);
                }
                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.err.println("RuntimeException in task execution: " + e.getMessage());
                }
            }
            System.out.println(Thread.currentThread().getName() + " завершил работу");
        }
    }
}
