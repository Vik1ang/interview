package leetcode.top.q7;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int reverse(int x) {
        int MAX = Integer.MAX_VALUE / 10, MIN = Integer.MIN_VALUE / 10;
        int res = 0;
        while (x != 0) {
            int temp = x % 10;
            if (res > MAX || (res == MAX && temp > 7)) {
                return 0;
            }
            if (res < MIN || res == MIN && temp < -8) {
                return 0;
            }
            res = res * 10 + temp;
            x /= 10;
        }

        return res;
    }
}

