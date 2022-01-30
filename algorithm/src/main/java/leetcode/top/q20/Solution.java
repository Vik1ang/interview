package leetcode.top.q20;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && stack.peekFirst() == leftOf(c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private Character leftOf(char c) {
        switch (c) {
            case ')':
                return '(';
            case ']':
                return '[';
            default:
                return '{';
        }
    }
}
