package com.algo.quiz;

import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;

/**
 * Intersection of two sets. Given two arrays \mathtt{a[]}a[] and \mathtt{b[]}b[],
 * each containing nn distinct 2D points in the plane, design a subquadratic algorithm to count
 * the number of points that are contained both in array \mathtt{a[]}a[] and array \mathtt{b[]}b[].
 *
 * Hint: shellsort (or any other subquadratic sort).
 */
public class Intersec2Sets {

    static class Point implements Comparable<Point> {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }


        @Override
        public int compareTo(Point o) {
            if (this.x > o.x) return 1;
            if (this.x < o.x) return -1;
            if (this.y > o.y) return 1;
            if (this.y < o.y) return -1;
            return 0;
        }
    }

    public static int countIntersection(Point[] a, Point[] b) {
        Shell.sort(a);
        Shell.sort(b);

        int count = 0;
        int j = 0;
        int i = 0;
        while (i < a.length && j < b.length) {
            if (a[i].compareTo(b[j]) == 0) {
                count++;
                i++;
                j++;
            } else if (a[i].compareTo(b[j]) < 0) {
                i++;
            } else {
                j++;
            }
        }
        return count;
    }

    public static void main(String[] argc) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(10, 1);
        Point p4 = new Point(1, 2);
        Point p5 = new Point(3, 5);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(10, 5);

        Point[] a = new Point[]{p7, p5, p3, p2};
        Point[] b = new Point[]{p2, p3, p4};
        int count = Intersec2Sets.countIntersection(a, b);
        StdOut.println(count);
    }
}
