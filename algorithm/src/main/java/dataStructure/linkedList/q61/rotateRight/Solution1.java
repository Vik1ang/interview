package dataStructure.linkedList.q61.rotateRight;

import dataStructure.linkedList.ListNode;

public class Solution1 {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }

        int n = 1;
        ListNode iter = head;

        while (iter.next != null) {
            iter = iter.next;
            n++;
        }

        int add = n - k % n;
        if (add == n) {
            return head;
        }
        iter.next = head;

        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode res = iter.next;
        iter.next = null;

        return res;
    }
}
