package dataStructure.array.prefixSum.q560.subarraySum;

public class Solution1 {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // 构造前缀和
        int[] preSum = new int[n + 1];

        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = nums[i - 1] + preSum[i - 1];
        }

        int res = 0;
        // 穷举所有子数组
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 子数组 nums[j..i-1] 的元素和
                if (preSum[i + 1] - preSum[j] == k) {
                    res++;
                }
            }
        }

        return res;
    }
}
