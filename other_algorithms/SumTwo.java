package main.com.algo;

import java.util.HashMap;
import java.util.Map;

public class SumTwo {
    public int[] twoSum(int[] nums, int target) {
        int[] indexes = new int[2];
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sum = target - nums[i];
            if (hashMap.containsKey(sum)) {
                indexes[0] = hashMap.get(sum);
                indexes[1] = i;
            }
            hashMap.put(nums[i], i);
        }
       return indexes;
    }

    public static void main(String[] ar) {
        int[] a = new int[]{2, 7, 11, 8, 15};
        SumTwo sumTwo = new SumTwo();
        int[] ints = sumTwo.twoSum(a, 15);

    }
}
