package com.algo.quiz;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.SET;

/**
 * Is an edge in a MST. Given an edge-weighted graph G and an edge e, design a linear-time algorithm
 * to determine whether e appears in some MST of G.
 *
 * Hint: consider the subgraph G' of G containing only those edges whose weight is strictly less
 * than that of e.
 */
public class EdgeInMST {

  public boolean edgeInMST(EdgeWeightedGraph graph, Edge edge) {
    SET<Integer> vertices = new SET<>();
    double weight = edge.weight();
    for (Edge e : graph.edges()) {
      if (e.weight() < weight) {
        int v = edge.either();
        int w = edge.other(v);
        vertices.add(v);
        vertices.add(w);
      }
    }
    int vEdge = edge.either();
    int wEdge = edge.other(vEdge);
    if (vertices.contains(vEdge) && vertices.contains(wEdge)) {
      return false;
    }
    return true;
  }
}
