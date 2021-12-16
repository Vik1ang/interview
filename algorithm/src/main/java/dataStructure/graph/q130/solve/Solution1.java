package dataStructure.graph.q130.solve;

public class Solution1 {
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
