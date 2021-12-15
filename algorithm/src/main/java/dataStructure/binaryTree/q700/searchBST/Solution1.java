package dataStructure.binaryTree.q700.searchBST;

import dataStructure.binaryTree.TreeNode;

public class Solution1 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return root;
        }

        // 去左子树搜索
        if (root.val > val) {
            return searchBST(root.left, val);
        }

        // 去右子树搜索
        if (root.val < val) {
            return searchBST(root.right, val);
        }
        return root;
    }
}
