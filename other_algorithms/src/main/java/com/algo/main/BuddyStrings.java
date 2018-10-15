package com.algo.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given two strings A and B of lowercase letters, return true if and only
 * if we can swap two letters in A so that the result equals B.
 */
public class BuddyStrings {

	public boolean buddyStrings(String A, String B) {
		if (A.length() != B.length()) {
			return false;
		}
		if (A.equals(B)) {
			Set<Character> hashSet = new HashSet<>();
			for (char c : A.toCharArray()) {
				if (!hashSet.add(c)) {
					return true;
				}
			}
			return hashSet.size() < A.length();
		}
		List<Integer> diff = new ArrayList<>();
		for (int i = 0; i < A.length(); i++) {
			if (A.charAt(i) != B.charAt(i)) {
				diff.add(i);
			}
		}
		return diff.size() == 2
			&& A.charAt(diff.get(0)) == B.charAt(diff.get(1))
			&& A.charAt(diff.get(1)) == B.charAt(diff.get(0));
	}

	public static void main(String[] args) {
		BuddyStrings buddyStrings = new BuddyStrings();
		String A = "aaaaba";
		String B = "aaaaab";
		boolean result = buddyStrings.buddyStrings(A, B);
		System.out.println(result);
	}

}
