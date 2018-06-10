package com.algo.quiz;


import java.util.ArrayList;
import java.util.List;

/**
 * Decimal dominants. Given an array with nn keys, design an algorithm to find all values that occur more than n/10n/10
 * times. The expected running time of your algorithm should be linear.
 * <p>
 * Hint: determine the (n/10)^{th}(n/10)
 * the largest key using quickselect and check if it occurs more than n/10n/10 times.
 * <p>
 * Alternate solution hint: use 9 counters.
 */
public class DecimalDominants {

    private static Element[] elements = new Element[9];

    private static class Element {
        public int element;
        public int count;

        public Element(int e, int c) {
            this.element = e;
            this.count = c;
        }
    }

    public static List<Integer> findElements(int[] a, int n) {

        //Init temp[]
        for (int i = 0; i < 9; i++) {
            elements[i] = new Element(0, 0);

        }

        //increase
        for (int i = 0; i < n; i++) {
            int id = findId(a[i]);
            if (id >= 0) {
                elements[id].count++;
            } else {
                addToElements(a[i]);
            }
        }
        return result(a);
    }

    private static List<Integer> result(int[] a) {
        for (int i = 0; i < 9; i++) {
            elements[i].count = 0;
            for (int j = 0; j < a.length; j++) {
                if (a[j] == elements[i].element) {
                    elements[i].count++;
                }
            }
        }
        List<Integer> elemList = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            if (elements[i].count > a.length / 10) {
                elemList.add(elements[i].element);
            }

        }
        return elemList;
    }

    private static void addToElements(int elem) {
        boolean insertFlag = false;
        while (!insertFlag) {
            for (int i = 0; i < 9; i++) {
                elements[i].count--;
                if (elements[i].count <= 0) {
                    elements[i].count = 1;
                    elements[i].element = elem;
                    insertFlag = true;
                    break;
                }
            }
        }
    }

    private static int findId(int elem) {
        for (int i = 0; i < 9; i++) {
            if (elements[i].element == elem) {
                return i;
            } else if (elements[i].count == 0) {
                elements[i].element = elem;
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] argc) {
        int[] ar = new int[]{1, 2, 3, 3, 5, 1, 2, 1, 1, 1, 2, 10, 3, 60, 11, 23, 1321, 1321, 4333, 1123, 1111, 565, 432, 673};
        findElements(ar, ar.length);
    }


}
