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
    private HashSet<Integer> connectedTo;

    public Vertex(int num) {
        this.num = num;
        connectedTo = new HashSet<>();
    }

    public int getNum() {
        return num;
    }

    public HashSet<Integer> getConnects() {
        return connectedTo;
    }

    public void addConnectTo(int vertNum) {
        connectedTo.add(vertNum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return num == vertex.num &&
                Objects.equals(connectedTo, vertex.connectedTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, connectedTo);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "num=" + num +
                ", connectedTo=" + connectedTo +
                '}';
    }
}
