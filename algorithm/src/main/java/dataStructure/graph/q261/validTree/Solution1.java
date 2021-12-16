package dataStructure.graph.q261.validTree;

import dataStructure.graph.UnionFind;

public class Solution1 {
    public boolean validTree(int n, int[][] edges) {
        // 初始化 0...n-1 共 n 个节点
        UnionFind unionFind = new UnionFind(n);

        // 遍历所有边，将组成边的两个节点进行连接
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // 若两个节点已经在同一连通分量中，会产生环
            if (unionFind.connected(u, v)) {
                return false;
            }
            // 这条边不会产生环，可以是树的一部分
            unionFind.union(u, v);
        }
        // 要保证最后只形成了一棵树，即只有一个连通分量
        return unionFind.count() == 1;
    }
}
