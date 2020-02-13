package test;

import java.util.*;

public class HiddenIteratorTest {

    public static void main(String[] args) throws InterruptedException {
        HiddenIterator hi = new HiddenIterator();

        hi.add(5);
        hi.add(879);
        hi.add(33);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    hi.addTenThings();
                }
            }
        }).start();
        Thread.sleep(1000L);

        hi.addTenThings();

    }

    public static class HiddenIterator {
        private final Set<Integer> set = new HashSet<Integer>();
        public synchronized void add(Integer i) { set.add(i); }
        public synchronized void remove(Integer i) { set.remove(i); }
        public void addTenThings() {
            Random r = new Random();
            for (int i = 0; i < 10; i++)
                add(r.nextInt());
            System.out.println("DEBUG: added ten elements to " + set);
        }
    }
}


