package dataStructure.binaryTree.q96.numTrees;

public class Solution1 {
    public int numTrees(int n) {
        return count(1, n);
    }

    // 计算闭区间 [lo, hi] 组成的 BST 个数
    private int count(int lo, int hi) {
        // base case
        if (lo > hi) {
            return 1;
        }

        int res = 0;
        for (int i = lo; i <= hi; i++) {
            // i 的值作为根节点 root
            int left = count(lo, i - 1);
            int right = count(i + 1, hi);
            // 左右子树的组合数乘积是 BST 的总数
            res += left * right;
        }

        return res;
    }
}
