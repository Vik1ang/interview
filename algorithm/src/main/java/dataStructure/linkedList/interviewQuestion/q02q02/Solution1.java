package dataStructure.linkedList.interviewQuestion.q02q02;

import dataStructure.linkedList.ListNode;

public class Solution1 {
    public int kthToLast(ListNode head, int k) {
        ListNode p1 = head;
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2.val;
    }
}
