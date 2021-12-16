package dataStructure.graph.q1135.minimumCost;

import dataStructure.graph.UnionFind;

import java.util.Arrays;

public class Solution1 {
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
}
