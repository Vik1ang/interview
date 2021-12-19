package dataStructure.backtracking.q51.solveNQueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {
    public List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
        ArrayList<char[]> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            board.add(chars);
        }

        backtrack(board, 0);

        return res;
    }

    // 路径：board 中小于 row 的那些行都已经成功放置了皇后
    // 选择列表：第 row 行的所有列都是放置皇后的选择
    // 结束条件：row 超过 board 的最后一行
    private void backtrack(ArrayList<char[]> board, int row) {
        // 触发结束条件
        if (row == board.size()) {
            List<String> temp = new ArrayList<>();
            for (char[] chars : board) {
                temp.add(new String(chars));
            }
            res.add(temp);
            return;
        }

        int n = board.get(0).length;
        for (int col = 0; col < n; col++) {
            // 排除不合法选择
            if (!isValid(board, row, col)) {
                continue;
            }

            // 做选择
            board.get(row)[col] = 'Q';
            // 进入下一行决策
            backtrack(board, row + 1);
            // 撤销选择
            board.get(row)[col] = '.';
        }
    }

    private boolean isValid(ArrayList<char[]> board, int row, int col) {
        int n = board.size();
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < n; i++) {
            if (board.get(i)[col] == 'Q') {
                return false;
            }
        }

        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board.get(i)[j] == 'Q') {
                return false;
            }
        }

        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i)[j] == 'Q') {
                return false;
            }
        }

        return true;
    }

}

