# 图论

- [图论](#图论)
    - [图的遍历](#图的遍历)
    - [q797. 所有可能的路径](#q797-所有可能的路径)

### [图的遍历]()

```java
// 记录被遍历过的节点
boolean[] visited;
// 记录从起点到当前节点的路径
boolean[] onPath;

/* 图遍历框架 */
void traverse(Graph graph, int s) {
    if (visited[s]) return;
    // 经过节点 s，标记为已遍历
    visited[s] = true;
    // 做选择：标记节点 s 在路径上
    onPath[s] = true;
    for (int neighbor : graph.neighbors(s)) {
        traverse(graph, neighbor);
    }
    // 撤销选择：节点 s 离开路径
    onPath[s] = false;
}

![](https://labuladong.gitee.io/algo/images/%e8%bf%ad%e4%bb%a3%e9%81%8d%e5%8e%86%e4%ba%8c%e5%8f%89%e6%a0%91/1.gif)

```java
void traverse(TreeNode root) {
    if (root == null) return;
    System.out.println("enter: " + root.val);
    for (TreeNode child : root.children) {
        traverse(child);
    }
    System.out.println("leave: " + root.val);
}

void traverse(TreeNode root) {
    if (root == null) return;
    for (TreeNode child : root.children) {
        System.out.println("enter: " + child.val);
        traverse(child);
        System.out.println("leave: " + child.val);
    }
}
```

### [q797. 所有可能的路径](https://leetcode-cn.com/problems/all-paths-from-source-to-target/)

深度优先

```java
public class Solution {
    // 记录所有路径
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // 维护递归过程中经过的路径
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return res;
    }

    private void traverse(int[][] graph, int s, LinkedList<Integer> path) {

        // 添加节点 s 到路径
        path.addLast(s);

        int n = graph.length;
        // 到达终点
        if (s == n - 1) {
            res.add(new LinkedList<>(path));
            path.removeLast();
            return;
        }

        // 递归每个相邻节点
        for (int v : graph[s]) {
            traverse(graph, v, path);
        }

        // 从路径移出节点 s
        path.removeLast();

    }
}
```
