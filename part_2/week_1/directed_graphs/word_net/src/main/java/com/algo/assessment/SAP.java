package com.algo.assessment;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Objects;

public class SAP {

	private final Digraph digraph;

	private class Ancestor {

		private int pathLength = -1;
		private int v = -1;

		public Ancestor(Digraph digraph, int v, int w) {
			if (Objects.isNull(digraph)) {
				throw new IllegalArgumentException();
			}
			BreadthFirstDirectedPaths vbfs = new BreadthFirstDirectedPaths(digraph, v);
			BreadthFirstDirectedPaths wbfs = new BreadthFirstDirectedPaths(digraph, w);
			init(digraph, vbfs, wbfs);
		}

		public Ancestor(Digraph digraph, Iterable<Integer> v, Iterable<Integer> w) {
			if (Objects.isNull(digraph)) {
				throw new IllegalArgumentException();
			}
			BreadthFirstDirectedPaths vbfs = new BreadthFirstDirectedPaths(digraph, v);
			BreadthFirstDirectedPaths wbfs = new BreadthFirstDirectedPaths(digraph, w);
			init(digraph, vbfs, wbfs);
		}

		private void init(Digraph digraph, BreadthFirstDirectedPaths vbfs, BreadthFirstDirectedPaths wbfs) {
			if (Objects.isNull(vbfs) || Objects.isNull(wbfs)) {
				throw new IllegalArgumentException();
			}
			for (int v = 0; v < digraph.V(); v++) {
				if (vbfs.hasPathTo(v) &&
					wbfs.hasPathTo(v) &&
					(pathLength < 0 || vbfs.distTo(v) + wbfs.distTo(v) < pathLength)) {
					this.pathLength = vbfs.distTo(v) + wbfs.distTo(v);
					this.v = v;
				}
			}
		}

		public int getPathLength() {
			return pathLength;
		}

		public void setPathlength(int pathLength) {
			this.pathLength = pathLength;
		}

		public int getV() {
			return v;
		}

		public void setV(int v) {
			this.v = v;
		}

	}

	public SAP(Digraph digraph) {
		if (Objects.isNull(digraph)) {
			throw new IllegalArgumentException();
		}
		this.digraph = digraph;

	}

	public int length(int v, int w) {
		return new Ancestor(digraph, v, w).getPathLength();
	}

	public int ancestor(int v, int w) {
		return new Ancestor(digraph, v, w).getV();
	}

	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		if (Objects.isNull(v) || Objects.isNull(w)) {
			throw new IllegalArgumentException();
		}
		return new Ancestor(digraph, v, w).getPathLength();

	}

	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		if (Objects.isNull(v) || Objects.isNull(w)) {
			throw new IllegalArgumentException();
		}
		return new Ancestor(digraph, v, w).getV();
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);

		int v = Integer.parseInt(args[1]);
		int w = Integer.parseInt(args[2]);
		int length = sap.length(v, w);
		int ancestor = sap.ancestor(v, w);
		StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);

	}
}

