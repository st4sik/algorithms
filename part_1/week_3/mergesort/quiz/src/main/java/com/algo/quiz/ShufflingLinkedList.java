package com.algo.quiz;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Shuffling a linked list. Given a singly-linked list containing nn items, rearrange the items uniformly at random.
 * Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional
 * to n \log nnlogn in the worst case.
 *
 * Hint: design a linear-time subroutine that can take two uniformly shuffled linked lists of sizes n_1n and n_2n
 * and combined them into a uniformly shuffled linked lists of size n_1 + n_2n.
 */
public class ShufflingLinkedList {

	public static class Node {

		private Comparable val;
		private Node next;

		public Node(Comparable val) {
			this.val = val;
			this.next = null;
		}
	}

	public static void shuffle(Node head, int N) {
		if (N == 1) {
			return;
		}
		int k = 1;
		Node mid = head;
		while (k < N / 2) {
			mid = mid.next;
			k++;
		}

		Node r = mid.next;
		mid.next = null;

		shuffle(head, N / 2);
		shuffle(r, N - N / 2);
		merge(head, r);
	}

	private static void merge(Node l, Node r) {
		Node left = l;
		Node right = r;

		if (StdRandom.uniform(2) > 0) {
			l = right;
			right = right.next;
		} else {
			left = left.next;
		}

		Node current = l;

		while (right != null || left != null) {
			if (left == null) {
				current.next = right;
				right = right.next;
			} else if (right == null) {
				current.next = left;
				left = left.next;
			} else if (StdRandom.uniform(2)>0) {
				current.next = right;
				right = right.next;
			} else {
				current.next = left;
				left = left.next;
			}
			current = current.next;
		}
	}

	public static void main(String[] argc) {
		Node a = new Node(1);
		Node b = new Node(2);
		Node c = new Node(10);
		Node d = new Node(9);
		Node e = new Node(3);
		Node f = new Node(4);

		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = f;

		shuffle(a, 6);

		while (a != null) {
			StdOut.print(a.val + " ");
			a = a.next;
		}


	}

}
