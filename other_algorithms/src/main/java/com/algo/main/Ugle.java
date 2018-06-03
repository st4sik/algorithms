package com.algo.main;

public class Ugle {
    public static boolean isUgly(int num) {
        if (num == 0) return false;
        if (num == 1) return true;

        if (num % 2 == 0) {
            num = num / 2;
            return isUgly(num);
        }

        if (num % 3 == 0) {
            num = num / 3;
            return isUgly(num);
        }

        if (num % 5 == 0) {
            num = num / 5;
            return isUgly(num);
        }

        return false;
    }


    public static void main(String[] argc) {
        boolean ugly = isUgly(10);
        System.out.print(ugly);
    }
}
