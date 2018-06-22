package com.algo.quiz;

import edu.princeton.cs.algs4.RedBlackBST;

/**
 * Generalized queue. Design a generalized queue data type that supports all of the following operations
 * in logarithmic time (or better) in the worst case.
 *
 * Hint: create a red–black BST where the keys are integers and the values are the items such that the i^{th}i
 * largest integer key in the red–black BST corresponds to the i^{th}i item in the queue.
 */
public class GeneralizedQueue<T> {

	private RedBlackBST<Integer, T> blackBST;
	private int index;

	public GeneralizedQueue() {
		this.blackBST = new RedBlackBST<>();
	}

	public void append(T item) {
		blackBST.put(index++, item);
	}

	public void remove() {
		blackBST.deleteMin();
	}

	public T get(int i) {
		int rank = blackBST.rank(i);
		return blackBST.get(rank);
	}

	public void removeElement(int i) {
		int rank = blackBST.rank(i);
		blackBST.delete(rank);
	}


}
