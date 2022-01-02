package dataStructure.binaryTree.q117.connect;

import java.util.Deque;
import java.util.LinkedList;

public class Solution1 {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Deque<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node node = null;
            Node preNode = null;

            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    preNode = queue.poll(); // 取出本层头一个节点
                    node = preNode;
                } else {
                    node = queue.poll();
                    preNode.next = node; // 本层前一个节点 next 指向当前节点
                    preNode = preNode.next;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            preNode.next = null; // 本层最后一个节点 next 指向 null
        }
        return root;
    }
}
