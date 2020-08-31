package graph;

public class Edge {
    private final int start;
    private final int end;

    public Edge(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Edge getSwapEdge(Edge edge) {
        return new Edge(this.end, this.start);
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
