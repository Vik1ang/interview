package top100.q20;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && leftOf(c) == stack.peekFirst()) {
                    stack.pop();
                } else {
                    // 和最近的左括号不匹配
                    return false;
                }
            }
        }

        // 是否所有的左括号都被匹配了
        return stack.isEmpty();
    }

    private Character leftOf(char c) {
        switch (c) {
            case ')':
                return '(';
            case '}':
                return '{';
            default:
                return '[';
        }
    }
}
