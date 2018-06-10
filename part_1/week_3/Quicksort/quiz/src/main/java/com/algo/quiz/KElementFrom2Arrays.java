package com.algo.quiz;

/**
 * Selection in two sorted arrays. Given two sorted arrays a[\; ]a[] and b[ \; ]b[], of sizes n_1n and n_2n,
 * respectively, design an algorithm to find the k^{th}k th largest key. The order of growth of the worst case
 * running time of your algorithm should be \log nlogn, where n = n_1 + n_2n=n
 * <p>
 * Version 1: n_1 = n_2n and k = n/2k=n/2
 * Version 2: k = n/2k=n/2
 * Version 3: no restrictions
 * <p>
 * Hint: there are two basic approaches.
 * <p>
 * Approach A: Compute the median in a[] and the median in b[]. Recur in a subproblem of roughly half the size.
 * Approach B: Design a constant-time algorithm to determine whether a[i] is the kth largest key.
 * Use this subroutine and binary search.
 * Dealing with corner cases can be tricky.
 *
 */
public class KElementFrom2Arrays {

    public static int select(int a[], int m, int b[], int n, int k) {
        assert (m >= 0);
        assert (n >= 0);
        assert (k > 0);
        assert (k <= m + n);

        int i = (int) ((double) m / (m + n) * (k - 1));
        int j = (k - 1) - i;

        assert (i >= 0);
        assert (j >= 0);
        assert (i <= m);
        assert (j <= n);

        int Ai_1 = ((i == 0) ? Integer.MIN_VALUE : a[i - 1]);
        int Bj_1 = ((j == 0) ? Integer.MIN_VALUE : b[j - 1]);
        int Ai = ((i == m) ? Integer.MAX_VALUE : a[i]);
        int Bj = ((j == n) ? Integer.MAX_VALUE : b[j]);

        if (Bj_1 < Ai && Ai < Bj)
            return Ai;
        else if (Ai_1 < Bj && Bj < Ai)
            return Bj;

        assert ((Ai > Bj && Ai_1 > Bj) ||
                (Ai < Bj && Ai < Bj_1));

        if (Ai < Bj)
            return select(a, m - i - 1, b, j, k - i - 1);
        else return select(a, i, b, n - j - 1, k - j - 1);
    }

    public static void main(String[] argc) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] b = new int[]{8, 9, 10, 11, 12, 14, 15, 16};

        int kthElement = select(a, a.length, b, b.length, 13);
        System.out.print(kthElement);
    }
}
