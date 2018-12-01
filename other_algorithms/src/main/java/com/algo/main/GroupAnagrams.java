package com.algo.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs.length == 0) {
			return new ArrayList();
		}
		Map<String, List> ans = new HashMap<>();
		for (String str : strs) {
			char[] chars = str.toCharArray();
			Arrays.sort(chars);
			String key = String.valueOf(chars);
			if (!ans.containsKey(key)) {
				ans.put(key, new ArrayList());
			}
			ans.get(key).add(str);

		}
		return new ArrayList(ans.values());
	}

	public static void main(String[] args) {
		GroupAnagrams groupAnagrams = new GroupAnagrams();
		String[] strings = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
		List<List<String>> lists = groupAnagrams.groupAnagrams(strings);
	}
}
