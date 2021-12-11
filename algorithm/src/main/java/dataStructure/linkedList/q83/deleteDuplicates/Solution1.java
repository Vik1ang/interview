package dataStructure.linkedList.q83.deleteDuplicates;

import dataStructure.linkedList.ListNode;

public class Solution1 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head, fast = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }

        // 断开与后面重复元素的连接
        slow.next = null;

        return head;
    }
}
