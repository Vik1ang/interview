package dataStructure.sorting;

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
