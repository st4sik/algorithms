package com.algo.assessment;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Board {

	private int N;
	private int[][] blocks;
	private Board[] neighbors;

	public Board(int[][] blocks) {
		N = blocks.length;
		this.blocks = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.blocks[i][j] = blocks[i][j];
			}
		}
	}

	public int dimension() {
		return N;
	}

	public int hamming() {
		int val = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] != (i * N + j + 1)) {
					val++;
				}
			}
		}
		return val;
	}

	public int manhattan() {
		int manhattanDist = 0;
		int[] location = new int[2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] != 0 && blocks[i][j] != i * N + j + 1) {
					location = findGoalLocation(blocks[i][j]);
					manhattanDist += Math.abs(i - location[0]) + Math.abs(j - location[1]);
				}
			}
		}
		return manhattanDist;
	}

	private int[] findGoalLocation(int element) {
		int[] location = new int[2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (element == i * N + j + 1) {
					location[1] = i;
					location[2] = j;
				}
			}
		}
		return location;
	}

	public boolean isGoal() {
		return hamming() == 0;
	}

	public Board twin() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - 1; i++) {
				if (blocks[i][j] != 0 && blocks[i][j + 1] != 0) {
					exchangeBlocks(blocks, i, j, i, j + 1);
					return new Board(blocks);
				}
			}
		}
		return new Board(blocks);
	}


	public boolean equals(Object y) {
		if (y == null) {
			return false;
		}
		if (y.getClass() != this.getClass()) {
			return false;
		}
		if (y == this) {
			return true;
		}
		Board board = (Board) y;
		for (int i = 0; i < this.dimension(); i++) {
			for (int j = 0; j < this.dimension(); j++) {
				if (this.blocks[i][j] != board.blocks[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public Iterable<Board> neighbors() {
		return new Iterable<Board>() {
			@Override
			public Iterator<Board> iterator() {
				if (blocks == null) {
					findNeighbors();
				}
				return new NeighborIterator();

			}

		};
	}

	findNeighbors()

	private class NeighborIterator implements Iterator<Board> {

		int index = 0;

		@Override
		public boolean hasNext() {
			return neighbors.length > index;
		}

		@Override
		public Board next() {
			if (hasNext()) {
				return neighbors[index++];
			} else {
				throw new NoSuchElementException("There is no next neighbor.");
			}
		}
	}

	public String toString
		()               // string representation of this board (in the output format specified below)

	private void exchangeBlocks(int[][] blocks, int iFirstBlock, int jFirstBlock, int iSecondsBlock,
		int jSecondBlock) {
		int firstValue = blocks[iFirstBlock][jFirstBlock];
		blocks[iFirstBlock][jFirstBlock] = blocks[iSecondsBlock][jSecondBlock];
		blocks[iSecondsBlock][jSecondBlock] = firstValue;
	}

	public static void main(String[] args)

}
