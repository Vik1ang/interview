package leetcode.top100.q647;

public class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        int res = 0;
        for (int center = 0; center < 2 * n - 1; center++) {
            int left = center / 2;
            int right = left + center % 2;

            while (left >= 0 && right < s.length() && s.charAt(left--) == s.charAt(right++)) {
                res++;
            }
        }

        return res;
    }
}
