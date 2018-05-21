package com.algo.quiz;

/**
 * Add a method \mathtt{find()}find() to the union-find data type so that \mathtt{find(i)}find(i) returns
 * the largest element in the connected component containing ii. The operations, \mathtt{union()}union(),
 * \mathtt{connected()}connected(), and \mathtt{find()}find() should all take logarithmic time or better.
 *
 * For example, if one of the connected components is \{1, 2, 6, 9\}{1,2,6,9}, then
 * the \mathtt{find()}find() method should return 99 for each of the four elements in the connected components.
 */
public class UFWithFindLargest {

    private int[] id;
    private int[] sz;
    private int[] large;

    public UFWithFindLargest(int N) {
        id = new int[N];
        sz = new int[N];
        large = new int[N];
        for (int i = 0; i < N; ++i) {
            id[i] = i;
            sz[i] = 1;
            large[i] = i;
        }
    }

    // path compression
    private int root(int p) {
        while (id[p] != p) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    public int find(int p) {
        return large[root(p)];
    }

    public void union(int p, int q) {
        int rootp = root(p);
        int rootq = root(q);
        if (rootp == rootq) return;

        int largestP = large[rootp];
        int largestQ = large[rootq];
        if (sz[rootp] < sz[rootq]) {
            id[rootp] = rootq;
            sz[rootq] += sz[rootp];

            if (largestP > largestQ)
                large[rootq] = largestP;
        } else {
            id[rootq] = rootp;
            sz[rootp] += sz[rootq];

            if (largestQ > largestP)
                large[rootp] = largestQ;
        }

    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public static void main(String[] args) {
        UFWithFindLargest ufWithFindLargest = new UFWithFindLargest(10);
        ufWithFindLargest.union(1, 2);
        ufWithFindLargest.union(3, 4);

        System.out.println(ufWithFindLargest.find(1)+ " " +ufWithFindLargest.find(3) );

        ufWithFindLargest.union(1, 5);
        ufWithFindLargest.union(4, 2);

        System.out.println(ufWithFindLargest.find(1)+ " " +ufWithFindLargest.find(3) );

        ufWithFindLargest.union(1, 6);
        System.out.println(ufWithFindLargest.find(5));
    }



}

