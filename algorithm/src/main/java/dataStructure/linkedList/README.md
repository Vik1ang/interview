# 链表

- [链表](#链表)
    - [q21. 合并两个有序链表](#q21-合并两个有序链表)
    - [q23. 合并 k 个有序链表](#q23-合并-k-个有序链表)
    - [单链表的倒数第 k 个节点](#单链表的倒数第-k-个节点)
    - [q19. 删除链表的倒数第 N 个结点](#q19-删除链表的倒数第-n-个结点)
    - [q876. 链表的中间结点](#q876-链表的中间结点)
    - [q141. 判断链表是否包含环](#q141-判断链表是否包含环)
    - [q142. 链表环的起点](#q142-链表环的起点)
    - [q160. 两个链表是否相交](#q160-两个链表是否相交)
    - [q206. 反转链表](#q206-反转链表)
    - [反转链表前 N 个节点](#反转链表前-n-个节点)
    - [q92. 反转链表 II](#q92-反转链表-ii)
    - [q2. 两数相加](#q2-两数相加)
    - [q445. 两数相加 II](#q445-两数相加-ii)
    - [剑指 Offer 06. 从尾到头打印链表](#剑指-offer-06-从尾到头打印链表)
    - [q237. 删除链表中的节点](#q237-删除链表中的节点)
    - [q83. 删除排序链表中的重复元素](#q83-删除排序链表中的重复元素)
    - [q82. 删除排序链表中的重复元素 II](#q82-删除排序链表中的重复元素-ii)
    - [q203. 移除链表元素](#q203-移除链表元素)
    - [面试题 02.02. 返回倒数第 k 个节点](#面试题-0202-返回倒数第-k-个节点)
    - [q24. 两两交换链表中的节点](#q24-两两交换链表中的节点)
    - [q147. 对链表进行插入排序](#q147-对链表进行插入排序)
    - [q148. 排序链表](#q148-排序链表)
    - [q143. 重排链表](#q143-重排链表)
    - [61. 旋转链表](#61-旋转链表)
    - [q25. K 个一组翻转链表](#q25-k-个一组翻转链表)
    - [q234. 回文链表](#q234-回文链表)

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

### [q160. 两个链表是否相交](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)

![](https://labuladong.gitee.io/algo/images/%e9%93%be%e8%a1%a8%e6%8a%80%e5%b7%a7/5.jpeg)

**解决这个问题的关键是, 通过某些方式, 让 `p1` 和 `p2` 能够同时到达相交节点 `c1`**

我们可以让 `p1` 遍历完链表 `A` 之后开始遍历链表 `B`, 让 `p2` 遍历完链表 `B` 之后开始遍历链表 `A`, 这样相当于**逻辑上**两条链表接在了一起

如果这样进行拼接, 就可以让 `p1` 和 `p2` 同时进入公共部分, 也就是同时到达相交节点 `c1`:

![](https://labuladong.gitee.io/algo/images/%e9%93%be%e8%a1%a8%e6%8a%80%e5%b7%a7/6.jpeg)

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // p1 指向 A 链表头结点, p2 指向 B 链表头结点
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            // p1 走一步, 如果走到 A 链表末尾, 转到 B 链表
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
            // p2 走一步, 如果走到 B 链表末尾, 转到 A 链表
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }
        return p1;
    }
}
```

### [q206. 反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)

在遍历链表时，将当前节点的 `next` 指针改为指向前一个节点; 由于节点没有引用其前一个节点, 因此必须事先存储其前一个节点

在更改引用之前, 还需要存储后一个节点, 最后返回新的头引用

```java
public class Solution {
    public ListNode reverseList(ListNode head) {
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
```

**递归反转链表**

`reverse` 函数定义: 输入一个节点 `head`, 将 **以 `head` 为起点** 的链表反转, 并返回反转之后的头结点

`ListNode last = reverse(head.next);`

![](https://labuladong.gitee.io/algo/images/%E5%8F%8D%E8%BD%AC%E9%93%BE%E8%A1%A8/2.jpg)
![](https://labuladong.gitee.io/algo/images/%E5%8F%8D%E8%BD%AC%E9%93%BE%E8%A1%A8/3.jpg)
![](https://labuladong.gitee.io/algo/images/%E5%8F%8D%E8%BD%AC%E9%93%BE%E8%A1%A8/4.jpg)

```java
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}
```

### 反转链表前 N 个节点

```java
// 将链表的前 n 个节点反转（n <= 链表长度）
ListNode reverseN(ListNode head, int n)
```
`reverseN(head, 3)`
![](https://labuladong.gitee.io/algo/images/%E5%8F%8D%E8%BD%AC%E9%93%BE%E8%A1%A8/6.jpg)

```java
public class Solution {
    public ListNode successor = null; // 后驱节点

    // 反转以 head 为起点的 n 个节点, 返回新的头结点
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }
}
```

1. base case 变为 `n == 1`, 反转一个元素，就是它本身, 同时要记录后驱节点
2. 刚才我们直接把 `head.next` 设置为 `null`, 因为整个链表反转后原来的 head 变成了整个链表的最后一个节点; 但现在 `head` 节点在递归反转之后不一定是最后一个节点了, 所以要记录后驱 `successor(第 n + 1 个节点)`, 反转之后将 head 连接上

### [q92. 反转链表 II](https://leetcode-cn.com/problems/reverse-linked-list-ii/)

如果 `m == 1`，就相当于反转链表开头的 `n` 个元素嘛, 也就是 [反转链表前 N 个节点](#反转链表前-n-个节点) 实现的功能

如果 `m != 1`, 如果我们把 `head` 的索引视为 `1`, 那么我们是想从第 `m` 个元素开始反转, 如果把 `head.next` 的索引视为 `1`, 那么相对于 `head.next`, 反转的区间应该是从第 `m - 1` 个元素开始的

```java
public class Solution {
    public ListNode successor = null; // 后驱节点

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    // 反转以 head 为起点的 n 个节点, 返回新的头结点
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }
}
```

迭代比较容易思考

```java
public class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        ListNode curr = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        return dummy.next;
    }
}

```

### [q2. 两数相加](https://leetcode-cn.com/problems/add-two-numbers/)

```java
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }

            carry = sum / 10;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            tail.next = new ListNode(carry);
        }

        return head;
    }
}
```

### [q445. 两数相加 II](https://leetcode-cn.com/problems/add-two-numbers-ii/)

```java
public class Solution {
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
```

如果不可以翻转链表, 使用栈: 把所有数字压入栈中, 再依次取出相加

```java
public class Solution {
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
```

### [剑指 Offer 06. 从尾到头打印链表](https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)

反转链表 参考 [q206. 反转链表](#q206-反转链表)

使用栈

```java
public class Solution {
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
```

### [q237. 删除链表中的节点](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/)

和下一个节点交换

```java
public class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

```

### [q83. 删除排序链表中的重复元素](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/)

原地修改:

我们让慢指针 `slow` 走在后面, 快指针 `fast` 走在前面探路, 找到一个不重复的元素就告诉 `slow` 并让 `slow` 前进一步

这样当 `fast` 指针遍历完整个数组 `nums` 后, `nums[0..slow]` 就是不重复元素

![](https://labuladong.gitee.io/algo/images/%e6%95%b0%e7%bb%84%e5%8e%bb%e9%87%8d/2.gif)

```java
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head, fast = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }

        // 断开与后面重复元素的连接
        slow.next = null;

        return head;
    }
}
```

### [q82. 删除排序链表中的重复元素 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/)

如果当前 `cur.next` 与 `cur.next.next` 对应的元素相同, 那么我们就需要将 `cur.next` 以及所有后面拥有相同元素值的链表节点全部删除; 
我们记下这个元素值 `xx`, 随后不断将 `cur.next` 从链表中移除, 直到 `cur.next` 为空节点或者其元素值不等于 `xx` 为止

```java
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr.next != null && curr.next.next != null) {
            if (curr.next.val == curr.next.next.val) {
                int val = curr.next.val;
                while (curr.next != null && curr.next.val == val) {
                    curr.next = curr.next.next;
                }
            } else {
                curr = curr.next;
            }
        }

        return dummy.next;
    }
}
```

### [q203. 移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/)

```java
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;

        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }

        return dummy.next;
    }
}
```

### [面试题 02.02. 返回倒数第 k 个节点](https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/)

参考 [单链表的倒数第 k 个节点](#单链表的倒数第-k-个节点)

### [q24. 两两交换链表中的节点](https://leetcode-cn.com/problems/swap-nodes-in-pairs/)

![](https://pic.leetcode-cn.com/42c91b69e3f38d63a0d0153c440724e69bd2d24b95091b4dcc5c68172f8f4e1e-%E8%BF%AD%E4%BB%A3.gif)

```java
public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p = dummy;

        while (p.next != null && p.next.next != null) {
            ListNode node1 = p.next;
            ListNode node2 = p.next.next;
            p.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            p = node1;
        }

        return dummy.next;
    }
}

```

### [q147. 对链表进行插入排序](https://leetcode-cn.com/problems/insertion-sort-list/)

1. 创建哑节点 `dummyHead`, 令 `dummyHead.next = head`; 引入哑节点是为了便于在 `head` 节点之前插入节点
2. 维护 `lastSorted` 为链表的已排序部分的最后一个节点, 初始时 `lastSorted = head`
3. 维护 `curr` 为待插入的元素, 初始时 `curr = head.next`
4. 比较 lastSorted 和 curr 的节点值
   + 若 `lastSorted.val <= curr.val`, 说明 `curr` 应该位于 `lastSorted` 之后, 将 `lastSorted` 后移一位, `curr` 变成新的 `lastSorted`
   + 否则, 从链表的头节点开始往后遍历链表中的节点m, 寻找插入 curr 的位置; 令 `prev` 为插入 `curr` 的位置的前一个节点
5. 令 `curr = lastSorted.next`, 此时 curr 为下一个待插入的元素
6. 重复第 5 步和第 6 步, 直到 curr 变成空, 排序结束

![](https://assets.leetcode-cn.com/solution-static/147/1.png)
![](https://assets.leetcode-cn.com/solution-static/147/5.png)
![](https://assets.leetcode-cn.com/solution-static/147/6.png)

```java
public class Solution {
  public ListNode insertionSortList(ListNode head) {
    if (head == null) {
      return head;
    }

    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode lastSorted = head, curr = head.next;
    while (curr != null) {
      if (lastSorted.val <= curr.val) {
        lastSorted = lastSorted.next;
      } else {
        ListNode prev = dummy;
        while (prev.next.val <= curr.val) {
          prev = prev.next;
        }
        lastSorted.next = curr.next;
        curr.next = prev.next;
        prev.next = curr;
      }
      curr = lastSorted.next;
    }
    return dummy.next;
  }
}
```

### [q148. 排序链表](https://leetcode-cn.com/problems/sort-list/)

参考 [q147. 对链表进行插入排序](#q147-对链表进行插入排序)

更好的复杂度, 类似 `merge sort`

```java
public class Solution {
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    private ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }

        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }

        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy, temp1 = head1, temp2 = head2;

        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }

        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }

        return dummy.next;
    }
}
```

### [q143. 重排链表](https://leetcode-cn.com/problems/reorder-list/)

利用线性表对链表下标进行储存

然后按照规律重新连接链表

```java
public class Solution {
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
```

寻找链表中点 + 链表逆序 + 合并链表

注意到目标链表即为将原链表的左半端和反转后的右半端合并后的结果

[q876. 链表的中间结点](#q876-链表的中间结点)
[q206. 反转链表](#q206-反转链表)

```java
public class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    private void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
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

    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
```

### [61. 旋转链表](https://leetcode-cn.com/problems/rotate-list/)

先将给定的链表连接成环, 然后将指定位置断开

```java
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }

        int n = 1;
        ListNode iter = head;

        while (iter.next != null) {
            iter = iter.next;
            n++;
        }

        int add = n - k % n;
        if (add == n) {
            return head;
        }
        iter.next = head;

        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode res = iter.next;
        iter.next = null;

        return res;
    }
}
```

### [q25. K 个一组翻转链表](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)

![](https://labuladong.gitee.io/algo/images/kgroup/8.gif)
![](https://labuladong.gitee.io/algo/images/kgroup/6.jpg)
![](https://labuladong.gitee.io/algo/images/kgroup/7.jpg)

```java
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null) {
            return head;
        }

        ListNode left, right;
        left = right = head;

        for (int i = 0; i < k; i++) {
            // 不足 k 个, 不需要反转，base case
            if (right == null) {
                return head;
            }
            right = right.next;
        }

        // 反转前 k 个元素
        ListNode newHead = reverse(left, right);

        // 递归反转后续链表并连接起来
        left.next = reverseKGroup(right, k);

        return newHead;
    }

    private ListNode reverse(ListNode left, ListNode right) {
        ListNode prev = null, curr = left, next = left;

        while (curr != right) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
```

### [q234. 回文链表](https://leetcode-cn.com/problems/palindrome-linked-list/)

![](https://labuladong.gitee.io/algo/images/%e5%9b%9e%e6%96%87%e9%93%be%e8%a1%a8/1.gif)

```java
public class Solution {

    ListNode left;

    public boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    private boolean traverse(ListNode right) {
        if (right == null) {
            return true;
        }
        boolean res = traverse(right.next);
        // 后序遍历代码
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }
}
```

优化空间复杂度

1. 先通过 **双指针技巧** 中的快慢指针来找到链表的中点:

```java
ListNode slow, fast;
slow = fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
// slow 指针现在指向链表中点
```

![](https://labuladong.gitee.io/algo/images/%e5%9b%9e%e6%96%87%e9%93%be%e8%a1%a8/1.jpg)

2. 如果`fast`指针没有指向`null`, 说明链表长度为奇数, `slow`还要再前进一步:

```java
if (fast != null)
    slow = slow.next;
```

![](https://labuladong.gitee.io/algo/images/%e5%9b%9e%e6%96%87%e9%93%be%e8%a1%a8/2.jpg)

3. 从`slow`开始反转后面的链表, 现在就可以开始比较回文串了:

```java
ListNode left = head;
ListNode right = reverse(slow);

while (right != null) {
    if (left.val != right.val)
        return false;
    left = left.next;
    right = right.next;
}
return true;
```

![](https://labuladong.gitee.io/algo/images/%e5%9b%9e%e6%96%87%e9%93%be%e8%a1%a8/3.jpg)

![](https://labuladong.gitee.io/algo/images/kgroup/8.gif)

```java
public class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null)
            slow = slow.next;

        ListNode left = head;
        ListNode right = reverse(slow);
        while (right != null) {
            if (left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }

        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
```