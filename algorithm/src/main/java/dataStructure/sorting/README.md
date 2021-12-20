# Sorting

- [Sorting](#sorting)
    - [Selection Sort 选择排序](#selection-sort-选择排序)
    - [Bubble Sort 冒泡排序](#bubble-sort-冒泡排序)
    - [Insertion Sort 插入排序](#insertion-sort-插入排序)
    - [希尔排序](#希尔排序)
    - [归并排序](#归并排序)
    - [快速排序](#快速排序)
    - [三向切分快速排序](#三向切分快速排序)


| 算法 | 稳定性 | 时间复杂度 | 空间复杂度 | 备注 |
| :---: | :---: |:---: | :---: | :---: |
| 选择排序 | × | N<sup>2</sup> | 1 | |
| 冒泡排序 | √ |  N<sup>2</sup> | 1 | |
| 插入排序 | √ |  N \~ N<sup>2</sup> | 1 | 时间复杂度和初始顺序有关 |
| 希尔排序 | ×  |  N 的若干倍乘于递增序列的长度 | 1 | 改进版插入排序 |
| 快速排序 | ×  | NlogN | logN | |
| 三向切分快速排序 | ×  |  N \~ NlogN | logN | 适用于有大量重复主键|
| 归并排序 | √ |  NlogN | N | |
| 堆排序 | ×  |  NlogN | 1 | 无法利用局部性原理|

### Selection Sort 选择排序

![](https://camo.githubusercontent.com/3077738d14d2fc08943fa953616639a1e7828f37c5928299f4af1ebc89a66881/68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f62633662653264302d656435652d346465662d383965352d3361646139616661383131612e676966)

```java
public class Selection {
    public void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                minIndex = nums[j] < nums[minIndex] ? j : minIndex;
            }
            swap(nums, i, minIndex);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### Bubble Sort 冒泡排序

![](https://camo.githubusercontent.com/1672cc63dfbfa9f3173fb2b94a5042d64076afd01158fce906b6b70fc21232c3/68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f30663864313738622d353264382d343931622d396466642d3431653035613935323537382e676966)

```java
public class Bubble {
    public void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### Insertion Sort 插入排序

![](https://camo.githubusercontent.com/9dc4032a0ee029d5e0549dd9129bead2f02b99db6b3c9d06ae44ec69a04697b1/68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f33353235336661342d663630612d346533622d616165632d3866633833356161626461632e676966)

```java
public class Insertion {
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 从已经排序的序列最右边的开始比较, 找到比其小的数
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                swap(nums, j, j - 1);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### 希尔排序

### 归并排序

![](https://www.runoob.com/wp-content/uploads/2019/03/mergeSort.gif)

```java
public class Merge {
    public void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        mergeProcess(nums, 0, nums.length - 1);
    }

    private void mergeProcess(int[] nums, int L, int R) {
        // 递归条件判断
        if (L >= R) {
            return;
        }

        int mid = L + ((R - L) >> 1);
        mergeProcess(nums, L, mid);
        mergeProcess(nums, mid + 1, R);

        // 这是一个优化，因为arr[L,mid]和arr[mid+1,R]已经有序，所以如果满足这个条件，就不要排序，防止一开始数组有序
        if (nums[mid] > nums[mid + 1]) {
            merge(nums, L, mid, R);
        }

    }

    private void merge(int[] nums, int L, int mid, int R) {
        int[] help = new int[R + 1];
        int k = 0;
        int p1 = L, p2 = mid + 1;

        while (p1 <= mid && p2 <= R) {
            // 左右边两边相等的话，就先拷贝左边的实现了稳定性
            help[k++] = nums[p1] <= nums[p2] ? nums[p1++] : nums[p2++];
        }

        // 左边剩余部分
        while (p1 <= mid) {
            help[k++] = nums[p1++];
        }

        // 右边剩余部分
        while (p2 <= R) {
            help[k++] = nums[p2++];
        }

        // 拷贝回原数组
        for (int i = 0; i < k; i++) {
            nums[i + L] = help[i];
        }
    }
}
```

### 快速排序

![](https://www.runoob.com/wp-content/uploads/2019/03/quickSort.gif)

```java
public class Quick {
    public void sort(int[] nums) {
        // 在[L,R]之间, 选取arr[L]作为划分点key
        // 然后从[L,R]之间, 如果当前arr[i] < key, 就放到左边部分swap(arr, i, ++pivot), 否则就不动
        // 最后将数组划分成了 arr[L..p-1] < arr[p], arr[p+1..R] > arr[p], 并返回p
        if (nums == null || nums.length < 2) {
            return;
        }

        quickProcess(nums, 0, nums.length - 1);
    }

    private void quickProcess(int[] nums, int L, int R) {
        if (L >= R) {
            return;
        }

        int pivot = partition(nums, L, R);
        quickProcess(nums, L, pivot - 1);
        quickProcess(nums, pivot + 1, R);

    }

    /*
     对 arr[L..R]部分进行partition操作
     返回p, 使得 arr[L..p-1] < arr[p], arr[p+1..R] > arr[p]
     */
    private int partition(int[] nums, int L, int R) {
        // 直接选取 arr[L]作为pivot(中心点)
        int key = nums[L];
        int pivot = L;
        for (int i = L + 1; i <= R; i++) {
            if (nums[i] < key) {
                swap(nums, i, ++pivot);
            }
        }

        // 将arr[L]放到pivot位置(中间) -> 完全了按照arr[L]划分数组的目的
        swap(nums, pivot, L);

        return pivot;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 1, 6, 5, 2, 8, 4, 7};
        new Quick().sort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
```

### 三向切分快速排序

![](https://upload-images.jianshu.io/upload_images/31729-d554cd3d1cfe670d.png?imageMogr2/auto-orient/strip|imageView2/2/w/385/format/webp)

```java
public class ThreeWayQuick {
    // 更好的解决重复元素多的问题
    // 先从序列中选取一个数作为基数(key)
    // 分区过程，将<key放到左边，>key的放在右边，=key放到中间
    // 再对左右区间重复第二步，直到各区间只有一个数
    // 返回的p数组中p[0]代表的是等于区域的左边界，p[1]代表的是等于区域的右边界
    public void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickProcess(arr, 0, arr.length - 1);
    }

    private void quickProcess(int[] arr, int L, int R) {
        if (L >= R)
            return;
        /**随机化的排序 期望为Ologn从前面的数中随机选出一个数和最后一个数交换 不至于极端的情况使得两边划分很不对称*/
        swap(arr, R, L + (int) (Math.random() * (R - L + 1))); //例子3~6 --> [0~1)*3 --> 0~2
        int[] p = partition4(arr, L, R); // 分别用partition、partition2、partition3测试都可以
        quickProcess(arr, L, p[0] - 1);
        quickProcess(arr, p[1] + 1, R);
    }

    /**
     * 划分函数,这里使用的是arr[R]来划分, 左边的都比arr[R]小，右边都比arr[R]大
     * 返回的数组是中间相等的两个下标
     */
    private int[] partition(int[] arr, int L, int R) {
        int cur = L, less = L - 1, more = R;
        int key = arr[R];
        while (cur < more) {
            if (arr[cur] < key)
                swap(arr, ++less, cur++);
            else if (arr[cur] > key)
                swap(arr, --more, cur);
            else
                cur++;
        }
        swap(arr, more, R);  //把最后那个数放到中间
        return new int[]{less + 1, more};  //当然如果没有相等的部分  那less+1 = more
    }

    /**
     * 上面的简写方式
     **/
    private int[] partition2(int[] arr, int L, int R) {
        int less = L - 1, more = R;  //把最后这个数当作标准 也可以使用第一个
        while (L < more) {
            if (arr[L] < arr[R])
                swap(arr, ++less, L++);
            else if (arr[L] > arr[R])
                swap(arr, --more, L);
            else
                L++;
        }
        swap(arr, more, R);  //把最后那个数放到中间
        return new int[]{less + 1, more};  //为什么不是 more-1,因为上面又交换了一个, 当然如果没有相等的部分  那less+1 = more
    }

    /**
     * 正方向：按照 arr[L]来划分
     **/
    private int[] partition3(int[] arr, int L, int R) {
        int key = arr[L], cur = L + 1;
        int less = L, more = R + 1; // more在外面(R+1)，等下循环cur < more
        while (cur < more) {
            if (arr[cur] < key)
                swap(arr, ++less, cur++);
            else if (arr[cur] > key)
                swap(arr, --more, cur);
            else cur++;
        }
        swap(arr, L, less);
        return new int[]{less, more - 1};
    }

    /**
     * 对比partition3的不同
     **/
    private int[] partition4(int[] arr, int L, int R) {
        int key = arr[L], cur = L + 1;
        int less = L, more = R; // more = R，等下循环cur <= more
        while (cur <= more) { // not cur < more
            if (arr[cur] < key)
                swap(arr, ++less, cur++);
            else if (arr[cur] > key)
                swap(arr, more--, cur); // 对比上面,不是--more,这些就是边界问题
            else cur++;
        }
        swap(arr, L, less);
        return new int[]{less, more}; //同样返回的也不同
    }


    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```