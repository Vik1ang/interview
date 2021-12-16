# 图论

- [图论](#图论)
    - [图的遍历](#图的遍历)
    - [q797. 所有可能的路径](#q797-所有可能的路径)
    - [207. 课程表](#207-课程表)
    - [q210. 课程表 II](#q210-课程表-ii)
    - [q785. 判断二分图](#q785-判断二分图)
    - [q886. 可能的二分法\](#q886-可能的二分法)
    - [Union Find](#union-find)
    - [130. 被围绕的区域](#130-被围绕的区域)
    - [q990. 等式方程的可满足性](#q990-等式方程的可满足性)

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

### [207. 课程表](https://leetcode-cn.com/problems/course-schedule/)

DFS

```java
public class Solution {
    // 记录一次 traverse 递归经过的节点
    public boolean[] onPath;
    // 记录遍历过的节点，防止走回头路
    public boolean[] visited;
    // 记录图中是否有环
    public boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            // 遍历图中的所有节点
            traverse(graph, i);
        }

        // 只要没有循环依赖可以完成所有课程
        return !hasCycle;
    }

    private void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            // 出现环
            hasCycle = true;
        }

        if (visited[s] || hasCycle) {
            // 如果已经找到了环，也不用再遍历了
            return;
        }

        // 前序遍历代码位置
        visited[s] = true;
        onPath[s] = true;

        for (Integer t : graph[s]) {
            traverse(graph, t);
        }

        // 后序遍历代码位置
        onPath[s] = false;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 图中共有 numCourses 个节点
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            // 修完课程 from 才能修课程 to
            // 在图中添加一条从 from 指向 to 的有向边
            graph[from].add(to);
        }

        return graph;
    }
}
```

### [q210. 课程表 II](https://leetcode-cn.com/problems/course-schedule-ii/)

拓扑排序

```java
public class Solution {
    // 记录一次 traverse 递归经过的节点
    public boolean[] onPath;
    // 记录遍历过的节点，防止走回头路
    public boolean[] visited;
    // 记录图中是否有环
    public boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            // 遍历图中的所有节点
            traverse(graph, i);
        }

        // 只要没有循环依赖可以完成所有课程
        return !hasCycle;
    }

    private void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            // 出现环
            hasCycle = true;
        }

        if (visited[s] || hasCycle) {
            // 如果已经找到了环，也不用再遍历了
            return;
        }

        // 前序遍历代码位置
        visited[s] = true;
        onPath[s] = true;

        for (Integer t : graph[s]) {
            traverse(graph, t);
        }

        // 后序遍历代码位置
        onPath[s] = false;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 图中共有 numCourses 个节点
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            // 修完课程 from 才能修课程 to
            // 在图中添加一条从 from 指向 to 的有向边
            graph[from].add(to);
        }

        return graph;
    }
}
```

### [q785. 判断二分图](https://leetcode-cn.com/problems/is-graph-bipartite/)

DFS

```java
public class Solution {
    // 记录图是否符合二分图性质
    private boolean ok = true;
    // 记录图中节点的颜色，false 和 true 代表两种不同颜色
    private boolean[] color;
    // 记录图中节点是否被访问过
    private boolean[] visited;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new boolean[n];
        visited = new boolean[n];
        // 因为图不一定是联通的，可能存在多个子图
        // 所以要把每个节点都作为起点进行一次遍历
        // 如果发现任何一个子图不是二分图，整幅图都不算二分图
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                traverse(graph, i);
            }
        }
        return ok;
    }

    private void traverse(int[][] graph, int v) {
        // 如果已经确定不是二分图了，就不用浪费时间再递归遍历了
        if (!ok) {
            return;
        }

        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                // 相邻节点 w 没有被访问过
                // 那么应该给节点 w 涂上和节点 v 不同的颜色
                color[w] = !color[v];
                // 继续遍历 w
                traverse(graph, w);
            } else {
                // 相邻节点 w 已经被访问过
                // 根据 v 和 w 的颜色判断是否是二分图
                if (color[w] == color[v]) {
                    // 若相同, 则此图不是二分图
                    ok = false;
                }
            }
        }
    }
}
```

BFS

```java
public class Solution {
    // 记录图是否符合二分图性质
    private boolean ok = true;
    // 记录图中节点的颜色，false 和 true 代表两种不同颜色
    private boolean[] color;
    // 记录图中节点是否被访问过
    private boolean[] visited;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color =  new boolean[n];
        visited =  new boolean[n];

        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                // 改为使用 BFS 函数
                bfs(graph, v);
            }
        }

        return ok;
    }

    // 从 start 节点开始进行 BFS 遍历
    private void bfs(int[][] graph, int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.offer(start);

        while (!q.isEmpty() && ok) {
            int v = q.poll();
            // 从节点 v 向所有相邻节点扩散
            for (int w : graph[v]) {
                if (!visited[w]) {
                    // 相邻节点 w 没有被访问过
                    // 那么应该给节点 w 涂上和节点 v 不同的颜色
                    color[w] = !color[v];
                    // 标记 w 节点，并放入队列
                    visited[w] = true;
                    q.offer(w);
                } else {
                    // 相邻节点 w 已经被访问过
                    // 根据 v 和 w 的颜色判断是否是二分图
                    if (color[w] == color[v]) {
                        // 若相同，则此图不是二分图
                        ok = false;
                    }
                }
            }
        }
    }
}
```

### [q886. 可能的二分法](https://leetcode-cn.com/problems/possible-bipartition/)\

类似 [q785. 判断二分图](#q785-判断二分图)

DFS

```java
public class Solution {
    private boolean ok = true;
    private boolean[] color;
    private boolean[] visited;

    public boolean possibleBipartition(int n, int[][] dislikes) {

        // 图节点编号从 1 开始
        color = new boolean[n + 1];
        visited = new boolean[n + 1];

        List<Integer>[] graph = buildGraph(n, dislikes);

        for (int v = 1; v <= n; v++) {
            if (!visited[v]) {
                traverse(graph, v);
            }
        }

        return ok;
    }

    private void traverse(List<Integer>[] graph, int v) {
        if (!ok) {
            return;
        }
        visited[v] = true;

        for (Integer w : graph[v]) {
            if (!visited[w]) {
                color[w] = !color[v];
                traverse(graph, w);
            } else {
                if (color[w] == color[v]) {
                    ok = false;
                }
            }
        }
    }

    private List<Integer>[] buildGraph(int n, int[][] dislikes) {
        // 图节点编号为 1...n
        List<Integer>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : dislikes) {
            int v = edge[1];
            int w = edge[0];
            // 「无向图」相当于「双向图」

            // v -> w
            graph[v].add(w);
            // w -> v
            graph[w].add(v);
        }

        return graph;
    }
}
```

### [Union Find]()

```java
public class UnionFind {
    // 记录连通分量个数
    private int count;
    // 存储若干棵树
    private int[] parent;
    // 记录树的“重量”
    private int[] size;

    public UnionFind(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /* 将 p 和 q 连通 */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;

        // 小树接到大树下面，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    /* 判断 p 和 q 是否互相连通 */
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        // 处于同一棵树上的节点，相互连通
        return rootP == rootQ;
    }

    /* 返回节点 x 的根节点 */
    private int find(int x) {
        while (parent[x] != x) {
            // 进行路径压缩
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return count;
    }
}
```

### [130. 被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/)

并查集 [Union Find](#union-find)

```java
public class Solution {
    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        // 给 dummy 留一个额外位置
        UnionFind unionFind = new UnionFind(m * n + 1);
        int dummy = m * n;

        // 将首列和末列的 O 与 dummy 连通
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                unionFind.union(i * n, dummy);
            }
            if (board[i][n - 1] == 'O') {
                unionFind.union(i * n + n - 1, dummy);
            }
        }

        // 将首行和末行的 O 与 dummy 连通
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                unionFind.union(j, dummy);
            }
            if (board[m - 1][j] == 'O') {
                unionFind.union(n * (m - 1) + j, dummy);
            }
        }

        // 方向数组 d 是上下左右搜索的常用手法
        int[][] d = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    // 将此 O 与上下左右的 O 连通
                    for (int k = 0; k < 4; k++) {
                        int x = i + d[k][0];
                        int y = j + d[k][1];
                        if (board[x][y] == 'O') {
                            unionFind.union(x * n + y, i * n + j);
                        }
                    }
                }
            }
        }

        // 所有不和 dummy 连通的 O，都要被替换
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!unionFind.connected(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }

    }
}
```

### [q990. 等式方程的可满足性](https://leetcode-cn.com/problems/satisfiability-of-equality-equations/)

并查集 [Union Find](#union-find)

```java
public class Solution {
    public boolean equationsPossible(String[] equations) {
        // 26 个英文字母
        UnionFind unionFind = new UnionFind(26);
        // 先让相等的字母形成连通分量
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                char x = equation.charAt(0);
                char y = equation.charAt(3);
                unionFind.union(x - 'a', y - 'a');
            }
        }

        // 检查不等关系是否打破相等关系的连通性
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                char x = equation.charAt(0);
                char y = equation.charAt(3);
                // 如果相等关系成立，就是逻辑冲突
                if (unionFind.connected(x - 'a', y - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }
}
```