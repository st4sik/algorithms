package com.algo.assessment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Objects;

public class Outcast {

	private final WordNet wordNet;

	public Outcast(WordNet wordNet) {
		if (Objects.isNull(wordNet)) {
			throw new IllegalArgumentException();
		}
		this.wordNet = wordNet;
	}

	public String outcast(String[] nouns) {
		String outcast = null;
		int max = 0;
		for (int i = 0; i < nouns.length; i++) {
			int sum = 0;
			for (int j = 0; j < nouns.length; j++) {
				sum += wordNet.distance(nouns[i], nouns[j]);
			}
			if (sum > max) {
				max = sum;
				outcast = nouns[i];
			}
		}
		return outcast;
	}

	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}
}
