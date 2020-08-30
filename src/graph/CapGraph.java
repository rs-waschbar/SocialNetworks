package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ruslan Zhdanov.
 * 
 * The warm up assignment, implement of a Graph for socialNetworks.
 *
 */
public class CapGraph implements Graph {
	private HashMap<Integer, Vertex> vertices;

	public CapGraph() {
		vertices = new HashMap<>();
	}

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
		Vertex vert;

		if (vertices.containsKey(from)) {
			vert = vertices.get(from);
		}
		else {
			vert = new Vertex(from);
		}
		vert.addEdgeTo(to);
	}

	public Set<Integer> getVertNums() {
		return vertices.keySet();
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		if (center < 0) throw new IllegalArgumentException(
								"num of vertex can't be less than zero");
        CapGraph egonet = new CapGraph();
		if (!this.vertices.containsKey(center))
		    return egonet;

        egonet.addVertex(center);
		HashSet<Integer> egoVerts = this.vertices.get(center).getEdges();
		egoVerts.add(center);

		for (Integer egoVert1 : egoVerts) {
            HashSet<Integer> allVertNeighbours = this.vertices.get(egoVert1).getEdges();

            for (Integer egoVert2 : egoVerts) {
                if (allVertNeighbours.contains(egoVert2)) {
                    egonet.addEdge(egoVert1, egoVert2);
                }
            }
        }
		return egonet;
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
		if (vertices == null) return null;

		HashMap<Integer, HashSet<Integer>> export = new HashMap<>();
		for (Integer key : vertices.keySet()) {
			export.put(key, vertices.get(key).getEdges());
		}
		return export;
	}

}
