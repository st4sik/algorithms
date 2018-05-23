package com.algo.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to n^2n
 * in the worst case. You may assume that you can sort the nn integers in time proportional to n^2n or better.
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] digits) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(digits);
        int length = digits.length;
        for (int i = 0; i < length - 2; i++) {
            if (i == 0 || digits[i] > digits[i - 1]) {
                int c = digits[i];
                int left = i + 1;
                int right = length - 1;
                while (left < right) {
                    if (digits[left] + digits[right] + digits[i] == 0) {
                        List<Integer> abc = new ArrayList<>();
                        abc.add(c);
                        abc.add(digits[left]);
                        abc.add(digits[right]);
                        list.add(abc);
                        left++;
                        right--;
                        while (left < right && digits[left] == digits[left - 1]) {
                            left++;
                        }
                        while (left < right && digits[right] == digits[right + 1]) {
                            right--;
                        }
                    } else if (digits[left] + digits[right] + digits[i] < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] digits = new int[]{-1, 0, 1, 2, -1, -4};

        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(digits);

        lists.stream().forEach(System.out::print);

    }
}
