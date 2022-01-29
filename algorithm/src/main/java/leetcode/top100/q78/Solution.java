package leetcode.top100.q78;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, 0, res, track);
        return res;
    }

    private void backtrack(int[] nums, int start, List<List<Integer>> res, LinkedList<Integer> track) {
        res.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums, i + 1, res, track);
            track.removeLast();
        }
    }
}
