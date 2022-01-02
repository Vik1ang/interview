package dataStructure.array.doublePointer.q977.sortedSquares;

public class Solution1 {
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
