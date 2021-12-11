package dataStructure.linkedList.q445.addTwoNumbers;

import dataStructure.linkedList.ListNode;

public class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode p1 = reverseList(l1);
        ListNode p2 = reverseList(l2);
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        int carry = 0;
        while (p1 != null || p2 != null) {
            int num1 = p1 != null ? p1.val : 0;
            int num2 = p2 != null ? p2.val : 0;
            int sum = num1 + num2 + carry;

            p.next = new ListNode(sum % 10);
            carry = sum / 10;

            if (p1 != null) {
                p1 = p1.next;
            }

            if (p2 != null) {
                p2 = p2.next;
            }
            p = p.next;
        }

        if (carry > 0) {
            p.next = new ListNode(carry);
            p = p.next;
        }

        return reverseList(dummy.next);
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
