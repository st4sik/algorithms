package com.algo.assessment;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class RandomizedQueue<T> implements Iterable<T> {

	private T[] items;
	private int n;

	public RandomizedQueue() {
		this.items = (T[]) new Object[1];
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	public void enqueue(T item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		if (n == items.length) {
			resize(2 * items.length);
		}
		items[n] = item;
		n++;
	}

	public T dequeue() {
		if (n == 0) {
			throw new NoSuchElementException();
		}

		int rand = StdRandom.uniform(n);
		T item = items[rand];

		if (size() - 1 == n) {
			items[rand] = null;
		} else {
			items[rand] = items[n - 1];
			items[n - 1] = null;
		}
		if (n == items.length / 4) {
			resize(items.length / 4);
		}
		n--;
		return item;
	}

	public T sample() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return items[StdRandom.uniform(n)];
	}

	private void resize(int capacity) {
		T[] copy = (T[]) new Object[capacity];

		for (int i = 0; i < items.length; i++) {
			copy[i] = items[i];
		}
		items = copy;
	}

	@Override
	public Iterator<T> iterator() {
		return new RandomQueueIterator();
	}

	private class RandomQueueIterator implements Iterator<T> {

		private int[] ids;
		private int i;


		public RandomQueueIterator() {
			ids = new int[n];

			for (int i = 0; i < n; i++) {
				ids[i] = i;
			}

			StdRandom.shuffle(ids);
		}

		@Override
		public boolean hasNext() {

			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return i < n;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return items[ids[i++]];
		}
	}
}
