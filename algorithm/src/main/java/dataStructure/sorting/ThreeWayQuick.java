package dataStructure.sorting;

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
