package com.algo.quiz;

/**
 * Reachable vertex.
 *
 * DAG: Design a linear-time algorithm to determine whether a DAG has a vertex that is reachable
 * from every other vertex, and if so, find one.
 *
 * Digraph: Design a linear-time algorithm to determine whether a digraph has a vertex that is
 * reachable from every other vertex, and if so, find one.
 *
 * Hint (DAG): compute the outdegree of each vertex.
 *
 *
 * Hint (digraph): compute the strong components and look at the kernel DAG (the digraph that
 * results when you contract each strong component to a single vertex).
 */
public class ReachableVertex {

  //1.Topological sorted order
  //2.Run DFS from each vertex
  //3.Ff target is in each marked list => target vertex

  //1.DFS from every vertex.V marked lists, one for each vertex
  //2.Take intersection of these lists. if []: None, else:vertices that every vertex can reach
}
