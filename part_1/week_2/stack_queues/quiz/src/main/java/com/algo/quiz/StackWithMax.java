package com.algo.quiz;


import java.util.Stack;

/**
 * Stack with max. Create a data structure that efficiently supports the stack operations (push and pop)
 * and also a return-the-maximum operation. Assume the elements are reals numbers so that you can compare them.
 *
 * Hint: Use two stacks, one to store all of the items and a second stack to store the maximums.
 */
public class StackWithMax extends Stack<Integer> {

	private Stack<Integer> max = new Stack<>();
	private Stack<Integer> stack = new Stack<>();

	public Integer push(Integer item) {
		super.push(item);
		stack.push(item);
		if (max.isEmpty() || item > max.peek() ) {
			max.push(item);
		}
		return item;
	}

	public Integer getMax(){
		return max.peek();
	}

	public Integer pop(){
		max.pop();
		return stack.pop();
	}

	public static void main(String[] argc){
		StackWithMax stackWithMax = new StackWithMax();
		stackWithMax.push(1);
		stackWithMax.push(3);
		stackWithMax.push(2);
		stackWithMax.push(10);
		System.out.println(stackWithMax.getMax());

	}
}
