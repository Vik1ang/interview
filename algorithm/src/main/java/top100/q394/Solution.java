package top100.q394;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;


public class Solution {
    private int ptr;

    public String decodeString(String s) {
        LinkedList<String> stack = new LinkedList<String>();

        ptr = 0;
        while (ptr < s.length()) {
            char curr = s.charAt(ptr);
            if (Character.isDigit(curr)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stack.addLast(digits);
            } else if (Character.isLetter(curr) || curr == '[') {
                // 获取一个字母并进栈
                stack.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ptr++;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stack.peekLast())) {
                    sub.addLast(stack.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stack.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repeatTime = Integer.parseInt(stack.removeLast());
                StringBuilder t = new StringBuilder();
                String o = getString(sub);

                while (repeatTime-- > 0) {
                    t.append(o);
                }
                stack.addLast(t.toString());
            }
        }

        return getString(stack);
    }

    private String getString(LinkedList<String> v) {
        StringBuilder sb = new StringBuilder();
        for (String s : v) {
            sb.append(s);
        }
        return sb.toString();
    }

    private String getDigits(String s) {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(s.charAt(ptr))) {
            sb.append(s.charAt(ptr++));
        }
        return sb.toString();
    }
}
