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
    - [1135. 最低成本联通所有城市](#1135-最低成本联通所有城市)
    - [1584. 连接所有点的最小费用](#1584-连接所有点的最小费用)
    - [q277. 搜寻名人](#q277-搜寻名人)
    - [743. 网络延迟时间](#743-网络延迟时间)
    - [1631. 最小体力消耗路径](#1631-最小体力消耗路径)
    - [1514. 概率最大的路径](#1514-概率最大的路径)

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

### [1135. 最低成本联通所有城市](https://leetcode-cn.com/problems/connecting-cities-with-minimum-cost/)

Kruskal 算法

```java
 public int minimumCost(int n, int[][] connections) {
        // 城市编号为 1...n，所以初始化大小为 n + 1
        UnionFind unionFind = new UnionFind(n + 1);
        // 对所有边按照权重从小到大排序
        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));
        // 记录最小生成树的权重之和
        int mst = 0;

        for (int[] edge : connections) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            // 若这条边会产生环，则不能加入 mst
            if (unionFind.connected(u, v)) {
                continue;
            }
            // 若这条边不会产生环，则属于最小生成树
            mst += weight;
            unionFind.union(u, v);
        }

        // 保证所有节点都被连通
        // 按理说 uf.count() == 1 说明所有节点被连通
        // 但因为节点 0 没有被使用，所以 0 会额外占用一个连通分量
        return unionFind.count() == 2 ? mst : -1;
    }
```

### [1584. 连接所有点的最小费用](https://leetcode-cn.com/problems/min-cost-to-connect-all-points/)

Kruskal 算法

```java
public class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        // 生成所有边及权重
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x_i = points[i][0];
                int y_i = points[i][1];
                int x_j = points[j][0];
                int y_j = points[j][1];
                // 用坐标点在 points 中的索引表示坐标点
                edges.add(new int[]{
                        i, j, Math.abs(x_i - x_j) + Math.abs(y_i - y_j)
                });
            }
        }

        edges.sort((a, b) -> {
            return a[2] - b[2];
        });

        // 执行 Kruskal 算法
        int mst = 0;
        UnionFind unionFind = new UnionFind(n);

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            // 若这条边会产生环，则不能加入 mst
            if (unionFind.connected(u, v)) {
                continue;
            }
            // 若这条边不会产生环，则属于最小生成树
            mst += weight;
            unionFind.union(u, v);
        }

        return mst;
    }
}
```

### [q277. 搜寻名人](https://leetcode-cn.com/problems/find-the-celebrity/)

```java
public class Solution extends Relation {
    public int findCelebrity(int n) {
        // 先假设 cand 是名人
        int candidate = 0;
        for (int other = 0; other < n; other++) {
            if (!knows(other, candidate) || knows(candidate, other)) {
                // cand 不可能是名人，排除
                // 假设 other 是名人
                candidate = other;
            } else {
                // other 不可能是名人，排除
                // 什么都不用做，继续假设 cand 是名人
            }
        }

        // 现在的 cand 是排除的最后结果，但不能保证一定是名人
        for (int other = 0; other < n; other++) {
            if (candidate == other) {
                continue;
            }
            // 需要保证其他人都认识 cand，且 cand 不认识任何其他人
            if (!knows(other, candidate) || knows(candidate, other)) {
                return -1;
            }
        }

        return candidate;
    }
}
```

### [743. 网络延迟时间](https://leetcode-cn.com/problems/network-delay-time/)

```java
public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 节点编号是从 1 开始的，所以要一个大小为 n + 1 的邻接表
        List<int[]>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        // 构造图
        for (int[] edge : times) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            // from -> List<(to, weight)>
            // 邻接表存储图结构，同时存储权重信息
            graph[from].add(new int[]{to, weight});
        }

        // 启动 dijkstra 算法计算以节点 k 为起点到其他节点的最短路径
        int[] distTo = dijkstra(k, graph);

        // 找到最长的那一条最短路径
        int res = 0;
        for (int i = 1; i < distTo.length; i++) {
            if (distTo[i] == Integer.MAX_VALUE) {
                // 有节点不可达，返回 -1
                return -1;
            }
            res = Math.max(res, distTo[i]);
        }

        return res;
    }

    private int[] dijkstra(int start, List<int[]>[] graph) {
        // 定义：distTo[i] 的值就是起点 start 到达节点 i 的最短路径权重
        int[] distTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        // base case，start 到 start 的最短距离就是 0
        distTo[start] = 0;

        // 优先级队列，distFromStart 较小的排在前面
        PriorityQueue<State> priorityQueue = new PriorityQueue<>((a, b) -> {
            return a.distFromStart - b.distFromStart;
        });

        // 从起点 start 开始进行 BFS
        priorityQueue.offer(new State(start, 0));

        while (!priorityQueue.isEmpty()) {
            State currState = priorityQueue.poll();
            int currNodeId = currState.id;
            int currDistFromStart = currState.distFromStart;

            if (currDistFromStart > distTo[currNodeId]) {
                continue;
            }

            // 将 curNode 的相邻节点装入队列
            for (int[] neighbor : graph[currNodeId]) {
                int nextNodeId = neighbor[0];
                int distToNextNode = distTo[currNodeId] + neighbor[1];
                // 更新 dp table
                if (distTo[nextNodeId] > distToNextNode) {
                    distTo[nextNodeId] = distToNextNode;
                    priorityQueue.offer(new State(nextNodeId, distToNextNode));
                }
            }

        }

        return distTo;
    }
}

class State {
    // 图节点的 id
    public int id;
    // 从 start 节点到当前节点的距离
    public int distFromStart;

    public State(int id, int distFromStart) {
        this.id = id;
        this.distFromStart = distFromStart;
    }
}
```

### [1631. 最小体力消耗路径](https://leetcode-cn.com/problems/path-with-minimum-effort/)

```java
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        // 定义：从 (0, 0) 到 (i, j) 的最小体力消耗是 effortTo[i][j]
        int[][] effortTo = new int[m][n];

        // dp table 初始化为正无穷
        for (int i = 0; i < m; i++) {
            Arrays.fill(effortTo[i], Integer.MAX_VALUE);
        }

        // base case，起点到起点的最小消耗就是 0
        effortTo[0][0] = 0;

        // 优先级队列，effortFromStart 较小的排在前面
        PriorityQueue<State> priorityQueue = new PriorityQueue<>((a, b) -> {
            return a.effortFromStart - b.effortFromStart;
        });

        // 从起点 (0, 0) 开始进行 BFS
        priorityQueue.offer(new State(0, 0, 0));

        while (!priorityQueue.isEmpty()) {
            State currState = priorityQueue.poll();
            int currX = currState.x;
            int currY = currState.y;
            int currEffortFromStart = currState.effortFromStart;

            // 到达终点提前结束
            if (currX == m - 1 && currY == n - 1) {
                return currEffortFromStart;
            }

            if (currEffortFromStart > effortTo[currX][currY]) {
                continue;
            }

            // 将 (curX, curY) 的相邻坐标装入队列
            for (int[] neighbor : adj(heights, currX, currY)) {
                int nextX = neighbor[0];
                int nextY = neighbor[1];
                // 计算从 (curX, curY) 达到 (nextX, nextY) 的消耗
                int effortToNextNode = Math.max(
                        effortTo[currX][currY],
                        Math.abs(heights[currX][currY] - heights[nextX][nextY])
                );
                // 更新 dp table
                if (effortTo[nextX][nextY] > effortToNextNode) {
                    effortTo[nextX][nextY] = effortToNextNode;
                    priorityQueue.offer(new State(nextX, nextY, effortToNextNode));
                }
            }
        }
        // 正常情况不会达到这个 return
        return -1;
    }

    // 返回坐标 (x, y) 的上下左右相邻坐标
    private List<int[]> adj(int[][] matrix, int x, int y) {
        // 方向数组，上下左右的坐标偏移量
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = matrix.length, n = matrix[0].length;
        // 存储相邻节点
        ArrayList<int[]> neighbors = new ArrayList<>();
        for (int[] dir : dirs) {
            int n_x = x + dir[0];
            int n_y = y + dir[1];
            if (n_x >= m || n_x < 0 || n_y >= n || n_y < 0) {
                // 索引越界
                continue;
            }
            neighbors.add(new int[]{n_x, n_y});
        }

        return neighbors;
    }
}

class State {
    // 矩阵中的一个位置
    public int x;
    public int y;
    // 从起点 (0, 0) 到当前位置的最小体力消耗（距离）
    int effortFromStart;

    public State(int x, int y, int effortFromStart) {
        this.x = x;
        this.y = y;
        this.effortFromStart = effortFromStart;
    }
}
```

### [1514. 概率最大的路径](https://leetcode-cn.com/problems/path-with-maximum-probability/)

```java
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<double[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }

        // 构造邻接表结构表示图
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double weight = succProb[i];
            // 无向图就是双向图；先把 int 统一转成 double，待会再转回来
            graph[from].add(new double[]{(double) to, weight});
            graph[to].add(new double[]{(double) from, weight});
        }

        return dijkstra(start, end, graph);
    }

    private double dijkstra(int start, int end, List<double[]>[] graph) {
        // 定义：probTo[i] 的值就是节点 start 到达节点 i 的最大概率
        double[] probTo = new double[graph.length];
        // dp table 初始化为一个取不到的最小值
        Arrays.fill(probTo, -1);
        // base case，start 到 start 的概率就是 1
        probTo[start] = 1;

        // 优先级队列，probFromStart 较大的排在前面
        Queue<State> priorityQueue = new PriorityQueue<>((a, b) -> {
            return Double.compare(b.probFromStart, a.probFromStart);
        });

        // 从起点 start 开始进行 BFS
        priorityQueue.offer(new State(start, 1));

        while (!priorityQueue.isEmpty()) {
            State currState = priorityQueue.poll();
            int currNodeId = currState.id;
            double currProbFromStart = currState.probFromStart;

            // 遇到终点提前返回
            if (currNodeId == end) {
                return currProbFromStart;
            }

            if (currProbFromStart < probTo[currNodeId]) {
                // 已经有一条概率更大的路径到达 curNode 节点了
                continue;
            }

            // 将 curNode 的相邻节点装入队列
            for (double[] neighbor : graph[currNodeId]) {
                int nextNodeId = (int) neighbor[0];
                // 看看从 curNode 达到 nextNode 的概率是否会更大
                double probToNextNode = probTo[currNodeId] * neighbor[1];
                if (probToNextNode > probTo[nextNodeId]) {
                    probTo[nextNodeId] = probToNextNode;
                    priorityQueue.offer(new State(nextNodeId, probToNextNode));
                }
            }
        }

        // 如果到达这里，说明从 start 开始无法到达 end，返回 0
        return 0.0;
    }
}

class State {
    // 图节点的 id
    public int id;
    // 从 start 节点到达当前节点的概率
    public double probFromStart;

    public State(int id, double probFromStart) {
        this.id = id;
        this.probFromStart = probFromStart;
    }
}
```