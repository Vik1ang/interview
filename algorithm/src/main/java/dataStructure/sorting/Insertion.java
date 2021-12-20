package dataStructure.sorting;

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
