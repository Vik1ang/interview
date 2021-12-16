package dataStructure.graph.q277.findCelebrity;

public class Solution1 extends Relation {
    public int findCelebrity(int n) {
        // 先假设 cand 是名人
        int candidate = 0;
        for (int other = 0; other < n; other++) {
            if (!knows(other, candidate) || knows(candidate, other)) {
                // cand 不可能是名人，排除
                // 假设 other 是名人
                candidate = other;
            } else {
                // other 不可能是名人，排除
                // 什么都不用做，继续假设 cand 是名人
            }
        }

        // 现在的 cand 是排除的最后结果，但不能保证一定是名人
        for (int other = 0; other < n; other++) {
            if (candidate == other) {
                continue;
            }
            // 需要保证其他人都认识 cand，且 cand 不认识任何其他人
            if (!knows(other, candidate) || knows(candidate, other)) {
                return -1;
            }
        }

        return candidate;
    }
}
