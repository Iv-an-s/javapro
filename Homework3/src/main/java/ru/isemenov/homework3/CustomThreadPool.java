package ru.isemenov.homework3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustomThreadPool {
    private final LinkedList<Runnable> taskQueue = new LinkedList<>();
    private final List<WorkerThread> threadPool;
    private final Object monitor = new Object();
    private volatile boolean isShutdown;

    public CustomThreadPool(int capacity) {
        this.threadPool = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            WorkerThread thread = new WorkerThread();
            thread.start();
            threadPool.add(thread);
        }
    }

    public void execute(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("The shutdown method has already been invoked. You can't add any more tasks");
        }
        synchronized (monitor) {
            taskQueue.add(task);
            monitor.notify();
        }
    }

    public void shutdown() {
        isShutdown = true;
        for (WorkerThread worker : threadPool) {
            worker.interrupt();
        }
    }

    private class WorkerThread extends Thread {
        Runnable task;

        @Override
        public void run() {
            while (!this.isInterrupted()) {
                try {
                    synchronized (monitor) {
                        if (taskQueue.isEmpty()) {
                            monitor.wait();
                        } else {
                            task = taskQueue.removeFirst();
                            task.run();
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + ": останавливает работу...");
                    this.interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": завершил работу.");
        }
    }
}
