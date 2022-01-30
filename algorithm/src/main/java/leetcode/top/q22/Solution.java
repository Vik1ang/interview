package leetcode.top.q22;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        StringBuilder track = new StringBuilder();
        backtrack(n, n, res, track);

        return res;
    }

    private void backtrack(int left, int right, ArrayList<String> res, StringBuilder track) {
        if (right < left) {
            return;
        }

        if (left < 0 || right < 0) {
            return;
        }

        if (left == 0 && right == 0) {
            res.add(track.toString());
            return;
        }

        track.append('(');
        backtrack(left - 1, right, res, track);
        track.deleteCharAt(track.length() - 1);

        track.append(')');
        backtrack(left, right - 1, res, track);
        track.deleteCharAt(track.length() - 1);
    }
}
