package com.algo.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Sum4 {

	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		if (Objects.isNull(nums) || nums.length < 4) {
			return result;
		}

		Arrays.sort(nums);

		int length = nums.length;
		for (int i = 0; i < length - 3; i++) {
			if (i == 0 || nums[i] > nums[i - 1]) {
				for (int j = i + 1; j < nums.length - 2; j++) {
					if (j == i + 1 || nums[j] > nums[j - 1]) {
						int left = j + 1;
						int right = length - 1;

						while (left < right) {
							if (nums[left] + nums[right] + nums[i] + nums[j] == 0) {
								List<Integer> abc = new ArrayList<>();
								abc.add(nums[i]);
								abc.add(nums[j]);
								abc.add(nums[left]);
								abc.add(nums[right]);
								result.add(abc);
								left++;
								right--;
								while (left < right && nums[left] == nums[left + 1]) {
									left++;
								}
								while (left < right && nums[right] == nums[right - 1]) {
									right--;
								}
							} else if (nums[left] + nums[right] + nums[i] + nums[j]<0) {
								left++;
							} else {
								right--;
							}
						}
					}
				}
			}
		}
		return result;
	}

}

