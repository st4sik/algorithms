package com.algo.quiz;

/**
 * Prefix free codes. In data compression, a set of binary strings is if no string is a prefix of
 * another. For example, {01,10,0010,1111} is prefix free, but {01,10,0010,10100} is not because 10
 * is a prefix of 10100. Design an efficient algorithm to determine if a set of binary strings is
 * prefix-free. The running time of your algorithm should be proportional the number of bits in all
 * of the binary stings.
 *
 * Hint: insert the binary strings into a 2-way trie.
 *
 * Remark: it's also possible to solve this problem using radix sorting or a ternary search trie.
 */
public class PrefixFreeCodes {

  private static final int R = 2;

  private Node root;
  private int n;

  private class Node {

    public boolean isString;
    private Node[] next = new Node[R];
    private String value;
  }

  public void put(String value) {
    root = put(root, value, 0);
  }

  public int size() {
    return n;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  private Node put(Node node, String value, int d) {
    if (node == null) {
      node = new Node();
    }
    if (d == value.length()) {
      if (node.next[0] != null || node.next[1] != null) {
        throw new IllegalArgumentException("");
      }
      node.value = value;
      n++;
    } else {
      if (node.value != null) {
        throw new IllegalArgumentException("Prefix = " + node.value + "exists for this input.");
      }
      int c = (int) value.charAt(d) - 48;
      node.next[c] = put(node.next[c], value, d + 1);
    }
    return node;
  }

  public static void main(String[] args) {
    PrefixFreeCodes pf = new PrefixFreeCodes();
    pf.put("01");
    pf.put("10");
    pf.put("0010");
    pf.put("1010");
    pf.put("10100");

  }

}
