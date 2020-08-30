package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.GraphLoader;

import static org.junit.jupiter.api.Assertions.*;

class CapGraphTest {
    CapGraph graph_small;
    CapGraph graph1000;
    private final String small_text = "data/small_test_graph.txt";
    private final String facebook_1000 = "data/facebook_1000.txt";

    @BeforeEach
    void setUp() {
        graph_small = new CapGraph();
        graph1000 = new CapGraph();
        GraphLoader.loadGraph(graph_small, small_text);
        GraphLoader.loadGraph(graph1000, facebook_1000);
    }

    @Test
    void addVertex() {
        assertEquals(14, graph_small.getVertNums().size(), "Testing num of vertices before adding");
        graph_small.addVertex(15);
        assertEquals(15, graph_small.getVertNums().size(), "Testing num of vertices after adding");
    }

    @Test
    void addEdge() {
    }

    @Test
    void getVertNumsTest() {
        System.out.println(graph_small);
        System.out.println(graph_small.getVertNums().size());
        System.out.println(graph1000);
        System.out.println(graph1000.getVertNums().size());
/*
        assertEquals(14, graph_small.getVertNums().size(), "Testing num of vertices for small Graph");
        assertEquals(1000, graph1000.getVertNums().size(), "Testing num of vertices for 1000 Graph");*/
    }

    @Test
    void exportGraph() {
    }

    @Test
    void getEgonet() {
    }

    @Test
    void getSCCs() {
    }


}