package com.algo.quiz;

import edu.princeton.cs.algs4.StdOut;

/**
 * Dutch national flag. Given an array of nn buckets, each containing a red, white, or blue pebble, sort them by color.
 * The allowed operations are:
 * <p>
 * swap(i, j)swap(i,j): swap the pebble in bucket ii with the pebble in bucket jj.
 * color(i)color(i): determine the color of the pebble in bucket ii.
 * The performance requirements are as follows:
 * <p>
 * At most nn calls to color()color().
 * At most nn calls to swap()swap().
 * Constant extra space.
 * <p>
 * Hint: 3-way partitioning.
 */
public class DutchNationalFlag {

    public static void sort(int[] buckets) {
        int reader = 0;
        int low = 0;
        int high = buckets.length - 1;
        while (reader <= high) {
            if (buckets[reader] == 0) {
                swap(buckets, reader, low);
                reader++;
                low++;
            } else if (buckets[reader] == 1) {
                reader++;
            } else if (buckets[reader] == 2) {
                swap(buckets, reader, high);
                high--;
            }
        }
    }

    public static void swap(int[] buckets, int i, int j) {
        int tmp = buckets[i];
        buckets[i] = buckets[j];
        buckets[j] = tmp;
    }

    public static void main(String[] args) {
        DutchNationalFlag f = new DutchNationalFlag();

        int[] buckets = new int[]{0, 1, 2, 0, 2, 2, 1, 1, 0, 1, 2};
        f.sort(buckets);
        for (int i : buckets) {
            StdOut.print(i + " ");
        }
    }

}
