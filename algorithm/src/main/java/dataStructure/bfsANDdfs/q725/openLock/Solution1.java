package dataStructure.bfsANDdfs.q725.openLock;

import java.util.*;

public class Solution1 {
    public int openLock(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set<String> deads = new HashSet<>();
        for (String deadend : deadends) {
            deads.add(deadend);
        }

        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        Deque<String> q = new LinkedList<>();

        // 从起点开始启动广度优先搜索
        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int size = q.size();
            /* 将当前队列中的所有节点向周围扩散 */
            for (int i = 0; i < size; i++) {
                String curr = q.poll();

                /* 判断是否到达终点 */
                if (deads.contains(curr)) {
                    continue;
                }
                if (curr.equals(target)) {
                    return step;
                }

                /* 将一个节点的未遍历相邻节点加入队列 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(curr, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }

                    String down = minusOne(curr, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }

            step++;
        }
        return -1;
    }

    // 将 s[j] 向上拨动一次
    private String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }

    // 将 s[i] 向下拨动一次
    private String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }
}
