package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author Ruslan Zhdanov.
 * 
 * The warm up assignment, implement of a Graph for socialNetworks.
 *
 */
public class CapGraph implements Graph {
	private HashMap<Integer, Vertex> vertices = new HashMap<>();


	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		if (num < 0) throw new IllegalArgumentException(
								"num of vertex can't be less than zero");
		if (vertices.containsKey(num)) throw new IllegalArgumentException(
								"vertex is already added");
		vertices.put(num, new Vertex(num));
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		if (from < 0 || to < 0) throw new IllegalArgumentException(
				"num of vertex can't be less than zero");
		if (!vertices.containsKey(from)) throw new IllegalArgumentException(
				"vertex must be added");

		Vertex vert = vertices.get(from);
		vert.addEdgeTo(to);
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
