package com.algo.quiz;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.Stack;

/**
 * Nonrecursive depth-first search. Implement depth-first search in an undirected graph without using recursion.
 * O(E + V) time
 * Hint 1: use an explicit stack.
 * Hint 2: it is trickier than it may appear at first; you can simply replace a queue with a stack in breadth-first search.
 */
public class DFS {
    public boolean[] marked;

    public DFS(Graph G, int s) {
        marked = new boolean[G.V()];
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();

        Stack<Integer> stack = new Stack<>();
        marked[s] = true;
        stack.push(s);

        while (!stack.isEmpty()) {
            int currentV = stack.peek();
            if (adj[currentV].hasNext()) {
                int currentW = adj[currentV].next();
                if (!marked[currentW]) {
                    marked[currentW] = true;
                    stack.push(currentW);
                }
            } else {
                stack.pop();
            }

        }
    }

    public static void main(String[] args) {
        Graph G = new Graph(5);
        G.addEdge(0, 1);
        G.addEdge(0, 3);
        G.addEdge(0, 4);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        DFS dfs = new DFS(G, 0);

        for (int v = 0; v < G.V(); v++)
            if (dfs.marked[v]) {
                StdOut.println(v + " ");
            }
    }
}
