package dataStructure.linkedList.q25.reverseKGroup;

import dataStructure.linkedList.ListNode;

public class Solution1 {
    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null) {
            return head;
        }

        ListNode left, right;
        left = right = head;

        for (int i = 0; i < k; i++) {
            // 不足 k 个, 不需要反转，base case
            if (right == null) {
                return head;
            }
            right = right.next;
        }

        // 反转前 k 个元素
        ListNode newHead = reverse(left, right);

        // 递归反转后续链表并连接起来
        left.next = reverseKGroup(right, k);

        return newHead;
    }

    private ListNode reverse(ListNode left, ListNode right) {
        ListNode prev = null, curr = left, next = left;

        while (curr != right) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
