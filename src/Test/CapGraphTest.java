package Test;

import graph.CapGraph;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import util.GraphLoader;

import static org.junit.Assert.*;


public class CapGraphTest {
    CapGraph graph_small;
    CapGraph graph1000;
    private final String small_text = "data/small_test_graph.txt";
    private final String facebook_1000 = "data/facebook_1000.txt";

    @BeforeEach
    public void setUp() {
        graph_small = new CapGraph();
        graph1000 = new CapGraph();
        GraphLoader.loadGraph(graph_small, small_text);
        GraphLoader.loadGraph(graph1000, facebook_1000);
    }

    @Test (timeout = 1000)
    public void testSize() {
        graph_small = new CapGraph();
        GraphLoader.loadGraph(graph_small, small_text);
        System.out.println(graph_small);

        /* System.out.println(graph_small.getVertNums().size());

        assertEquals("Testing num of vertices for small Graph", 14, graph_small.getVertNums().size());
        assertEquals("Testing num of vertices for 1000 Graph", 1000, graph1000.getVertNums().size());

         */

    }
}