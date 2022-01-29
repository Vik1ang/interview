package leetcode.top100.q437;

import dataStructure.binaryTree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    private Map<Integer, Integer> prefixMap;
    private int target;

    public int pathSum(TreeNode root, int targetSum) {
        prefixMap = new HashMap<>();
        target = targetSum;

        prefixMap.put(0, 1);

        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int currSum) {
        if (node == null) {
            return 0;
        }

        int res = 0;
        currSum += node.val;

        res += prefixMap.getOrDefault(currSum - target, 0);
        prefixMap.put(currSum, prefixMap.getOrDefault(currSum, 0) + 1);

        int left = dfs(node.left, currSum);
        int right = dfs(node.right, currSum);

        res = res + left + right;

        prefixMap.put(currSum, prefixMap.get(currSum) - 1);

        return res;
    }
}
