package dataStructure.linkedList.jianzhiOffer.q06.reversePrint;

import dataStructure.linkedList.ListNode;

public class Solution1 {
    public int[] reversePrint(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        int count = 0;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }

        int[] res = new int[count];
        int i = 0;
        while (prev != null) {
            res[i++] = prev.val;
            prev = prev.next;
        }

        return res;
    }
}
