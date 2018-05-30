package com.algo.quiz;


import java.util.Stack;

/**
 * Queue with two stacks. Implement a queue with two stacks so that each queue operations takes
 * a constant amortized number of stack operations.
 *
 * Hint: If you push elements onto a stack and then pop them all, they appear in reverse order.
 * If you repeat this process, they're now back in order.
 */
public class QueueWith2Stacks {

	private Queue queue = new Queue();

	private static class Queue {

		Stack<Integer> firstStack = new Stack<>();
		Stack<Integer> secondStack = new Stack<>();
	}

	public boolean isEmpty() {
		return queue.firstStack.isEmpty() && queue.secondStack.isEmpty();
	}

	public int size() {
		return queue.firstStack.size() + queue.secondStack.size();
	}

	public void enqueue(Integer elem) {
		queue.firstStack.push(elem);
	}

	public Integer dequeue() {
		if (queue.secondStack.isEmpty()) {
			while (!queue.firstStack.isEmpty()) {
				queue.secondStack.push(queue.firstStack.pop());
			}
		}
		return queue.secondStack.pop();
	}

	public Integer front() {
		if (queue.secondStack.isEmpty()) {
			while (!queue.firstStack.isEmpty()) {
				queue.secondStack.push(queue.firstStack.pop());
			}
		}
		return queue.secondStack.peek();
	}

	public static void main(String[] argc){
		QueueWith2Stacks queueWith2Stacks = new QueueWith2Stacks();
		queueWith2Stacks.enqueue(1);
		queueWith2Stacks.enqueue(2);
		queueWith2Stacks.enqueue(3);
		queueWith2Stacks.enqueue(4);

		while (!queueWith2Stacks.isEmpty()){
			System.out.println(queueWith2Stacks.dequeue());
		}
	}


}
