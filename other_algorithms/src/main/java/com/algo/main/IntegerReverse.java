package com.algo.main;

public class IntegerReverse {
    public int reverse(int x) {
        int reverse = 0;
        boolean flag = false;
        int cur = 0;
        while (x != 0) {
            cur = x % 10;
            reverse = reverse * 10;
            reverse = reverse + cur;
            x = x / 10;
            if (x != 0 && Integer.MAX_VALUE / 10 < Math.abs(reverse)) {
                return 0;
            }
        }
        return reverse;
    }
}
