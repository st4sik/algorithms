package com.algo.main;

import java.util.Arrays;

public class Prime {
    public static int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        int count = 0;
        for (int i = 2; i < primes.length; i++) {
            if (primes[i]) {
                for (int j = 2; i * j < primes.length; j++) {
                    primes[j * i] = false;
                }
                count++;
            }
        }
        return count;
    }

    public static void main(String[] argc) {
        int n = 10;
        int sum = countPrimes(10);
        System.out.print(sum);
    }
}
