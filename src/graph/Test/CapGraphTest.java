package graph.Test;

import graph.CapGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.GraphLoader;

import static org.junit.jupiter.api.Assertions.*;

class CapGraphTest {
    CapGraph graph_small;
    CapGraph graph1000;
    CapGraph graphFacebookUCSD;
    private final String small_text = "data/small_test_graph.txt";
    private final String facebook_1000 = "data/facebook_1000.txt";
    private final String facebook_UCSD = "data/facebook_ucsd.txt";

    @BeforeEach
    void setUp() {
        graph_small = new CapGraph();
        graph1000 = new CapGraph();
        graphFacebookUCSD = new CapGraph();
        GraphLoader.loadGraph(graph_small, small_text);
        GraphLoader.loadGraph(graph1000, facebook_1000);
        GraphLoader.loadGraph(graphFacebookUCSD, facebook_UCSD);
    }

    @Test
    void addVertex() {
        assertEquals(14, graph_small.getVertNums().size(), "Testing num of vertices before adding");
        graph_small.addVertex(15);
        assertEquals(15, graph_small.getVertNums().size(), "Testing num of vertices after adding");

        assertEquals(14948, graphFacebookUCSD.getVertNums().size(), "Testing FacebookUCSD vertices before adding");
        graphFacebookUCSD.addVertex(14948);
        assertEquals(14949, graphFacebookUCSD.getVertNums().size(), "Testing FacebookUCSD vertices after adding");

    }

    @Test
    void addEdge() {
        graphFacebookUCSD.addVertex(14948);
        graphFacebookUCSD.addEdge(14948, 14947);
        graphFacebookUCSD.addEdge(14949, 14948);
        graphFacebookUCSD.addEdge(14949, 14947);
//        System.out.println(graphFacebookUCSD.getVertex(14948));
//        System.out.println(graphFacebookUCSD.getVertex(14949));
        assertEquals(1, graphFacebookUCSD.getVertex(14948)
                .getConnectsTo().size(),
                "Testing num of vertices for small Graph");
        assertEquals(2, graphFacebookUCSD.getVertex(14949)
                        .getConnectsTo().size(),
                "Testing num of vertices for small Graph");

    }

    @Test
    void getVertNumsTest() {
        assertEquals(14, graph_small.getVertNums().size(), "Testing num of vertices for small Graph");
        assertEquals(14948, graphFacebookUCSD.getVertNums().size(), "Testing num of vertices for FacebookUCSD Graph");
    }

    @Test
    void getEgonet() {
    }

    @Test
    void getSCCs() {
    }


}