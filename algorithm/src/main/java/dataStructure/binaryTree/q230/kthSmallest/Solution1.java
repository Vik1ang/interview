package dataStructure.binaryTree.q230.kthSmallest;

import dataStructure.binaryTree.TreeNode;

public class Solution1 {
    public int res = 0;
    public int rank = 0;
    public int kthSmallest(TreeNode root, int k) {
        // 利用 BST 的中序遍历特性
        traverse(root, k);
        return res;
    }

    private void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);

        // 中序遍历代码位置
        rank++;
        if (k == rank) {
            // 找到第 k 小的元素
            res = root.val;
            return;
        }

        traverse(root.right, k);

    }
}
