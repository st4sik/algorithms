package com.algo.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Map<String, String> map = new HashMap<>();
        Stack<Character> characters = new Stack<>();
        map.put("(", ")");
        map.put("[", "]");
        map.put("{", "}");

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.keySet().contains(c)) {
                characters.push(c);
            } else {
                if (map.values().contains(c)) {
                    if (!characters.isEmpty()) {
                        if (map.get(characters.peek()).equals(c)) {
                            characters.pop();
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
