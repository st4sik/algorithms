package com.algo.quiz;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2-sum. Given an array aa of n 64-bit integers and a target value T, determine whether there are
 * two distinct integers ii and jj such that a_i + a_j = T. Your algorithm should run in linear time
 * in the worst case.
 *
 * Hint: sort the array in linear time.
 */
public class Sum2 {

  private static final int BITS_PER_BYTE = 8;

  public static void sort(int[] a) {
    final int BITS = 64;                 // each int is 32 bits
    final int R = 1 << BITS_PER_BYTE;    // each bytes is between 0 and 255
    final int MASK = R - 1;              // 0xFF
    final int w = BITS / BITS_PER_BYTE;  // each int is 4 bytes

    int n = a.length;
    int[] aux = new int[n];

    for (int d = 0; d < w; d++) {

      // compute frequency counts
      int[] count = new int[R + 1];
      for (int i = 0; i < n; i++) {
        int c = (a[i] >> BITS_PER_BYTE * d) & MASK;
        count[c + 1]++;
      }

      // compute cumulates
      for (int r = 0; r < R; r++) {
        count[r + 1] += count[r];
      }

      // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
      if (d == w - 1) {
        int shift1 = count[R] - count[R / 2];
        int shift2 = count[R / 2];
        for (int r = 0; r < R / 2; r++) {
          count[r] += shift1;
        }
        for (int r = R / 2; r < R; r++) {
          count[r] -= shift2;
        }
      }

      // move data
      for (int i = 0; i < n; i++) {
        int c = (a[i] >> BITS_PER_BYTE * d) & MASK;
        aux[count[c]++] = a[i];
      }

      // copy back
      for (int i = 0; i < n; i++) {
        a[i] = aux[i];
      }
    }
  }

  public int[] twoSum(int[] a, int T) {
    int[] result = new int[2];
    sort(a);

    int l = 0;
    int r = a.length - 1;

    while (l < r) {
      if (a[l] + a[r] < T) {
        l++;
      } else if (a[l] + a[r] > T) {
        r--;
      } else {
        result[0] = a[l];
        result[1] = a[r];
        return result;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    Sum2 sum2 = new Sum2();
    int[] array = new int[]{100, 200, 300, 500, 1};
    int T = 101;
    int[] result = sum2.twoSum(array, T);
    StdOut.print(result[0] + " " + result[1]);
  }
}
