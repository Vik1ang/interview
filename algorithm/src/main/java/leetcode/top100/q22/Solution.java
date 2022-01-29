package leetcode.top100.q22;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        ArrayList<String> res = new ArrayList<>();
        StringBuilder track = new StringBuilder();

        backtrack(n, n, track, res);
        return res;
    }

    private void backtrack(int left, int right, StringBuilder track, ArrayList<String> res) {
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
        backtrack(left - 1, right, track, res);
        track.deleteCharAt(track.length() - 1);

        track.append(')');
        backtrack(left, right - 1, track, res);
        track.deleteCharAt(track.length() - 1);
    }
}
