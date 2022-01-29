package leetcode.top.q3;

import java.util.HashMap;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int res = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            window.put(c, window.getOrDefault(c, 0) + 1);

            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;

                if (window.containsKey(d)) {
                    window.put(d, window.get(d) - 1);
                }
            }
            res = Math.max(res, right - left);
        }

        return res;
    }
}
