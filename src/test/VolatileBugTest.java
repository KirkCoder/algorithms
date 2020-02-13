package test;

import java.util.concurrent.*;

public class VolatileBugTest extends Thread {

    private  int i = 0;

    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    private boolean keepRunning = true;

    public synchronized void setKeepRunning(boolean keepRunning) {
        this.keepRunning = keepRunning;
    }

    public synchronized boolean getKeepRunning() {
        return keepRunning;
    }

    public synchronized void run() {
        while (keepRunning) {
        }

        System.out.println("Thread terminated.");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        VolatileBugTest t = new VolatileBugTest();
        t.start();
        Thread.sleep(1000);
        t.keepRunning = false;
//        t.setKeepRunning(false);
        System.out.println("keepRunning set to false.");
    }

}
