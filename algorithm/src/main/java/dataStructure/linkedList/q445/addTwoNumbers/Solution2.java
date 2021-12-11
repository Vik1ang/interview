package dataStructure.linkedList.q445.addTwoNumbers;

import dataStructure.linkedList.ListNode;

import java.util.Deque;
import java.util.LinkedList;

public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<>();
        Deque<Integer> stack2 = new LinkedList<>();

        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode ans = null;

        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int num1 = stack1.isEmpty() ? 0 : stack1.pop();
            int num2 = stack2.isEmpty() ? 0 : stack2.pop();
            int num = num1 + num2 + carry;
            ListNode curr = new ListNode(num % 10);
            curr.next = ans;
            ans = curr;
            carry = num / 10;
        }

        return ans;
    }
}
