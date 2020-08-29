package graph;

import java.util.HashMap;
import java.util.Objects;

public class Vertex {
    private final int num;
    private HashMap<Vertex, Edge> edges;

    public Vertex(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public HashMap<Vertex, Edge> getEdges() {
        return edges;
    }

    public void addEdgeTo(Vertex vert) {
        Edge edge = new Edge(this, vert);
        edges.put(vert, edge);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return num == vertex.num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }
}
