package com.algo.main;

import static java.lang.Math.max;

public class DiameterBT {

  int diameter;

  public static class TreeNode {

    int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
  
  public int diameterOfBinaryTree(TreeNode root) {
    diameter = 1;
    depth(root);
    return diameter - 1;
  }

  private int depth(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int L = depth(node.left);
    int R = depth(node.right);
    diameter = max(R + L + 1, diameter);
    return max(R, L) + 1;
  }

  public static void main(String[] args) {
    TreeNode treeNode = new TreeNode(5);
  }

}
