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

### 单链表的倒数第 k 个节点

**只遍历一次链表**, 就算出倒数第 `k` 个节点

1. 首先, 我们先让一个指针 `p1` 指向链表的头节点 `head`, 然后走 `k` 步:

![](https://labuladong.gitee.io/algo/images/%e9%93%be%e8%a1%a8%e6%8a%80%e5%b7%a7/1.jpeg)

现在的 p1, 只要再走 n - k 步, 就能走到链表末尾的空指针

趁这个时候, 再用一个指针 `p2` 指向链表头节点 head:

![](https://labuladong.gitee.io/algo/images/%e9%93%be%e8%a1%a8%e6%8a%80%e5%b7%a7/2.jpeg)

接下来就很显然了, 让 `p1` 和 `p2` 同时向前走, `p1` 走到链表末尾的空指针时走了 `n - k` 步, `p2` 也走了 `n - k` 步，也就是链表的倒数第 `k` 个节点:

![](https://labuladong.gitee.io/algo/images/%e9%93%be%e8%a1%a8%e6%8a%80%e5%b7%a7/3.jpeg)

```java
public class Solution {
    // 返回链表的倒数第 k 个节点
    ListNode findFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        // p1 先走 k 步
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        // p1 和 p2 同时走 n - k 步
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        // p2 现在指向第 n - k 个节点
        return p2;
    }
}
```

### [q19. 删除链表的倒数第 N 个结点](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/)


```java
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 删除倒数第 n 个，要先找倒数第 n + 1 个节点
        ListNode x = findFromEnd(dummy, n + 1);

        // 删掉倒数第 n 个节点
        x.next = x.next.next;
        return dummy.next;
    }

    private ListNode findFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        // p1 先走 k 步
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        // p1 和 p2 同时走 n - k 步
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        // p2 现在指向第 n - k 个节点
        return p2;
    }
}
```

要删除倒数第 `n` 个节点, 就得获得倒数第 `n + 1` 个节点的引用, 可以用我们实现的 `findFromEnd` 来操作

不过注意我们又使用了虚拟头结点的技巧, 也是为了防止出现空指针的情况, 比如说链表总共有 5 个节点, 题目就让你删除倒数第 5 个节点, 也就是第一个节点, 那按照算法逻辑, 应该首先找到倒数第 6 个节点
但第一个节点前面已经没有节点了, 这就会出错

