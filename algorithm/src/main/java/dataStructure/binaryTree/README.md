# 二叉树

- [二叉树](#二叉树)
    - [226. 翻转二叉树](#226-翻转二叉树)
    - [q116. 填充每个节点的下一个右侧节点指针](#q116-填充每个节点的下一个右侧节点指针)
    - [](#)

### [226. 翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/)

我们发现只要把二叉树上的每一个节点的左右子节点进行交换, 最后的结果就是完全翻转之后的二叉树

```java
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        // base case
        if (root == null) {
            return root;
        }

        // 前序遍历位置
        // root 节点需要交换它的左右子节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 让左右子节点继续翻转它们的子节点
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
```

迭代方式

![](https://pic.leetcode-cn.com/f9e06159617cbf8372b544daee37be70286c3d9b762c016664e225044fc4d479-226_%E8%BF%AD%E4%BB%A3.gif)

```java
public class Solution {
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
```

### [q116. 填充每个节点的下一个右侧节点指针](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/)

递归

```java
public class Solution {
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
```

迭代

```java
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
```

### []()

递归

![](https://labuladong.gitee.io/algo/images/%e4%ba%8c%e5%8f%89%e6%a0%91%e7%b3%bb%e5%88%97/2.jpeg)

1. 将 `root` 的左子树和右子树拉平
2. 将 `root` 的右子树接到左子树下方, 然后将整个左子树作为右子树

```java
public class Solution1 {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);

        // 后序遍历位置
        // 1. 左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2. 将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3. 将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }
}
```

迭代

前序遍历, 由于将节点展开之后会破坏二叉树的结构而丢失子节点的信息, 同时进行

每次从栈内弹出一个节点作为当前访问的节点, 获得该节点的子节点, 如果子节点不为空, 则依次将右子节点和左子节点压入栈内(注意入栈顺序)

```java
public class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode prev = null;

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (prev != null) {
                prev.left = null;
                prev.right = curr;
            }
            TreeNode left = curr.left, right = curr.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
            prev = curr;
        }
    }
}

```