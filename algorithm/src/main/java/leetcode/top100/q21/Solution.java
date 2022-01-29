package leetcode.top100.q21;

import dataStructure.linkedList.ListNode;

public class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        ListNode p1 = list1, p2 = list2;

        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }

        if (p1 != null) {
            p.next = p1;
            p1 = p1.next;
        }

        if (p2 != null) {
            p.next = p2;
            p2 = p2.next;
        }

        return dummy.next;
    }
}
