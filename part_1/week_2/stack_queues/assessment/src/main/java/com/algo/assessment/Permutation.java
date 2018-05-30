package com.algo.assessment;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
		String[] symbols = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};
		for (int i = 0; i < symbols.length; i++) {
			randomizedQueue.enqueue(symbols[i]);
		}
		/*while (!StdIn.isEmpty()) {
			randomizedQueue.enqueue(StdIn.readString());
		}*/
		int num = 0;
		while (num != -1) {
			num = StdIn.readInt();
			while (num-- > 0) {
				StdOut.println(randomizedQueue.dequeue());
			}
		}
	}

}
