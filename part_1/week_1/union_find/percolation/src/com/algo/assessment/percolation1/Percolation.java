package com.algo.assessment.percolation1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF weightedQuickUnionUFBottom;
    private WeightedQuickUnionUF weightedQuickUnionUFTop;
    private int N;
    private int sz;
    private int openSiteNumber;

    public Percolation(int N) {
        if (N <= 0)
            throw new java.lang.IllegalArgumentException();
        this.grid = new boolean[N][N];
        this.N = N;
        initGrid();
        openSiteNumber = 0;
        int square = N * N;
        sz = square + 2;
        weightedQuickUnionUFBottom = new WeightedQuickUnionUF(sz);
        weightedQuickUnionUFTop = new WeightedQuickUnionUF(square + 1);

        for (int i = 1; i <= N; ++i) {
            weightedQuickUnionUFBottom.union(0, i);
            weightedQuickUnionUFBottom.union(sz - 1, square + 1 - i);
            weightedQuickUnionUFTop.union(0, i);
        }
    }

    private void initGrid() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                this.grid[i][j] = false;
            }
        }
    }

    private int posToIndex(int i, int j) {
        return (i - 1) * N + j;
    }
    public int numberOfOpenSites(){
        return openSiteNumber;
    }
    public void open(int i, int j) {

        grid[i - 1][j - 1] = true;
        int idx = posToIndex(i, j);
        openSiteNumber++;

        if (i > 1 && isOpen(i - 1, j)) {
            weightedQuickUnionUFBottom.union(posToIndex(i - 1, j), idx);
            weightedQuickUnionUFTop.union(posToIndex(i - 1, j), idx);
        }
        if (i < N && isOpen(i + 1, j)) {
            weightedQuickUnionUFBottom.union(posToIndex(i + 1, j), idx);
            weightedQuickUnionUFTop.union(posToIndex(i + 1, j), idx);
        }
        if (j > 1 && isOpen(i, j - 1)) {
            weightedQuickUnionUFBottom.union(posToIndex(i, j - 1), idx);
            weightedQuickUnionUFTop.union(posToIndex(i, j - 1), idx);
        }
        if (j < N && isOpen(i, j + 1)) {
            weightedQuickUnionUFBottom.union(posToIndex(i, j + 1), idx);
            weightedQuickUnionUFTop.union(posToIndex(i, j + 1), idx);
        }


    }

    private void validArgs(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N)
            throw new java.lang.IndexOutOfBoundsException();
    }

    public boolean isOpen(int i, int j) {
        validArgs(i, j);
        return grid[i - 1][j - 1];
    }

    public boolean isFull(int i, int j){
        return isOpen(i,j) && weightedQuickUnionUFTop.connected(posToIndex(i,j), 0);
    }

    public boolean percolates() {
        if (N == 1)
            return isOpen(1,1);
        return weightedQuickUnionUFBottom.connected(0, sz-1);

    }
}
