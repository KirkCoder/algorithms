package test;

import java.util.ArrayList;

import static sun.util.calendar.CalendarUtils.mod;

public class Test {

    public static void main(String[] args) {
        mod(3, 4);
    }

    public static Object doit(ArrayList<?> list) {
//        list.add(new Three());
        list.add(null);
        return list.get(0);
    }
}