package leetcode.top100.q538;

import dataStructure.binaryTree.TreeNode;

public class Solution {
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }

        traverse(node.right);
        sum += node.val;
        node.val = sum;
        traverse(node.left);
    }
}
