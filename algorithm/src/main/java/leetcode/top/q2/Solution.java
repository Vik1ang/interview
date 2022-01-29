package leetcode.top.q2;

import dataStructure.linkedList.ListNode;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        int carry = 0;

        while (p1 != null || p2 != null || carry > 0) {
            int sum = carry;
            if (p1 != null) {
                sum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                sum += p2.val;
                p2 = p2.next;
            }

            carry = sum / 10;

            p.next = new ListNode(sum % 10);
            p = p.next;
        }

        return dummy.next;
    }
}
