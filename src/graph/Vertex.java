package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Vertex {
    private final int num;
    private HashSet<Integer> edges;

    public Vertex(int num) {
        this.num = num;
        edges = new HashSet<>();
    }

    public int getNum() {
        return num;
    }

    public HashSet<Integer> getEdges() {
        return edges;
    }

    public void addEdgeTo(int vertNum) {
        edges.add(vertNum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return num == vertex.num &&
                Objects.equals(edges, vertex.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, edges);
    }
}
