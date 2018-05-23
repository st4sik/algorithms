package com.algo.quiz;

/**
 * Social network connectivity. Given a social network containing nn members and a log file containing mm timestamps at
 * which times pairs of members formed friendships, design an algorithm to determine the earliest time at which all
 * members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). Assume that
 * the log file is sorted by timestamp and that friendship is an equivalence relation.
 * The running time of your algorithm should be m \log nmlogn or better and use extra space proportional to nn.
 */
public class NaiveUnionFind {
    private int[] id;

    public NaiveUnionFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean isConnected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        if (pid == qid) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == qid) {
                id[i] = pid;
            }
        }
    }

}
