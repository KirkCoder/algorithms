package test;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        byte[] b = "В".getBytes();
        for (byte b1 : b) {
            System.out.println(Integer.toBinaryString(b1));
        }
    }

    public static void calculate(char[] arrBytes){
        System.out.println(arrBytes.length);
        int lustIndex = 0;
        ArrayList<String> shortList = new ArrayList<>();
        for (int i = 0; i < arrBytes.length; i = i + 6) {
            StringBuilder sb = new StringBuilder();
            for (int j = lustIndex; j < i; j++) {
                sb.append(arrBytes[j]);
            }
            lustIndex = i;
            shortList.add(sb.toString());
        }
        System.out.println(shortList);
    }
}
