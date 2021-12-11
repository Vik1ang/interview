package dataStructure.linkedList.jianzhiOffer.q06.reversePrint;

import dataStructure.linkedList.ListNode;

import java.util.Deque;
import java.util.LinkedList;

public class Solution2 {
    public int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new LinkedList<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        int[] res = new int[stack.size()];

        int i = 0;
        while (!stack.isEmpty()) {
            res[i++] = stack.pop();
        }
        return res;
    }
}
