package dataStructure.graph.q1514.maxProbability;

public class State {
    // 图节点的 id
    public int id;
    // 从 start 节点到达当前节点的概率
    public double probFromStart;

    public State(int id, double probFromStart) {
        this.id = id;
        this.probFromStart = probFromStart;
    }
}
