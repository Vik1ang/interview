package leetcode.top100.q581;

public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int maxN = Integer.MIN_VALUE, minN = Integer.MAX_VALUE;
        int left = -1, right = -1;

        for (int i = 0; i < n; i++) {
            if (maxN > nums[i]) {
                right = i;
            } else {
                maxN = nums[i];
            }

            if (minN < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minN = nums[n - i - 1];
            }
        }

        return right == -1 ? 0 : right - left + 1;
    }
}
