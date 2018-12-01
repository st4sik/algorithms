package com.algo.main;

/**
 * Floyd's Tortoise and Hare
 */
public class FindDuplicate {

	public static int findDuplicate(int[] nums) {
		int tortoise = nums[0];
		int hare = nums[0];

		do {
			tortoise = nums[tortoise];
			hare = nums[nums[hare]];
		} while (tortoise != hare);

		int ptr1 = nums[0];
		int ptr2 = tortoise;

		while (ptr1 != ptr2) {
			ptr1 = nums[ptr1];
			ptr2 = nums[ptr2];
		}
		return ptr1;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{
			2, 5, 9, 6, 9, 3, 8, 9, 7, 1};
		int duplicate = findDuplicate(nums);
		System.out.println(duplicate);
	}


}