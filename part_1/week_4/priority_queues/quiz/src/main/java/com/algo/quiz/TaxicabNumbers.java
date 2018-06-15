package com.algo.quiz;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Taxicab numbers. A taxicab number is an integer that can be expressed as the sum of two cubes of positive integers
 * in two different ways: a^3 + b^3 = c^3 + d^3a. For example, 17291729 is the smallest taxicab number: 9
 * ^3 + 10^3 = 1^3 + 12^39. Design an algorithm to find all taxicab numbers less than nn.
 *
 * Hint: Use a min-oriented priority queue with nn items. O(n^2*logn) time and O(n) space
 */
public class TaxicabNumbers {

	private class Taxicab implements Comparable<Taxicab> {

		private final int a;
		private final int b;
		private final int cube;

		public Taxicab(int a, int b) {
			this.a = a;
			this.b = b;
			this.cube = (a * a * a) + (b * b * b);
		}


		@Override
		public int compareTo(Taxicab o) {
			if (o.cube > this.cube) {
				return -1;
			}
			if (o.cube < this.cube) {
				return 1;
			}
			return 0;
		}

		public String toString() {
			return String.format("%d^3 + %d^3", a, b);
		}
	}

	public void findTaxiNumber(int N, boolean optimize) {
		if (optimize) {
			MinPQ<Taxicab> minPQ = new MinPQ<>();
			for (int i = 1; i <= N; i++) {
				minPQ.insert(new Taxicab(i, i));
			}

			Taxicab prevTaxicab = new Taxicab(0, 0);
			while (!minPQ.isEmpty()) {
				Taxicab currTaxicab = minPQ.delMin();
				if (currTaxicab.cube == prevTaxicab.cube) {
					StdOut.println(
						String.format("%d %s=%s", currTaxicab.cube, currTaxicab.toString(), prevTaxicab.toString()));
				}
				if (currTaxicab.b <= N) {
					minPQ.insert(new Taxicab(currTaxicab.a, currTaxicab.b + 1));
				}
				prevTaxicab = currTaxicab;
			}
		} else {
			List<Taxicab> taxicabList = new ArrayList<>();

			for (int i = 1; i <= N; i++) {
				for (int j = i; j <= N; j++) {
					taxicabList.add(new Taxicab(i, j));
				}
			}
			taxicabList.sort((o1, o2) -> {
				if (o1.cube > o2.cube) {
					return -1;
				}
				if (o1.cube < o2.cube) {
					return 1;
				}
				return 0;
			});

			Taxicab prevTaxicab = new Taxicab(0, 0);
			for (Taxicab taxicab : taxicabList) {
				if (prevTaxicab.cube == taxicab.cube) {
					StdOut.println(
						String.format("%d %s=%s", taxicab.cube, taxicab.toString(), prevTaxicab.toString()));
				}
				prevTaxicab = taxicab;
			}
		}
	}


	public static void main(String[] argc) {
		int n = 100;
		TaxicabNumbers taxicabNumbers = new TaxicabNumbers();
		taxicabNumbers.findTaxiNumber(n, false);
	}

}

