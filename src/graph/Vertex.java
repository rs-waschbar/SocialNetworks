package graph;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author Ruslan Zhdanov.
 *
 * The warm up assignment, implement of a Graph for socialNetworks.
 *
 */

public class Vertex implements Cloneable {
    private final int num;
    private HashSet<Integer> edgesTo;
    private HashSet<Integer> edgesFrom;

    public Vertex(int num) {
        this.num = num;
        edgesTo = new HashSet<>();
        edgesFrom = new HashSet<>();
    }

    public int getNum() {
        return num;
    }

    public HashSet<Integer> getConnectsTo() {
        return edgesTo;
    }

    public HashSet<Integer> getConnectsFrom() {
        return edgesFrom;
    }

    public void addConnectTo(int vertNum) {
        edgesTo.add(vertNum);
    }

    public void addConnectFrom(int vertNum) {
        edgesFrom.add(vertNum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return num == vertex.num &&
                Objects.equals(edgesTo, vertex.edgesTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, edgesTo);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "num=" + num +
                ", connectedTo=" + edgesTo +
                '}';
    }
}
