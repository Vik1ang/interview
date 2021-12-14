package dataStructure.binaryTree.q116.connect;


public class Solution1 {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        connectTwoNode(root.left, root.right);

        return root;
    }

    private void connectTwoNode(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        // 前序遍历位置
        // 将传入的两个节点连接
        node1.next = node2;

        // 连接相同父节点的两个子节点
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        connectTwoNode(node1.right, node2.left);
    }
}
