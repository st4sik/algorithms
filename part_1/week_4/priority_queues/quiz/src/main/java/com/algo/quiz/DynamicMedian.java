package com.algo.quiz;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

/**
 * Dynamic median. Design a data type that supports insert in logarithmic time, find-the-median in constant time,
 * and remove-the-median in logarithmic time.
 *
 * Hint: maintain two binary heaps, one that is max-oriented and one that is min-oriented.
 */
public class DynamicMedian {

	private MaxPQ<Integer> left;
	private MinPQ<Integer> right;

	public DynamicMedian() {
		this.left = new MaxPQ<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
		this.right = new MinPQ<>(new Integer[]{11, 12, 13, 14, 15, 16, 17, 18});
	}

	public double getMedian() {
		int LNode = left.size();
		int RNode = right.size();

		if (LNode == RNode) {
			return ((double) right.min() + (double) left.max()) / 2;
		}

		if (LNode > RNode) {
			return left.max();
		} else {
			return right.min();
		}
	}

	public void insert(int key) {
		double median = getMedian();

		if (key <= median) {
			left.insert(key);
			if (left.size() - right.size() > 1) {
				right.insert(left.delMax());
			} else {
				right.insert(key);
				if (right.size() - left.size() > 1) {
					left.insert(right.delMin());
				}
			}
		}
	}

	public void removeMedian() {
		if (left.size() > right.size()) {
			left.delMax();
		} else {
			right.delMin();
		}
	}

	public static void main(String[] argc) {
		DynamicMedian dynamicMedian = new DynamicMedian();
		double median = dynamicMedian.getMedian();
		StdOut.print(median);

		dynamicMedian.removeMedian();
		median = dynamicMedian.getMedian();
		StdOut.print(median);

	}


}
