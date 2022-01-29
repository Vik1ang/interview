package leetcode.top100.q169;

public class Solution {
    public int majorityElement(int[] nums) {
        int cardNum = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (cardNum == nums[i]) {
                count++;
            } else if (--count == 0) {
                cardNum = nums[i];
                count = 1;
            }
        }
        return cardNum;
    }
}
