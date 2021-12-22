package dataStructure.array.binarySearch;

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
