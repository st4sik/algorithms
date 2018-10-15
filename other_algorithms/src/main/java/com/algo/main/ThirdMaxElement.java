package com.algo.main;

/**
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist,
 * return the maximum number. The time complexity must be in O(n).
 */
public class ThirdMaxElement {

	public static int thirdMax(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		long firstMax = Long.MIN_VALUE;
		long secondMax = Long.MIN_VALUE;
		long thirdMax = Long.MIN_VALUE;

		for (int num : nums) {
			if (num > firstMax) {
				thirdMax = secondMax;
				secondMax = firstMax;
				firstMax = num;
			} else if (num < firstMax && num > secondMax) {
				thirdMax = secondMax;
				secondMax = num;
			} else if (num > thirdMax && num < firstMax && num < secondMax) {
				thirdMax = num;
			}

		}
		return (int) (thirdMax == Long.MIN_VALUE ? firstMax : thirdMax);
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1, 2, -2147483648};
		System.out.println(thirdMax(nums));
	}

}
