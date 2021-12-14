package dataStructure.linkedList.q143.reorderList;

import dataStructure.linkedList.ListNode;

import java.util.ArrayList;

public class Solution1 {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ArrayList<ListNode> listNodes = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            listNodes.add(p);
            p = p.next;
        }
        int n = listNodes.size() - 1;
        int i = 0;
        while (i < n) {
            listNodes.get(i).next = listNodes.get(n);
            i++;
            if (i == n) {
                break;
            }
            listNodes.get(n).next = listNodes.get(i);
            n--;
        }
        listNodes.get(i).next = null;
    }
}
