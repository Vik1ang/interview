package dataStructure.binaryTree.q226.invertTree;

import dataStructure.binaryTree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class Solution2 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 将二叉树中的节点逐层放入队列中, 再迭代处理队列中的元素
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // 每次都从队列中拿一个节点, 并交换这个节点的左右子树
            TreeNode temp = queue.poll();
            TreeNode left = temp.left;
            temp.left = temp.right;
            temp.right = left;
            // 如果当前节点的左子树不为空, 则放入队列等待后续处理
            if (temp.left != null) {
                queue.add(temp.left);
            }
            // 如果当前节点的右子树不为空, 则放入队列等待后续处理
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }

        return root;
    }
}
