package test;

import java.util.ArrayList;

import static sun.util.calendar.CalendarUtils.mod;

public class Test {

    public static void main(String[] args) {
        long l = (long) Integer.MAX_VALUE;
        System.out.println(l);
    }

    public static Object doit(ArrayList<?> list) {
//        list.add(new Three());
        list.add(null);
        return list.get(0);
    }
}