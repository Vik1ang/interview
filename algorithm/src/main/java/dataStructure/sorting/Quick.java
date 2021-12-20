package dataStructure.sorting;

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
