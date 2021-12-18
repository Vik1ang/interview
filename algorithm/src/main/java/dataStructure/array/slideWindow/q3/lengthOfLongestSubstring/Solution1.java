package dataStructure.array.slideWindow.q3.lengthOfLongestSubstring;

import java.util.HashMap;

public class Solution1 {
    public int lengthOfLongestSubstring(String s) {

        HashMap<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        int res = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            window.put(c, window.getOrDefault(c, 0) + 1);

            // 判断左侧窗口是否要收缩
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;

                if (window.containsKey(d)) {
                    window.put(d, window.get(d) - 1);
                }
            }

            res = Math.max(right - left, res);

        }

        return res;
    }
}
