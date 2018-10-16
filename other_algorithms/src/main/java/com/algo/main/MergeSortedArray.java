package com.algo.main;

public class MergeSortedArray {

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int L = m + n - 1;
		int i = m - 1;
		int j = n - 1;

		while (j >= 0 && i >= 0) {
			if (nums1[i] > nums2[j]) {
				nums1[L] = nums1[i];
				i--;
			} else {
				nums1[L] = nums2[j];
				j--;
			}
			L--;
		}

		if (j >= 0) {
			for (int k = 0; k <= j; k++) {
				nums1[k] = nums2[k];
			}
		}
	}

	public static void main(String[] args) {
		MergeSortedArray mergeSortedArray = new MergeSortedArray();
		int[] nums1 = new int[]{1, 2, 3, 4, 0, 0, 0, 0, 0};
		int[] nums2 = new int[]{1, 2, 3, 4, 5};
		mergeSortedArray.merge(nums1, 4, nums2, nums2.length);
		System.out.println(nums1);
	}
}
