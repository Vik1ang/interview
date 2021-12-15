package dataStructure.binaryTree.q700.searchBST;

import dataStructure.binaryTree.TreeNode;

public class Solution2 {
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (val == root.val) {
                return root;
            }
            root = val < root.val ? root.left : root.right;
        }
        return null;
    }
}
