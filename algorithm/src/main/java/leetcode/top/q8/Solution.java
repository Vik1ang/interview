package leetcode.top.q8;

public class Solution {
    public int myAtoi(String s) {
        if (s == null) {
            return 0;
        }

        int len = s.length();
        char[] charArray = s.toCharArray();

        int index = 0;
        while (index < len && charArray[index] == ' ') {
            index++;
        }
        if (index == len) {
            return 0;
        }

        int sign = 1;
        char firstChar = charArray[index];
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }

        int res = 0;
        int MAX = Integer.MAX_VALUE / 10, MIN = Integer.MIN_VALUE / 10;
        while (index < len) {
            char curr = charArray[index];
            if (curr > '9' || curr < '0') {
                break;
            }

            if (res > MAX || (res == MAX && (curr - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < MIN || (res == MIN && (curr - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            res = res * 10 + sign * (curr - '0');
            index++;
        }

        return res;
    }
}
