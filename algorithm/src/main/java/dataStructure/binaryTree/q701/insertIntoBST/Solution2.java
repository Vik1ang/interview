package dataStructure.binaryTree.q701.insertIntoBST;

import dataStructure.binaryTree.TreeNode;

public class Solution2 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        TreeNode curr = root;
        while (curr != null) {
            if (curr.val > val) {
                if (curr.left == null) {
                    curr.left = new TreeNode(val);
                    break;
                } else {
                    curr = curr.left;
                }
            } else {
                if (curr.right == null) {
                    curr.right = new TreeNode(val);
                    break;
                } else {
                    curr = curr.right;
                }
            }
        }
        return root;
    }
}
