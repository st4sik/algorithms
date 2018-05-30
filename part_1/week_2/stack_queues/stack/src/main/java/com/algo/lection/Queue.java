package com.algo.lection;

public class Queue<T> {
    private Node<T> last;
    private Node<T> first;
    private int n;

    private static class Node<T> {
        private Node<T> next;
        private T item;
    }

    public void push(T item) {
        Node<T> oldLast = last;
        last = new Node<>();
        last.next = null;
        last.item = item;
        n++;
        if (first == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public T pop(){
        T item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty(){
        if (null == first) return true;
        return false;
    }
    public static void main(String[] arg) {
        Queue<Integer> integerStack = new Queue<>();
        integerStack.push(new Integer(1));
        integerStack.push(new Integer(2));
        integerStack.push(new Integer(3));

        while (!integerStack.isEmpty()) {
            System.out.println(integerStack.pop());
        }

    }

}
