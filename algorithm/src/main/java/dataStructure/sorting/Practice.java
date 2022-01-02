package dataStructure.sorting;

public class Practice {
    public void quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        quickProcess(nums, 0, nums.length - 1);
    }

    private void quickProcess(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = partition(nums, left, right);
        quickProcess(nums, left, pivot - 1);
        quickProcess(nums, pivot + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int key = nums[left];
        int pivot = left;

        for (int i = left + 1; i <= right ; i++) {
            if (nums[i] < key) {
                swap(nums, i, ++pivot);
            }
        }

        swap(nums, pivot, left);

        return pivot;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void MergeSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        mergeProcess(nums, 0, nums.length - 1);
    }

    private void mergeProcess(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeProcess(nums, left, mid);
        mergeProcess(nums, mid + 1, right);

        if (nums[mid] > nums[mid + 1]) {
            merge(nums, left, mid, right);
        }
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] help = new int[right + 1];
        int k = 0;
        int p1 = left, p2 = mid + 1;

        while (p1 <= mid && p2 <= right) {
            help[k++] = nums[p1] <= nums[p2] ? nums[p1++] : nums[p2++];
        }

        while (p1 <= mid) {
            help[k++] = nums[p1++];
        }
        while (p2 <= right) {
            help[k++] = nums[p2++];
        }

        for (int i = 0; i < k; i++) {
            nums[i + left] = help[i];
        }
    }


}
