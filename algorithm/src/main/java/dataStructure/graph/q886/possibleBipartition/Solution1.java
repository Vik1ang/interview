package dataStructure.graph.q886.possibleBipartition;

import java.util.LinkedList;
import java.util.List;

public class Solution1 {
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
