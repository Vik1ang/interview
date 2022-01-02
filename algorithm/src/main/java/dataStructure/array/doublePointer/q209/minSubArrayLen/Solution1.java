package dataStructure.array.doublePointer.q209.minSubArrayLen;

public class Solution1 {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (right < nums.length) {
            int num = nums[right];
            right++;

            sum += num;

            if (sum >= target) {
                res = Math.min(res, right - left);
            }

            while (sum >= target) {
                int temp = nums[left];
                left++;

                sum -= temp;
                if (sum >= target) {
                    res = Math.min(res, right - left);
                }
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
