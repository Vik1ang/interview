# Array

- [Array](#array)
  - [Prefix Sum](#prefix-sum)
    - [q303. 区域和检索 - 数组不可变](#q303-区域和检索---数组不可变)
    - [q304. 二维区域和检索 - 矩阵不可变](#q304-二维区域和检索---矩阵不可变)
    - [q560. 和为 K 的子数组](#q560-和为-k-的子数组)
    - [238. 除自身以外数组的乘积](#238-除自身以外数组的乘积)
  - [Slide Window](#slide-window)
    - [76. 最小覆盖子串](#76-最小覆盖子串)
    - [567. 字符串的排列](#567-字符串的排列)
    - [438. 找到字符串中所有字母异位词](#438-找到字符串中所有字母异位词)
    - [3. 无重复字符的最长子串](#3-无重复字符的最长子串)
    - [209. 长度最小的子数组](#209-长度最小的子数组)
  - [Binary Search](#binary-search)
    - [240. 搜索二维矩阵 II](#240-搜索二维矩阵-ii)
    - [153. 寻找旋转排序数组中的最小值](#153-寻找旋转排序数组中的最小值)
    - [154. 寻找旋转排序数组中的最小值 II](#154-寻找旋转排序数组中的最小值-ii)
  - [Double Pointer](#double-pointer)
    - [26. 删除有序数组中的重复项](#26-删除有序数组中的重复项)
    - [80. 删除有序数组中的重复项 II](#80-删除有序数组中的重复项-ii)
    - [27. 移除元素](#27-移除元素)
    - [283. 移动零](#283-移动零)
    - [88. 合并两个有序数组](#88-合并两个有序数组)
    - [15. 三数之和](#15-三数之和)
    - [18. 四数之和](#18-四数之和)
    - [259. 较小的三数之和](#259-较小的三数之和)
    - [16. 最接近的三数之和](#16-最接近的三数之和)
    - [977. 有序数组的平方](#977-有序数组的平方)
  - [Hash](#hash)
    - [1. 两数之和](#1-两数之和)
    - [170. 两数之和 III - 数据结构设计](#170-两数之和-iii---数据结构设计)
  - [Other](#other)
    - [剑指 Offer 03. 数组中重复的数字](#剑指-offer-03-数组中重复的数字)
    - [54. 螺旋矩阵](#54-螺旋矩阵)

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

### [238. 除自身以外数组的乘积](https://leetcode-cn.com/problems/product-of-array-except-self/)

```java
public class Solution1 {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];

        // answer[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
            answer[i] = answer[i] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R *= nums[i];
        }

        return answer;
    }
}
```

## Slide Window

### [76. 最小覆盖子串](https://leetcode-cn.com/problems/minimum-window-substring/)

当 `valid == need.size()` 时, 说明 `s2` 中所有字符已经被覆盖, 已经得到一个可行的覆盖子串, 现在应该开始收缩窗口了, 以便得到 **最小覆盖子串**

```java
public class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;

        // 记录最小覆盖子串的起始索引及长度
        int start = 0;
        int length = Integer.MAX_VALUE;

        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < length) {
                    start = left;
                    length = right - left;
                }

                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return length == Integer.MAX_VALUE ? "" : s.substring(start, start + length);
    }
}
```

### [567. 字符串的排列](https://leetcode-cn.com/problems/permutation-in-string/)

本题移动 left 缩小窗口的时机是窗口大小大于 `s1.length()` 时, 应为排列嘛, 显然长度应该是一样的

```java
HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();

        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;

        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;

            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            while (right - left >= s1.length()) {
                // 在这里判断是否找到了合法的子串
                if (valid == need.size()) {
                    return true;
                }

                char d = s2.charAt(left);
                left++;

                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }

        }

        return false;
```

### [438. 找到字符串中所有字母异位词](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/)

```java
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left =0, right = 0;
        int valid = 0;

        ArrayList<Integer> res = new ArrayList<>();

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // 判断左侧窗口是否要收缩
            while (right - left >= p.length()) {
                // 当窗口符合条件时，把起始索引加入 res
                if (valid == need.size()) {
                    res.add(left);
                }

                char d = s.charAt(left);
                left++;

                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return res;
    }
}
```

### [3. 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)

```java
public class Solution {
    public int lengthOfLongestSubstring(String s) {

        HashMap<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        int res = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            window.put(c, window.getOrDefault(c, 0) + 1);

            // 判断左侧窗口是否要收缩
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;

                if (window.containsKey(d)) {
                    window.put(d, window.get(d) - 1);
                }
            }

            res = Math.max(right - left, res);

        }

        return res;
    }
}
```

### [209. 长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/)

```java
public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (right < nums.length) {
            int num = nums[right];
            right++;

            sum += num;

            if (sum >= target) {
                res = Math.min(res, right - left);
            }

            while (sum >= target) {
                int temp = nums[left];
                left++;

                sum -= temp;
                if (sum >= target) {
                    res = Math.min(res, right - left);
                }
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
```

## Binary Search

```java
public class BinarySearch {
    /**
     * 二分查找闭区间 [L,R]
     * <p>
     * 范围在[L,R]闭区间中，L = 0, R = arr.length - 1
     * 循环条件为 L <= R
     */
    public int binarySearchCloseInterval(int[] arr, int key) {
        int L = 0, R = arr.length - 1; // 在[L,R]范围内寻找key
        int mid = 0;
        while (L <= R) {
            mid = L + (R - L) / 2;
            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] > key) {
                R = mid - 1; // key在[L,mid-1]内
            } else {
                L = mid + 1; // key在[L+1,mid]内
            }
        }
        return -1;
    }

    /**
     * 二分查找 返回第一个等于key的
     * <p>
     * 因为要找到第一个，我们去左边找的时候不仅仅 arr[mid] > key, 等于也要继续找，因为要找到最左边的等于
     * 最后要判断L是否越界(L有可能等于arr.length), 而且最后arr[L]是否要等与找的key
     * 如果arr[L]不等于key，说明没有这个元素
     */
    public int binarySearchGetFirstEqual(int[] arr, int key) {
        int L = 0, R = arr.length - 1; // [L,R]
        int mid = 0;
        while (L <= R) {
            mid = L + (R - L) / 2;
            if (arr[mid] >= key) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
            if (L < arr.length && arr[L] == key) {
                return L;
            }

        }
        return -1;
    }

    /**
     * 二分查找第一个大于key的 >
     */
    public int binarySearchGetFirstGreater(int[] arr, int key) {
        int L = 0, R = arr.length - 1;
        int mid = 0;
        while (L <= R) {
            mid = L + (R - L) / 2;
            if (arr[mid] > key) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return L;
    }

    /**
     * 二分查找 第一个大于等于key >=
     */
    public int binarySearchGetFirstGreaterEqual(int[] arr, int key) {
        int L = 0, R = arr.length - 1;
        int mid = 0;
        while (L <= R) {
            mid = L + (R - L) / 2;
            if (arr[mid] >= key) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return L;
    }

    /**
     * 二分查找最后一个等于key =
     */
    public int binarySearchGetLastEqual(int[] arr, int key) {
        int L = 0, R = arr.length - 1;
        int mid = 0;
        while (L <= R) {
            mid = L + (R - L) / 2;
            if (arr[mid] <= key) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
            if (R >= 0 && arr[R] == key) {
                return R;
            }
        }
        return -1;
    }

    /**
     * 二分查找最后一个小于key的 < key
     */
    public int binarySearchGetLastLess(int[] arr, int key) {
        int L = 0, R = arr.length - 1;
        int mid = 0;
        while (L <= R) {
            mid = L + (R - L) / 2;
            if (arr[mid] < key) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return R;
    }

    /**
     * 二分查找最后一个 <= key
     */
    public int binarySearchGetLastLessEqual(int[] arr, int key) {
        int L = 0, R = arr.length - 1;
        int mid = 0;
        while (L <= R) {
            mid = L + (R - L) / 2;
            if (arr[mid] <= key) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return R;
    }

    /**
     * 二分查找开区间 [L, R)
     * <p>
     * 循环条件为 L < R, 因为 R 本身是一个不可以到达的地方
     */
    public int binarySearchOpenInterval(int[] arr, int key) {
        int L = 0, R = arr.length; // 在[L,R)开区间范围内寻找key
        int mid = 0;
        while (L < R) {
            mid = L + (R - L) / 2;
            if (arr[mid] == key) {
                return mid;
            }
            // 在[L, mid) 中找
            if (arr[mid] > key) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }
        return -1;
    }

}
```

### [240. 搜索二维矩阵 II](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/)

```java
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int index = search(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    private int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
```

### [153. 寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)

我们考虑数组中的**最后一个元素** `x`: 在最小值右侧的元素(不包括最后一个元素本身), 它们的值一定都严格小于`x`; 而在最小值左侧的元素, 它们的值一定都严格大于 `x`; 因此, 我们可以根据这一条性质, 通过二分查找的方法找出最小值

```java
public class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }

        return nums[low];
    }
}
```

### [154. 寻找旋转排序数组中的最小值 II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/)

类似 [153. 寻找旋转排序数组中的最小值](#153-寻找旋转排序数组中的最小值)

```java
public class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else if (nums[pivot] > nums[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return nums[low];
    }
}
```

## Double Pointer

### [26. 删除有序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)

```java
public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }

        return slow + 1;
    }
}
```

### [80. 删除有序数组中的重复项 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/)

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
```

### [27. 移除元素](https://leetcode-cn.com/problems/remove-element/)

```java
public class Solution {
    public int removeElement(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
```

### [283. 移动零](https://leetcode-cn.com/problems/move-zeroes/)

```java
public class Solution {
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;

        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
    }
}
```

### [88. 合并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array/)

**逆向双指针**

```java
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;

        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}
```

### [15. 三数之和](https://leetcode-cn.com/problems/3sum/)

第二重循环枚举到的元素不小于当前第一重循环枚举到的元素

第三重循环枚举到的元素不小于当前第二重循环枚举到的元素

```java
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        // 枚举a
        for (int first = 0; first < n; first++) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];

            // 枚举 b
            for (int second = first + 1; second < n; second++) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    res.add(list);
                }
            }
        }

        return res;
    }
}
```

### [18. 四数之和](https://leetcode-cn.com/problems/4sum/)

类似 [15. 三数之和](#15-三数之和)

```java
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }

        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) {
                continue;
            }

            // 穷举b
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) {
                    continue;
                }
                int left = j + 1, right = n - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return res;
    }
}
```

### [259. 较小的三数之和](https://leetcode-cn.com/problems/3sum-smaller/)

```java
public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target) {
        int sum = 0;
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                sum += right - left;
                left++;
            } else {
                right--;
            }
        }
        return sum;
    }
}
```

### [16. 最接近的三数之和](https://leetcode-cn.com/problems/3sum-closest/)

```java
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        // 枚举 a
        for (int i = 0; i < n; i++) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return sum;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        k0--;
                    }
                    k = k0;
                } else {
                    int j0 = j + 1;
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }

        return best;
    }
}
```

### [977. 有序数组的平方](https://leetcode-cn.com/problems/squares-of-a-sorted-array/)

```java
public class Solution {
    public int[] sortedSquares(int[] nums) {
        int left = 0, right = nums.length - 1;
        int[] result = new int[nums.length];

        int index = result.length - 1;
        while (left <= right) {
            if (Math.pow(nums[left], 2) > Math.pow(nums[right], 2)) {
                result[index--] = (int) Math.pow(nums[left], 2);
                left++;
            } else {
                result[index--] = (int) Math.pow(nums[right], 2);
                right--;
            }
        }
        return result;
    }
}
```

## Hash

### [1. 两数之和](https://leetcode-cn.com/problems/two-sum/)

```java
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, Integer> index = new HashMap<>();
        // 构造一个哈希表: 元素映射到相应的索引
        for (int i = 0; i < n; i++) {
            index.put(nums[i], i);
        }

        for (int i = 0; i < n; i++) {
            int other = target - nums[i];
            // 如果 other 存在且不是 nums[i] 本身
            if (index.containsKey(other) && index.get(other) != i) {
                return new int[]{i, index.get(other)};
            }
        }
        return new int[]{-1, -1};
    }
}
```

### [170. 两数之和 III - 数据结构设计](https://leetcode-cn.com/problems/two-sum-iii-data-structure-design/)

```java
public class TwoSum {
    Map<Integer, Integer> freq = new HashMap<>();

    public TwoSum() {

    }

    public void add(int number) {
        // 记录 number 出现的次数
        freq.put(number, freq.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value) {
        for (Integer key : freq.keySet()) {
            int other = value - key;
            // 情况一
            if (other == key && freq.get(key) > 1) {
                return true;
            }
            // 情况二
            if (other != key && freq.containsKey(other)) {
                return true;
            }
        }
        return false;
    }
}
```

## Other

### [剑指 Offer 03. 数组中重复的数字](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

```java
public class Solution {
    public int findRepeatNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) {
                return nums[i];
            }
            int temp = nums[i];
            nums[i] = nums[temp];
            nums[temp] = temp;
        }
        return -1;
    }
}
```

### [54. 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)

![](https://assets.leetcode-cn.com/solution-static/54/54_fig1.png)

```java
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }

        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
```