package dataStructure.bfsANDdfs.q111.minDepth;

import dataStructure.binaryTree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class Solution1 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                /* 判断是否到达终点 */
                if (curr.left == null && curr.right == null) {
                    return depth;
                }
                /* 将 cur 的相邻节点加入队列 */
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            /* 这里增加步数 */
            depth++;
        }

        return depth;
    }
}
