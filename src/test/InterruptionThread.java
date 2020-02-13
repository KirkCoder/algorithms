package test;

public class InterruptionThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("while in runnable");
                    try {
                        Thread.sleep(6000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("in runnable");
                    }
                }
                System.out.println("stop");
            }
        });
        t.start();
        Thread.sleep(1000L);
        t.interrupt();
        System.out.println("main stop");
    }
}
