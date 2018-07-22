package com.algo.quiz;

import edu.princeton.cs.algs4.KMP;

/**
 * Cyclic rotation of a string. A string s is a cyclic rotation of a string t if s and t have the
 * same length and s consists of a suffix of t followed by a prefix of t. For example, "winterbreak"
 * is a cyclic rotation of "breakwinter" (and vice versa). Design a linear-time algorithm to
 * determine whether one string is a cyclic rotation of another.
 *
 * Hint: Use Knuth-Morris-Pratt.
 */
public class CyclicRotationString {

  public boolean isCyclicRotation(String s1, String s2) {
    String concat = s1 + s1;
    KMP pattern = new KMP(s2); //https://algs4.cs.princeton.edu/53substring
    return pattern.search(concat) < concat.length();
  }

  public static void main(String[] args) {
    String str1 = "winterbreak";
    String str2 = "breakwinter";
    CyclicRotationString cyclicRotationString = new CyclicRotationString();
    boolean cyclicRotation = cyclicRotationString.isCyclicRotation(str1, str2);
    System.out.print(cyclicRotation);
  }

}
