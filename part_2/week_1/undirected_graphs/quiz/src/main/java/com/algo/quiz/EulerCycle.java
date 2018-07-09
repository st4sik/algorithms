package com.algo.quiz;

import edu.princeton.cs.algs4.Graph;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * Euler cycle. An Euler cycle in a graph is a cycle (not necessarily simple) that uses every edge
 * in the graph exactly one.
 *
 * Show that a connected graph has an Euler cycle if and only if every vertex has even degree.
 * Design a linear-time algorithm to determine whether a graph has an Euler cycle, and if so, find
 * one.
 *
 * Hint: use depth-first search and piece together the cycles you discover.
 */
public class EulerCycle {

  private Set<Tuple> tuples = new HashSet<>();
  private Set<Tuple> cycles = new HashSet<>();

  public class Tuple<X, Y> {

    public final X x;
    public final Y y;

    public Tuple(X x, Y y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Tuple<?, ?> tuple = (Tuple<?, ?>) o;
      return Objects.equals(x, tuple.x) &&
          Objects.equals(y, tuple.y);
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  public void find(Graph g, int v) {
    Iterable<Integer> adj = g.adj(v);
    Iterator<Integer> iterator = adj.iterator();
    while (iterator.hasNext()) {
      int w = iterator.next();
      Tuple tuple = new Tuple(v, w);
      if (!tuples.contains(tuple)) {
        tuples.add(tuple);
        if (!selfLoop(tuple)) {
          tuples.add(new Tuple(w, v));
        }
        cycles.add(tuple);
        find(g, w);
      }
    }
  }

  private boolean selfLoop(Tuple tuple) {
    return tuple.x == tuple.y;
  }

  public Set<Tuple> getCycles() {
    return cycles;
  }

  public static void main(String[] args) {
    EulerCycle eulerCycle = new EulerCycle();
    Graph G = new Graph(5);
    G.addEdge(0, 1);
    G.addEdge(0, 3);
    G.addEdge(1, 2);
    G.addEdge(2, 3);
    eulerCycle.find(G, 0);
    Set<Tuple> cycles = eulerCycle.getCycles();
    cycles.stream().forEach(tuple -> System.out.println(tuple.x + " " + tuple.y));
  }
}
