# Array

- [Array](#array)
  - [Prefix Sum](#prefix-sum)
    - [q303. 区域和检索 - 数组不可变](#q303-区域和检索---数组不可变)
    - [q304. 二维区域和检索 - 矩阵不可变](#q304-二维区域和检索---矩阵不可变)
    - [q560. 和为 K 的子数组](#q560-和为-k-的子数组)

## Prefix Sum

### [q303. 区域和检索 - 数组不可变](https://leetcode-cn.com/problems/range-sum-query-immutable/)

```java
public class NumArray {
    // 前缀和数组
    private int[] preSum;

    public NumArray(int[] nums) {
        // preSum[0] = 0，便于计算累加和
        preSum = new int[nums.length + 1];
        // 计算 nums 的累加和
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
    }

    /* 查询闭区间 [left, right] 的累加和 */
    public int sumRange(int left, int right) {
        return preSum[right + 1] - preSum[left];
    }
}
```

### [q304. 二维区域和检索 - 矩阵不可变](https://leetcode-cn.com/problems/range-sum-query-2d-immutable/)

![](https://labuladong.gitee.io/algo/images/%E5%89%8D%E7%BC%80%E5%92%8C/5.png)

我想计算红色的这个子矩阵的元素之和, 可以用绿色矩阵减去蓝色矩阵减去橙色矩阵最后加上粉色矩阵, 而绿蓝橙粉这四个矩阵有一个共同的特点, 就是左上角就是 `(0, 0)` 原点

```java
public class NumMatrix {
    // preSum[i][j] 记录矩阵 [0, 0, i, j] 的元素和
    private int[][] preSum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        if (m == 0 || n == 0) return;
        // 构造前缀和矩阵
        preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 计算每个矩阵 [0, 0, i, j] 的元素和
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + matrix[i - 1][j - 1] - preSum[i - 1][j - 1];
            }
        }
    }

    // 计算子矩阵 [x1, y1, x2, y2] 的元素和
    public int sumRegion(int x1, int y1, int x2, int y2) {
        // 目标矩阵之和由四个相邻矩阵运算获得
        return preSum[x2 + 1][y2 + 1] - preSum[x1][y2 + 1] - preSum[x2 + 1][y1] + preSum[x1][y1];
    }
}
```

### [q560. 和为 K 的子数组](https://leetcode-cn.com/problems/subarray-sum-equals-k/)

```java
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // 构造前缀和
        int[] preSum = new int[n + 1];

        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = nums[i - 1] + preSum[i - 1];
        }

        int res = 0;
        // 穷举所有子数组
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 子数组 nums[j..i-1] 的元素和
                if (preSum[i + 1] - preSum[j] == k) {
                    res++;
                }
            }
        }

        return res;
    }
}
```

优化

```java
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // map：前缀和 -> 该前缀和出现的次数
        HashMap<Integer, Integer> preSum = new HashMap<>();

        // base case
        preSum.put(0, 1);

        int res = 0, sum0_i = 0;

        for (int i = 0; i < n; i++) {
            sum0_i += nums[i];
            // 这是我们想找的前缀和 nums[0..j]
            int sum0_j = sum0_i - k;
            // 如果前面有这个前缀和，则直接更新答案
            if (preSum.containsKey(sum0_j)) {
                res += preSum.get(sum0_j);
            }

            // 把前缀和 nums[0..i] 加入并记录出现次数
            preSum.put(sum0_i, preSum.getOrDefault(sum0_i, 0) + 1);
        }

        return res;
    }
}
```