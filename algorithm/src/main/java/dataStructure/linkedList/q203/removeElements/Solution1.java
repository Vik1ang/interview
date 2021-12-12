package dataStructure.linkedList.q203.removeElements;

import dataStructure.linkedList.ListNode;

public class Solution1 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;

        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }

        return dummy.next;
    }
}
