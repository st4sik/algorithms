package com.algo.quiz;

/**
 * Counting inversions. An inversion in an array a[\,]a[] is a pair of entries a[i]a[i] and a[j]a[j] such
 * that i < ji<j but a[i] > a[j]a[i]>a[j]. Given an array, design a linearithmic algorithm to count the number of inversions.
 *
 * Hint: count while mergesorting.
 */
public class CountInversions {

	private static int merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		int count = 0;
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) {
				a[k] = aux[i++];
			} else if (aux[i].compareTo(aux[j]) > 0) {
				a[k] = aux[j++];
				count += mid - i + 1;
			} else {
				a[k] = aux[i++];
			}
		}
		return count;
	}

	public static int sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo) {
			return 0;
		}
		int mid = lo + (hi - lo) / 2;
		int sort = sort(a, aux, lo, mid);
		int sort1 = sort(a, aux, mid + 1, hi);
		int merge = merge(a, aux, lo, mid, hi);
		return sort + sort1 + merge;
	}

	public static void main(String[] argv) {
		CountInversions countInversions = new CountInversions();
		Comparable[] a = {10, 32, 55, 9, 17, 28, 10, 32};
		Comparable[] aux = new Comparable[a.length];
		int sort = countInversions.sort(a, aux, 0, a.length - 1);
		System.out.println(sort);


	}

}
