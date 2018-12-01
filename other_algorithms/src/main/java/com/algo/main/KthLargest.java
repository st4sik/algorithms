package com.algo.main;

import java.util.Arrays;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the
 * sorted order, not the kth distinct element.
 */
public class KthLargest {

  public int findKthLargest(int[] nums, int k) {
    Arrays.sort(nums);
    return nums[nums.length - k];
  }

  public static void main(String[] args) {
    int[] nums = new int[]{3, 2, 1, 5, 6, 4};
    int k = 2;

    KthLargest kthLargest = new KthLargest();
    int element = kthLargest.findKthLargest(nums, k);

    System.out.print(element);
  }
}
