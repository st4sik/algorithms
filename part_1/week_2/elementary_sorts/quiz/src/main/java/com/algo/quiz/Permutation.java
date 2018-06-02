package com.algo.quiz;

import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Permutation. Given two integer arrays of size nn, design a subquadratic algorithm to determine whether
 * one is a permutation of the other. That is, do they contain exactly the same entries but, possibly,
 * in a different order.
 * <p>
 * Hint: sort both arrays.
 */
public class Permutation {
    public static boolean isPermutation(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }

        Arrays.sort(a);
        Arrays.sort(b);

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] argc) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6};
        int[] b = new int[]{6, 5, 4, 3, 2, 1};

        StdOut.println(isPermutation(a, b));
        b = new int[]{6, 5, 4, 3, 2, 1, 0};
        StdOut.println(isPermutation(a, b));
    }
}
