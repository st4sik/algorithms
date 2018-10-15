package com.algo.main;

/**
 * Babylonian Algorithm
 */
public class Sqrt_X {

	public static int mySqrt(int n) {
		if (n <= 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}

		double err = 1e-15;
		double next = n;
		while (Math.abs(next - n / next) > err * next) {
			next = (n / next + next) / 2.0;
		}
		return (int) next;
	}

	public static void main(String[] args) {
		int i = mySqrt(8);
		System.out.println(i);
	}
}
