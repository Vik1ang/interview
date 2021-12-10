# 链表

[TOC]

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

### [q876. 链表的中间结点](https://leetcode-cn.com/problems/middle-of-the-linked-list/)

一次遍历就得到中间节点, 使用**快慢指针**的技巧:

我们让两个指针 `slow` 和 `fast` 分别指向链表头结点 `head`

**每当慢指针 `slow` 前进一步, 快指针 `fast` 就前进两步, 这样, 当 `fast` 走到链表末尾时, `slow` 就指向了链表中点**

需要注意的是, **如果链表长度为偶数, 也就是说中点有两个的时候, 我们这个解法返回的节点是靠后的那个节点**

```java
public class Solution {
    public ListNode middleNode(ListNode head) {
        // 快慢指针初始化指向 head
        ListNode slow = head, fast = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            // 慢指针走一步, 快指针走两步
            slow = slow.next;
            fast = fast.next.next;
        }
        // 慢指针指向中点
        return slow;
    }
}
```

### [q141. 判断链表是否包含环](https://leetcode-cn.com/problems/linked-list-cycle/)

每当慢指针 `slow` 前进一步, 快指针 `fast` 就前进两步

如果 `fast` 最终遇到空指针, 说明链表中没有环; 如果 `fast` 最终和 `slow` 相遇, 那肯定是 fast 超过了 slow 一圈, 说明链表中含有环

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        // 快慢指针初始化指向 head
        ListNode fast = head, slow = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            // 慢指针走一步, 快指针走两步
            slow = slow.next;
            fast = fast.next.next;
            // 快慢指针相遇, 说明含有环
            if (slow == fast) {
                return true;
            }
        }
        // 不包含环
        return false;
    }
}
```

### [q142. 链表环的起点](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

**当快慢指针相遇时, 让其中任一个指针指向头节点, 然后让它俩以相同速度前进, 再次相遇时所在的节点位置就是环开始的位置**

1. 我们假设快慢指针相遇时, 慢指针 `slow` 走了 `k` 步, 那么快指针 `fast` 一定走了 `2k` 步:

![](https://labuladong.gitee.io/algo/images/%E5%8F%8C%E6%8C%87%E9%92%88/3.jpeg)

`fast` 一定比 `slow` 多走了 `k` 步, 这多走的 `k` 步其实就是 `fast` 指针在环里转圈圈, 所以 `k` 的值就是环长度的**整数倍**

假设相遇点距环的起点的距离为 `m`, 那么结合上图的 `slow` 指针, 环的起点距头结点 `head` 的距离为 `k - m`, 也就是说如果从 `head` 前进 `k - m` 步就能到达环起点

如果从相遇点继续前进 `k - m` 步, 也恰好到达环起点; 因为结合上图的 `fast` 指针, 从相遇点开始走 `k` 步可以转回到相遇点, 那走 `k - m` 步肯定就走到环起点

![](https://labuladong.gitee.io/algo/images/%E5%8F%8C%E6%8C%87%E9%92%88/2.jpeg)

所以, 只要我们把快慢指针中的任一个重新指向 `head`, 后两个指针同速前进, `k - m` 步后一定会相遇, 相遇之处就是环的起点了

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            // fast 遇到空指针说明没有环
            return null;
        }

        // 重新指向头结点
        slow = head;
        // 快慢指针同步前进，相交点就是环起点
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}
```