package dataStructure.graph.q1631.minimumEffortPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution1 {
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
