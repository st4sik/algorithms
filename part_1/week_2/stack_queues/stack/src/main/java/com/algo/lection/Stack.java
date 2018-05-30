package com.algo.lection;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    private Node<T> first;
    private int n;

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        private Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    private static class Node<T> {
        public Node<T> next;
        public T item;
    }

    public void push(T item) {
        Node<T> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public boolean isEmpty() {
        if (null == first) {
            return true;
        }
        return false;
    }

    public T pop() {
        T item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public static void main(String[] arg) {
        Stack<Integer> integerStack = new Stack<>();
        integerStack.push(new Integer(1));
        integerStack.push(new Integer(2));
        integerStack.push(new Integer(3));

        while (!integerStack.isEmpty()) {
            System.out.println(integerStack.pop());
        }

        Stack<String> stringStackGenericStack = new Stack<>();
        stringStackGenericStack.push("344");
        stringStackGenericStack.push("777");
        stringStackGenericStack.push("11111");

        Iterator<String> iterator = stringStackGenericStack.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        for (String string : stringStackGenericStack) {
            System.out.println(string);
        }
    }
}
