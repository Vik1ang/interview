# 链表

### [q21. 合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/submissions/)

![](https://labuladong.gitee.io/algo/images/%e9%93%be%e8%a1%a8%e6%8a%80%e5%b7%a7/1.gif)

`while` 循环每次比较 `p1` 和 `p2` 的大小，把较小的节点接到结果链表上:

代码中还用到一个链表的算法题中是很常见的**虚拟头结点**技巧, 也就是 **dummy** 节点

有了 **dummy** 节点这个占位符, 可以避免处理空指针的情况, 降低代码的复杂性

