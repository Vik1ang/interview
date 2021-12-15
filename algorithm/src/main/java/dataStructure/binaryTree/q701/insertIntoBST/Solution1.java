package dataStructure.binaryTree.q701.insertIntoBST;

import dataStructure.binaryTree.TreeNode;

public class Solution1 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        // BST 中一般不会插入已存在元素
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }

        return root;
    }
}
