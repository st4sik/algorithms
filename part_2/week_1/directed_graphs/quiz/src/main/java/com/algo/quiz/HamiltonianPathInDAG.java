package com.algo.quiz;

import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;
import java.util.Iterator;

/**
 * Hamiltonian path in a DAG. Given a directed acyclic graph, design a linear-time algorithm to
 * determine whether it has a Hamiltonian path (a simple path that visits every vertex), and if so,
 * find one.
 *
 * Hint: topological sort.
 */
public class HamiltonianPathInDAG {

  public boolean isHamiltonianPath(Digraph g) {
    DepthFirstOrder depthFirstOrder = new DepthFirstOrder(g);
    Iterable<Integer> reversePost = depthFirstOrder.reversePost();
    int target = -1;
    for (Integer v : reversePost) {
      if (v != target) {
        int id = getId(reversePost, v);
        boolean[] marked = new boolean[g.V()];
        DirectedDFS directedDFS = new DirectedDFS(g, v);
        Integer item = getItem(reversePost, id);
        if (!marked[item]) {
          return false;
        }
      }
    }
    return true;
  }

  private Integer getItem(Iterable<Integer> reversePost, int id) {
    int i = 0;
    Iterator<Integer> iterator = reversePost.iterator();
    while (iterator.hasNext()) {
      if (id == i) {
        return iterator.next();
      }
      i++;
    }
    return -1;
  }

  private int getId(Iterable<Integer> reversePost, Integer v) {
    int i = 0;
    Iterator<Integer> iterator = reversePost.iterator();
    while (iterator.hasNext()) {
      if (iterator.next() == v) {
        return i;
      }
      i++;
    }
    return -1;
  }
}
