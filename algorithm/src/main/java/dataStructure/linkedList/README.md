# 链表

### [q21. 合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/submissions/)

![](https://labuladong.gitee.io/algo/images/%e9%93%be%e8%a1%a8%e6%8a%80%e5%b7%a7/1.gif)

`while` 循环每次比较 `p1` 和 `p2` 的大小，把较小的节点接到结果链表上:

代码中还用到一个链表的算法题中是很常见的**虚拟头结点**技巧, 也就是 **dummy** 节点

有了 **dummy** 节点这个占位符, 可以避免处理空指针的情况, 降低代码的复杂性

```java
public class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 虚拟头结点
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode p1 = list1, p2 = list2;

        while (p1 != null && p2 != null) {
            // 比较 p1 和 p2 两个指针
            // 将值较小的的节点接到 p 指针
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }

        if (p1 != null) {
            p.next = p1;
        }

        if (p2 != null) {
            p.next = p2;
        }

        return dummy.next;
    }
}
```

### [q23. 合并 k 个有序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

逻辑类似合并两个有序链表

难点在于, 如何快速得到 `k` 个节点中的最小节点, 接到结果链表上

**优先队列**, 把链表节点放入一个最小堆, 就可以每次获得 `k` 个节点中的最小节点

```java
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        // 虚拟头结点
        ListNode dummy = new ListNode(-1), p = dummy;

        // 优先级队列, 最小堆
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        // 将 k 个链表的头结点加入最小堆
        for (ListNode head : lists) {
            if (head != null) {
                priorityQueue.add(head);
            }
        }

        while (!priorityQueue.isEmpty()) {
            // 获取最小节点, 接到结果链表中
            ListNode node = priorityQueue.poll();
            p.next = node;
            if (node.next != null) {
                priorityQueue.add(node.next);
            }
            p = p.next;
        }

        return dummy.next;
    }
}
```