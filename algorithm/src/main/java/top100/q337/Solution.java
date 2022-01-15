package top100.q337;

import dataStructure.binaryTree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }

        int doIt = root.val
                + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));

        int notDoIt = rob(root.left) + rob(root.right);

        int res = Math.max(doIt, notDoIt);

        memo.put(root, res);
        return res;
    }
}
