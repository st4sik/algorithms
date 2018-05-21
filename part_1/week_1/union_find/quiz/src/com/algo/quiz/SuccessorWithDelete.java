package com.algo.quiz;

/**
 * Given a set of nn integers S = \{ 0, 1, ... , n-1 \}S={0,1,...,n−1} and a sequence of requests of the following form:
 * <p>
 * Remove xx from SS
 * Find the successor of xx: the smallest yy in SS such that y \ge xy≥x.
 * design a data type so that all operations (except construction) take logarithmic time or better in the worst case.
 */
public class SuccessorWithDelete {
    private UFWithFindLargest ufWithFindLargest;
    private int N;
    private boolean data[];

    public SuccessorWithDelete(int N) {
        this.N = N;
        this.data = new boolean[N];
        for (int i = 0; i < N; ++i)
            data[i] = true;
        this.ufWithFindLargest = new UFWithFindLargest(N);
    }

    public void remove(int x) {
        data[x] = false;
        if (x > 0 && !data[x-1])
            ufWithFindLargest.union(x, x-1);
        if (x < N - 1 && !data[x+1])
            ufWithFindLargest.union(x, x+1);
    }

    public int successor(int x) {
        if (data[x]) {
            return x;
        } else {
            int res = ufWithFindLargest.find(x) + 1;
            if (res >= N) {
                System.out.println("Successor was not found");
                return -1;
            } else {
                return res;
            }
        }
    }

    public static void main(String[] args) {
        SuccessorWithDelete successorWithDelete = new SuccessorWithDelete(10);
        successorWithDelete.remove(2);
        System.out.println(successorWithDelete.successor(2) == 3);

        successorWithDelete.remove(5);
        successorWithDelete.remove(4);
        System.out.println(successorWithDelete.successor(3) == 3);

    }
}
