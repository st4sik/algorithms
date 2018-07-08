package com.algo.quiz;

import static edu.princeton.cs.algs4.StdStats.min;

import edu.princeton.cs.algs4.Cycle;
import edu.princeton.cs.algs4.Graph;

/**
 * Diameter and center of a tree. Given a connected graph with no cycles
 *
 * Diameter: design a linear-time algorithm to find the longest simple path in the graph. Center:
 * design a linear-time algorithm to find a vertex such that its maximum distance from any other
 * vertex is minimized.
 *
 * Hint (diameter): to compute the diameter, pick a vertex s; run BFS from s; then run BFS again
 * from the vertex that is furthest from s.
 *
 * Hint (center): consider vertices on the longest path.
 */
public class DiameterСenterOfTree {

  public int max_dist;
  public boolean[] marked;
  public int[] maxdistances;

  public DiameterСenterOfTree(Graph g) {
    this.marked = new boolean[g.V()];
    this.maxdistances = new int[g.V()];
  }

  public void diameter(Graph g, int s) {
    Cycle cycle = new Cycle(g);
    if (cycle.hasCycle()) {
      throw new IllegalArgumentException("Graph contains cycle");
    }
    if (s == -1) {
      return;
    }
    BFS bfs = new BFS(g, s);
    bfs.bfs(g, s);
    int node = -1;
    marked[s] = true;
    for (int v = 0; v < g.V(); v++) {
      if (bfs.hasPathTo(v)) {
        int d = bfs.distTo(v);
        if (d > max_dist) {
          max_dist = d;
          node = v;
        }
      }
    }
    diameter(g, node);
  }

  public void center(Graph g, int s) {
    Cycle cycle = new Cycle(g);
    if (cycle.hasCycle()) {
      throw new IllegalArgumentException("Graph contains cycle");
    }
    for (int v = 0; v < g.V(); v++) {
      int maxDist = 0;
      BFS bfs = new BFS(g, s);
      bfs.bfs(g, s);
      for (int v1 = 0; v1 < g.V(); v1++) {
        if (bfs.hasPathTo(v1)) {
          int dist = bfs.distTo(v1);
          if (maxDist < dist) {
            maxDist = dist;

          }
        }
      }
      maxdistances[v] = maxDist;
    }
  }

  public int getCenter(){
    int minMaxDist = min(maxdistances);
    return maxdistances[minMaxDist];

  }
  public static void main(String[] args) {
    Graph G = new Graph(5);
    G.addEdge(0, 1);
    G.addEdge(0, 4);
    G.addEdge(1, 2);
    G.addEdge(2, 3);

    DiameterСenterOfTree diameterСenterOfTree = new DiameterСenterOfTree(G);
    diameterСenterOfTree.diameter(G, 0);
    int max_dist = diameterСenterOfTree.max_dist;
    diameterСenterOfTree.center(G,0);
    int center = diameterСenterOfTree.getCenter();

  }

}
