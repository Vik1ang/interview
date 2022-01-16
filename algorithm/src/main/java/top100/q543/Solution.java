package top100.q543;

import dataStructure.binaryTree.TreeNode;

public class Solution {
    private int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        traverse(root);
        return max;
    }

    private int traverse(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftMax = traverse(node.left);
        int rightMax = traverse(node.right);

        max = Math.max(leftMax + rightMax, max);

        return 1 + Math.max(leftMax, rightMax);
    }
}
