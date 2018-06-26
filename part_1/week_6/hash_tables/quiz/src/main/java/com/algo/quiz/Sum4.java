package com.algo.quiz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 4-SUM. Given an array a[] of n integers, the 4-SUM problem is to determine if there exist distinct indices i, j, k,
 * and l such that a[i] + a[j] = a[k] + a[l]. Design an algorithm for the 4-SUM problem that takes time proportional
 * to n^2 (under suitable technical assumptions).
 *
 * Hint: create a hash table with (2 n) key-value pairs.
 */
public class Sum4 {

	private class Pair {

		int i;
		int j;

		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Pair pair = (Pair) o;
			return i == pair.i &&
				j == pair.j;
		}

		@Override
		public int hashCode() {

			return Objects.hash(i, j);
		}

		public String toString() {
			return String.format("Pair {%s, %s}", i, j);
		}
	}

	public HashMap<Integer, Set<Pair>> sum4(int[] array) {
		int length = array.length;
		assert length >= 4;
		Arrays.sort(array);
		HashMap<Integer, Set<Pair>> hashMap = new HashMap<>();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (i == j) {
					continue;
				}
				int sum = array[i] + array[j];
				if (!hashMap.containsKey(sum)) {
					hashMap.put(sum, new HashSet<Pair>());
				}
				hashMap.get(sum).add(new Pair(array[i], array[j]));
			}
		}
		return hashMap;
	}

	public static void main(String[] args) {
		Sum4 sum4 = new Sum4();
		int[] array = new int[]{0, 0, 0, 0};
		HashMap<Integer, Set<Pair>> integerSetHashMap = sum4.sum4(array);
	}

}
