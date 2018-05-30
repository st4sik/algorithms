package com.algo.assessment;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {

	private Node last;
	private Node first;
	private int n;


	private class Node {

		private Node next;
		private Node prev;
		private T item;
	}

	public Deque() {
		this.first = null;
		this.last = null;
		this.n = 0;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return n;
	}

	public void addFirst(T item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}

		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.prev = null;
		if (oldFirst == null) {
			first.next = null;
		} else {
			first.next = oldFirst;
			oldFirst.prev = first;
		}
		if (last == null) {
			last = first;
		}
		n++;
	}

	public void addLast(T item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;

		if (oldLast == null) {
			last.prev = null;
		} else {
			last.prev = oldLast;
			oldLast.next = last;
		}
		if (first == null) {
			first = last;
		}

		n++;

	}

	public T removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T item = first.item;
		first = first.next;

		if (first != null && first.prev != null) {
			first.prev = null;
		}

		if (first == null) {
			last = null;
		}

		n--;
		return item;
	}

	public T removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T item = last.item;
		last = last.prev;

		if (last != null && last.next != null) {
			last.next = null;
		}

		if (last == null) {
			first = null;
		}

		n--;
		return item;
	}

	public Iterator<T> iterator() {
		return new DequeIterator();
	}


	private class DequeIterator implements Iterator<T> {

		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			T item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	public static void main(String[] argc){
		Deque<Integer> deque = new Deque<>();

		deque.addFirst(1);
		deque.addFirst(2);
		deque.addFirst(3);
		deque.addFirst(4);

		deque.addLast(5);
		deque.addLast(6);

		for(Integer integer : deque){
			System.out.println(integer);
		}

		System.out.println("*");
		deque.removeFirst();
		deque.removeLast();

		for(Integer integer : deque){
			System.out.println(integer);
		}


	}

}
