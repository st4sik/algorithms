package com.algo.main;

import java.util.Stack;

public class EvaluateRPN {

	public int evalRPN(String[] tokens) {
		int returnValue = 0;
		String operators = "+-/*";
		Stack<String> stack = new Stack<>();
		for (String token : tokens) {
			if (!operators.contains(token)) {
				stack.push(token);
			} else {
				int firstValue = Integer.valueOf(stack.pop());
				int secondValue = Integer.valueOf(stack.pop());
				int id = operators.indexOf(token);
				switch (id) {
					case 0:
						stack.push(String.valueOf(firstValue + secondValue));
						break;
					case 1:
						stack.push(String.valueOf(secondValue - firstValue));
						break;
					case 2:
						stack.push(String.valueOf(secondValue / firstValue));
						break;
					case 3:
						stack.push(String.valueOf(firstValue * secondValue));
						break;
				}
			}
		}
		returnValue = Integer.valueOf(stack.pop());
		return returnValue;
	}
}
