package com.algo.quiz;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/**
 * Minimum-weight feedback edge set. A feedback edge set of a graph is a subset of edges that
 * contains at least one edge from every cycle in the graph. If the edges of a feedback edge set are
 * removed, the resulting graph is acyclic. Given an edge-weighted graph, design an efficient
 * algorithm to find a feedback edge set of minimum weight. Assume the edge weights are positive.
 *
 * Hint: complement of an MST. Kruskal.
 */
public class MinFeedbackEdge {

  //Kruskal with Max-priority queue
  public Queue<Edge> MWFES(EdgeWeightedGraph G) {
    MaxPQ<Edge> maxPQ = new MaxPQ<>();
    Queue<Edge> mwfes = new Queue<>();
    for (Edge e : G.edges()) {
      maxPQ.insert(e);
    }

    UF uf = new UF(G.V());
    while (!maxPQ.isEmpty()) {
      Edge e = maxPQ.delMax();
      int v = e.either();
      int w = e.other(v);
      if (!uf.connected(v, w)) {
        uf.union(v, w);
      } else {
        mwfes.enqueue(e);
      }
    }

    return mwfes;
  }

}
