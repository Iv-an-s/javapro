package ru.isemenov.homework3;

public class TaskRunner {
    public static void main(String[] args) {
        CustomThreadPool threadPool = new CustomThreadPool(4);

        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName() + ": Task #" + taskNumber + " started");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + ": Task #" + taskNumber + " finished");
            };
            threadPool.execute(task);
            System.out.println("Task #" + taskNumber + " added to thread pool");
        }

        System.err.println("Вызваем метод shutdown()");
        threadPool.shutdown();

        System.err.println("Пытаемся добавить новую задачу...");
        try{
            threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": Is task started???"));
        }catch (IllegalStateException e){
            System.err.printf("При попытке добавить задачу после вызова метода shutdown получили %s с сообщением: {%s}\n",
            e.getClass().getSimpleName(), e.getMessage());
        }
    }
}