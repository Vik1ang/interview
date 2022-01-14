package top100.q297;

import dataStructure.binaryTree.TreeNode;

import java.util.LinkedList;

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        serialize(root, stringBuilder);
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(",")) {
            nodes.addLast(s);
        }
        return deserialize(nodes);
    }

    private void serialize(TreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            stringBuilder.append("#").append(",");
            return;
        }

        stringBuilder.append(root.val).append(",");

        serialize(root.left, stringBuilder);
        serialize(root.right, stringBuilder);
    }

    private TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }

        String first = nodes.removeFirst();
        if (first.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));

        root.left = deserialize(nodes);
        root.right = deserialize(nodes);

        return root;
    }
}
