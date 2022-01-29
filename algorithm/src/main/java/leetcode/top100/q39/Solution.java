package leetcode.top100.q39;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        backtrack(candidates, target, res, combine, 0);
        return res;
    }

    private void backtrack(int[] candidates, int target, List<List<Integer>> res, List<Integer> combine, int index) {
        if (index == candidates.length) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<Integer>(combine));
            return;
        }

        backtrack(candidates, target, res, combine, index + 1);

        if (target - candidates[index] >= 0) {
            combine.add(candidates[index]);
            backtrack(candidates, target - candidates[index], res, combine, index);
            combine.remove(combine.size() - 1);
        }
    }
}
