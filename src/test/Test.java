package test;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
    }

    public static Object doit(ArrayList<?> list) {

//        list.add(new Three());
        list.add(null);
        return list.get(0);
    }
    
}

class Solution {

}