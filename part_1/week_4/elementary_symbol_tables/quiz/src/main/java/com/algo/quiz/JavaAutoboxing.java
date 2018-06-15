package com.algo.quiz;

import edu.princeton.cs.algs4.StdOut;

public class JavaAutoboxing {

	public static void main(String[] argc) {
		double a = 0.0;
		double b = -0.0;
		if (a == b) {
			StdOut.print("true");
		}
		Double x = a;
		Double y = b;
		if (!x.equals(y)) {
			StdOut.print("true");
		}

		a = 0.0/0.0;
		b = 0.0/0.0;
		if (a != b) {
			StdOut.print("true");
		}

		x = a;
		y = b;

		if (x.equals(y)) {
			StdOut.print("true");
		}


	}

}
