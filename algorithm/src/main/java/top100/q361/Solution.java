package top100.q361;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        HashSet<String> currSet = new HashSet<>();
        currSet.add(s);

        while (true) {
            for (String str : currSet) {
                if (isValid(str)) {
                    res.add(str);
                }
            }
            if (res.size() > 0) {
                return res;
            }
            HashSet<String> nextSet = new HashSet<>();
            for (String str : currSet) {
                for (int i = 0; i < str.length(); i++) {
                    if (i > 0 && str.charAt(i) == str.charAt(i - 1)) {
                        continue;
                    }
                    if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                        nextSet.add(str.substring(0, i) + str.substring(i + 1));
                    }
                }
            }
            currSet = nextSet;
        }
    }

    private boolean isValid(String str) {
        char[] chars = str.toCharArray();
        int count = 0;

        for (char c : chars) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }

        return count == 0;
    }
}
