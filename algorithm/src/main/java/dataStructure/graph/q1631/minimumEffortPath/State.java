package dataStructure.graph.q1631.minimumEffortPath;

public class State {
    // 矩阵中的一个位置
    public int x;
    public int y;
    // 从起点 (0, 0) 到当前位置的最小体力消耗（距离）
    int effortFromStart;

    public State(int x, int y, int effortFromStart) {
        this.x = x;
        this.y = y;
        this.effortFromStart = effortFromStart;
    }
}
