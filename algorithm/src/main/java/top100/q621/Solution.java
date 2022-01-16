package top100.q621;

public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] temp = new int[26];
        int countMaxTask = 0, maxTask = 0;
        for (char task : tasks) {
            temp[task - 'A']++;
            maxTask = Math.max(temp[task - 'A'], maxTask);
        }

        for (int i = 0; i < 26; i++) {
            if (temp[i] == maxTask) {
                countMaxTask++;
            }
        }

        return Math.max(tasks.length, (maxTask - 1) * (n + 1) + countMaxTask);
    }
}
