package dataStructure.backtracking.q77.combine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution1 {

    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {

        if (k <= 0 || n <= 0) {
            return res;
        }

        LinkedList<Integer> track = new LinkedList<>();
        backtrack(n, k, 1, track);

        return res;
    }

    private void backtrack(int n, int k, int start, LinkedList<Integer> track) {
        // 到达树的底部
        if (track.size() == k) {
            res.add(new ArrayList<>(track));
            return;
        }

        // 注意 i 从 start 开始递增
        for (int i = start; i <= n; i++) {
            // 做选择
            track.add(i);
            // 回溯
            backtrack(n, k, i + 1, track);
            // 撤销选择
            track.removeLast();
        }
    }
}
