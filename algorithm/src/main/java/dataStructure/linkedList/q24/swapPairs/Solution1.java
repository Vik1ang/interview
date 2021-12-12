package dataStructure.linkedList.q24.swapPairs;

import dataStructure.linkedList.ListNode;

public class Solution1 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p = dummy;

        while (p.next != null && p.next.next != null) {
            ListNode node1 = p.next;
            ListNode node2 = p.next.next;
            p.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            p = node1;
        }

        return dummy.next;
    }
}
