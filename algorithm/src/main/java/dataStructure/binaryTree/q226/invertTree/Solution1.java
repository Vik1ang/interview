package dataStructure.binaryTree.q226.invertTree;

import dataStructure.binaryTree.TreeNode;

public class Solution1 {
    public TreeNode invertTree(TreeNode root) {
        // base case
        if (root == null) {
            return root;
        }

        // 前序遍历位置
        // root 节点需要交换它的左右子节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 让左右子节点继续翻转它们的子节点
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
