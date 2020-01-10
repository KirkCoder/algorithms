package test;

import com.sun.istack.internal.NotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomExecutor {

    public static void main(String[] args) {
        executeTasks(generateTasks(30), new CustomExecutor());
    }

    private static void executeTasks(List<Task> tasks, CustomExecutor customExecutor) {
        for (Task task : tasks) {
            customExecutor.addTask(task);
        }
        System.out.println("all tasks added");
    }

    private static List<Task> generateTasks(int tasksCount) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < tasksCount; i++) {
            tasks.add(new Task(i));
        }
        return tasks;
    }

    private final BlockingQueue<Task> queue = new LinkedBlockingQueue<>();

    private final Worker worker = new Worker(Runtime.getRuntime().availableProcessors(), queue);

    public void addTask(Task task) {
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Worker {

        public Worker(int threadsCount, @NotNull BlockingQueue<Task> queue) {
            if (threadsCount <= 0) throw new IllegalArgumentException("Invalid thread count");
            System.out.println("threads count: " + threadsCount);
            for (int i = 0; i < threadsCount; i++) {
                createThread(queue);
            }
        }

        private void createThread(@NotNull BlockingQueue<Task> queue) {
            Thread thread = new Thread(new CycleRunnable(queue));
            thread.start();
        }
    }

    private static class CycleRunnable implements Runnable {

        private final BlockingQueue<Task> queue;

        public CycleRunnable(BlockingQueue<Task> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    queue.take().execute();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Task {
        private final int id;

        public Task(int id) {
            this.id = id;
        }

        public void execute() {
//            BigInteger res = HelperKt.findBigPrime();
//            System.out.println("id: " + id + " thread name " + Thread.currentThread().getName() + ", result: " + res);
            try {
                Thread.sleep(5000L);
                System.out.println("id: " + id + " thread name " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
