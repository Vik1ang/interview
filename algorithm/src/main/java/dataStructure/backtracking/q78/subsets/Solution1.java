package dataStructure.backtracking.q78.subsets;

import java.util.LinkedList;
import java.util.List;

public class Solution1 {
    public List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        // 记录走过的路径
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, 0, track);
        return res;
    }

    private void backtrack(int[] nums, int start, LinkedList<Integer> track) {
        res.add(new LinkedList<>(track));

        // 注意 i 从 start 开始递增
        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.add(nums[i]);
            System.out.println(track);
            // 回溯
            backtrack(nums, i + 1, track);
            // 撤销选择
            track.removeLast();
        }
    }
}
