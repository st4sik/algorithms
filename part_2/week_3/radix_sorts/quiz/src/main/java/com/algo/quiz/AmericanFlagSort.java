package com.algo.quiz;

/**
 * American flag sort. Given an array of n objects with integer keys between 0 and R-1, design a
 * linear-time algorithm to rearrange them in ascending order. Use extra space at most proportional
 * to $R$$.
 *
 * Hint: first compute the frequency counts for each integer, which tells you where the keys need to
 * go. Then cyclically permute the keys into their proper places.
 */
public class AmericanFlagSort {

  public void sort(int[] array, int R) {
    int n = array.length;
    int[] count = new int[R];

    for (int i = 0; i < n; i++) {
      count[array[i]]++;
    }

    int k = 0;
    int r = 0;
    while (k < n) {
      while (count[r]-- > 0) {
        array[k++] = r;
      }
      r++;
    }
  }

  public static void main(String[] args) {
    AmericanFlagSort americanFlagSort = new AmericanFlagSort();
    int[] array = new int[]{1, 2, 3, 4, 4, 1, 2, 3, 4, 4};
    int R = 5;
    americanFlagSort.sort(array, R);
    for (int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    }

  }
}
