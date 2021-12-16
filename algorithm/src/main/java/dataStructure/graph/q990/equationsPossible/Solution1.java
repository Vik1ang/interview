package dataStructure.graph.q990.equationsPossible;

import dataStructure.graph.q130.solve.UnionFind;

public class Solution1 {
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
