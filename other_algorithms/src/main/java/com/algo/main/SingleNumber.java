package com.algo.main;

import java.util.HashSet;
import java.util.Set;

class SingleNumber {
    public int singleNumber(int[] nums) {
        Set<Integer> hashSet = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i]))
                hashSet.remove(nums[i]);
            else hashSet.add(nums[i]);

        }
        return (int) hashSet.toArray()[0];
    }
}