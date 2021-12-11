package dataStructure.linkedList.q2.addTwoNumbers;

import dataStructure.linkedList.ListNode;

public class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2;

        int carry = 0;
        int sum = 0;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (p1 != null && p2 != null) {
            sum = p1.val + p2.val;
            if (carry != 0) {
                sum += carry;
                carry = 0;
            }

            if (sum >= 10) {
                sum %= 10;
                carry = 1;
            }

            p.next = new ListNode(sum);
            p = p.next;
            p1 = p1.next;
            p2 = p2.next;
        }

        while (p1 != null) {
            sum = p1.val;
            if (carry != 0) {
                sum += carry;
                carry = 0;
            }

            if (sum >= 10) {
                sum %= 10;
                carry = 1;
            }

            p.next = new ListNode(sum);
            p = p.next;
            p1 = p1.next;
        }

        while (p2 != null) {
            sum = p2.val;
            if (carry != 0) {
                sum += carry;
                carry = 0;
            }

            if (sum >= 10) {
                sum %= 10;
                carry = 1;
            }

            p.next = new ListNode(sum);
            p = p.next;
            p2 = p2.next;
        }

        if (carry != 0) {
            p.next = new ListNode(1);
        }

        return dummy.next;
    }

}
