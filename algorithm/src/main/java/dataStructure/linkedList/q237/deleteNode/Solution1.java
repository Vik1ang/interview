package dataStructure.linkedList.q237.deleteNode;

import dataStructure.linkedList.ListNode;

public class Solution1 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
