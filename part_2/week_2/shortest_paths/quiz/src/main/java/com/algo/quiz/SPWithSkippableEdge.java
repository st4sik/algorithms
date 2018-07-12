package com.algo.quiz;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Stack;

/**
 * Shortest path with one skippable edge. Given an edge-weighted digraph, design an ElogV algorithm
 * to find a shortest path from s to t where you can change the weight of any one edge to zero.
 * Assume the edge weights are nonnegative.
 *
 * Hint: compute the shortest path from ss to every vertex; compute the shortest path from every
 * vertex to t; combine.
 *
 * 1) Execute Dijkstra on the graph G starting from s to obtain the shortest distance T(v) between s
 * and any v.
 *
 * 2) Reverse all the edges to obtain the reversed graph G'
 *
 * 3) Execute Dijkstra on the graph G' starting from t to obtain the shortest distance R(v) between
 * t and any v.
 *
 * 4) The one to skip is the edge e(v1 --> v2) for which T(v1) + R(v2) is minimum.
 *
 * 5) The path to follow is a concatenation of the shortest path between s and v1 given by the first
 * Dijkstra and the shortest path between v2 and t given by the second Dijkstra.
 */
public class SPWithSkippableEdge {

  public Iterable<DirectedEdge> skippablePath(EdgeWeightedDigraph G, int s, int t) {
    //Execute Dijkstra on the graph G starting from s to obtain the shortest distance T(v)
    //between s and any v.
    DijkstraSP spaths = new DijkstraSP(G, s);
    //Reverse all the edges to obtain the reversed graph G'.
    //Execute Dijkstra on the graph G' starting from t to obtain the shortest distance R(v) between
    //t and any v.
    DijkstraSP tpaths = new DijkstraSP(reverse(G), t);

    //The one to skip is the edge e(v1 --> v2) for which T(v1) + R(v2) is minimum.
    DirectedEdge skipDirectedEdge = null;
    double min = Double.MAX_VALUE;
    for (DirectedEdge e : G.edges()) {
      int v = e.from();
      int w = e.to();
      if (spaths.distTo(v) + tpaths.distTo(w) < min) {
        skipDirectedEdge = e;
        min = spaths.distTo(v) + tpaths.distTo(w);
      }
    }

    //The path to follow is a concatenation of the shortest path between s and v1 given by the first
    //Dijkstra and the shortest path between v2 and t given by the second Dijkstra.
    Stack<DirectedEdge> path = new Stack<>();
    Stack<DirectedEdge> tmp = new Stack<>();

    for (DirectedEdge e : tpaths.pathTo(skipDirectedEdge.to())) {
      path.push(e);
    }
    path.push(skipDirectedEdge);

    for (DirectedEdge e : spaths.pathTo(skipDirectedEdge.from())) {
      tmp.push(e);
    }
    for (DirectedEdge e : tmp) {
      path.push(e);
    }

    return path;
  }

  private static EdgeWeightedDigraph reverse(EdgeWeightedDigraph g) {
    int numVertices = g.V();
    EdgeWeightedDigraph gr = new EdgeWeightedDigraph(numVertices);
    for (DirectedEdge e : g.edges()) {
      int f = e.from();
      int t = e.to();
      double w = e.weight();
      DirectedEdge er = new DirectedEdge(t, f, w);
      gr.addEdge(er);
    }
    return gr;
  }
}
