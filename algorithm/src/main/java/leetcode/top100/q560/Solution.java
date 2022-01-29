package leetcode.top100.q560;

import java.util.HashMap;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);

        int res = 0, sum0_i = 0;
        for (int i = 0; i < n; i++) {
            sum0_i += nums[i];

            int sum0_j = sum0_i - k;
            if (prefixSumMap.containsKey(sum0_j)) {
                res += prefixSumMap.get(sum0_j);
            }
            prefixSumMap.put(sum0_i, prefixSumMap.getOrDefault(sum0_i, 0) + 1);
        }

        return res;
    }
}
