package com.algo.main;

public class StringReverse {
    public String reverseString(String s) {
        char[] str = new char[s.length()];
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            str[n - i - 1] = s.charAt(i);
        }
        return String.valueOf(str);
    }
}