package dataStructure.binaryTree.q104.maxDepth;

import dataStructure.binaryTree.TreeNode;

    public class Solution2 {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            } else {
                int left = maxDepth(root.left);
                int right = maxDepth(root.right);
                return Math.max(left, right) + 1;
            }
        }
    }
