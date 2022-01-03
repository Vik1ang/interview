package top100.q5;

// https://leetcode-cn.com/problems/longest-palindromic-substring/
public class Solution {
    public String longestPalindrome(String s) {
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心的最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }

        return res;
    }

    private String palindrome(String s, int left, int right) {
        // 防止索引越界
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 返回以 s[l] 和 s[r] 为中心的最长回文串
        return s.substring(left + 1, right);
    }
}
