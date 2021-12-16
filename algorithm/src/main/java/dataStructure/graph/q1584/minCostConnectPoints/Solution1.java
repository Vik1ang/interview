package dataStructure.graph.q1584.minCostConnectPoints;

import dataStructure.graph.UnionFind;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
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
