package com.algo.quiz;

import java.util.Arrays;

/**
 * Merging with smaller auxiliary array. Suppose that the subarray \mathtt{a[0]}a[0] to \mathtt{a[n-1]}a[n−1] is sorted
 * and the subarray \mathtt{a[n]}a[n] to \mathtt{a[2*n-1]}a[2∗n−1] is sorted. How can you merge the two subarrays
 * so that \mathtt{a[0]}a[0] to \mathtt{a[2*n-1]}a[2∗n−1] is sorted using an auxiliary array of length nn (instead of 2n2n)?
 *
 * Hint: copy only the left half into the auxiliary array.
 */
public class MergeWithSmallerArray {

	public void merge(Comparable[] a, Comparable[] aux, int N) {
		for (int i = 0; i < N; i++) {
			aux[i] = a[i];
		}

		int i = 0; //the first element of aux
		int j = N; //the first element of 2 array
		int k = 0; //the first element of the first subarray

		while (k < a.length) {
			if (i >= N) {
				a[k] = a[j];
				k++;
				j++;
			} else if (j >= a.length) {
				a[k] = aux[i];
				k++;
				i++;
			} else if (aux[i].compareTo(a[j]) < 0) {
				a[k] = aux[i];
				k++;
				i++;
			} else {
				a[k] = a[j];
				k++;
				j++;
			}
		}
	}

	public static void main(String[] argc) {
		Comparable[] a = {10, 32, 55, 9, 17, 28};
		MergeWithSmallerArray mergeWithSmallerArray = new MergeWithSmallerArray();
		Comparable[] aux = new Comparable[a.length / 2];
		mergeWithSmallerArray.merge(a, aux, a.length / 2);
		Arrays.stream(a).forEach(i -> System.out.println(i + " "));
	}

}
