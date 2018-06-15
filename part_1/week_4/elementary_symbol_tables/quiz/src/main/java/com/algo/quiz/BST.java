package com.algo.quiz;

import java.util.Objects;

public class BST {

	public static class TreeNode {

		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public boolean isValidBST(TreeNode root) {
		if (Objects.isNull(root)) {
			return true;
		}
		int nodeValue = root.val;

		if (Objects.nonNull(root.left)) {
			TreeNode leftSide = root.left;
			while (Objects.nonNull(leftSide)) {
				if (leftSide.val >= nodeValue) {
					return false;
				}
				leftSide = leftSide.right;
			}
		}

		if (Objects.nonNull(root.right)) {
			TreeNode rightSide = root.right;
			while (Objects.nonNull(rightSide)) {
				if (rightSide.val <= nodeValue) {
					return false;
				}
				rightSide = rightSide.left;
			}
		}

		if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
			return true;
		}
		if (Objects.isNull(root.left)) {
			return isValidBST(root.right);
		}
		return isValidBST(root.left) && isValidBST(root.right);
	}

}
