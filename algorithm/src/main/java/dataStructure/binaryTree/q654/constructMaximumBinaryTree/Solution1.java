package dataStructure.binaryTree.q654.constructMaximumBinaryTree;

import dataStructure.binaryTree.TreeNode;

public class Solution1 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return builds(nums, 0, nums.length - 1);
    }

    // 将 nums[lo..hi] 构造成符合条件的树, 返回根节点
    private TreeNode builds(int[] nums, int lo, int hi) {
        // base case
        if (lo > hi) {
            return null;
        }

        // 找到数组中的最大值和对应的索引
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }

        TreeNode root = new TreeNode(maxVal);

        // 递归调用构造左右子树
        root.left = builds(nums, lo, index - 1);
        root.right = builds(nums, index + 1, hi);

        return root;
    }
}
