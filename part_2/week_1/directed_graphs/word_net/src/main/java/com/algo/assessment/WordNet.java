package com.algo.assessment;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WordNet {

	private final SAP sap;
	private final Map<Integer, String> synsetsMap = new HashMap<>();
	private final Map<String, Bag<Integer>> hypernymsMap = new HashMap<>();

	public WordNet(String synsets, String hypernyms) {
		if (Objects.isNull(synsets) || Objects.isNull(hypernyms)) {
			throw new IllegalArgumentException();
		}
		loadSynsetsFromFile(synsets);
		Digraph digraph = loadHypernymsFromFile(hypernyms);
		this.sap = new SAP(digraph);
	}

	public Iterable<String> nouns() {
		return hypernymsMap.keySet();
	}

	public boolean isNoun(String word) {
		if (Objects.isNull(word)) {
			throw new IllegalArgumentException();
		}
		return hypernymsMap.containsKey(word);
	}

	public int distance(String nounA, String nounB) {
		checkNoun(nounA);
		checkNoun(nounB);

		return sap.length(hypernymsMap.get(nounA), hypernymsMap.get(nounB));
	}

	public String sap(String nounA, String nounB) {
		checkNoun(nounA);
		checkNoun(nounB);

		return synsetsMap.get(sap.ancestor(hypernymsMap.get(nounA), hypernymsMap.get(nounB)));
	}

	private void loadSynsetsFromFile(String synsetsFile) {
		In input = new In(synsetsFile);
		Bag<Integer> bag;
		while (input.hasNextLine()) {
			String[] tokens = input.readLine().split(",");
			int id = Integer.parseInt(tokens[0]);
			synsetsMap.put(id, tokens[1]);
			String[] nouns = tokens[1].split(" ");
			for (String noun : nouns) {
				bag = hypernymsMap.get(id);
				if (Objects.isNull(bag)) {
					bag = new Bag<>();
					bag.add(id);
					hypernymsMap.put(noun, bag);
				} else {
					bag.add(id);
				}
			}
		}
	}

	private Digraph loadHypernymsFromFile(String synsetsFile) {
		In input = new In(synsetsFile);
		Digraph digraph = new Digraph(synsetsMap.size());
		while (input.hasNextLine()) {
			String[] tokens = input.readLine().split(",");
			int id = Integer.parseInt(tokens[0]);
			for (int i = 1; i < tokens.length; i++) {
				digraph.addEdge(id, Integer.parseInt(tokens[i]));
			}
		}
		checkCycle(digraph);

		return digraph;
	}

	private void checkCycle(Digraph digraph) {
		DirectedCycle directedCycle = new DirectedCycle(digraph);
		if (directedCycle.hasCycle()) {
			throw new IllegalArgumentException();
		}
	}

	private void checkNoun(String noun) {
		if (!isNoun(noun)) {
			throw new IllegalArgumentException();
		}
	}

	public static void main(String[] args) {

	}


}
