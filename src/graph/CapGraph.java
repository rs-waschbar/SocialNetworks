package graph;

import java.util.*;

/**
 * @author Ruslan Zhdanov.
 * 
 * The warm up assignment, implement of a Graph for socialNetworks.
 *
 */
public class CapGraph implements Graph {
	private HashMap<Integer, Vertex> vertices;
	private HashSet<Edge> edges;

	/**
	 *
	 */
	public CapGraph() {
		vertices = new HashMap<>();
	}

	/**
	 *
	 * @param num
	 */
	@Override
	public void addVertex(int num) {
		if (num < 0) throw new IllegalArgumentException(
								"num of vertex can't be less than zero");
		if (vertices.containsKey(num)) return;

		vertices.put(num, new Vertex(num));
	}

	public Vertex getVertex(int num) {
		if (!vertices.containsKey(num)) throw new IllegalArgumentException(
				"Graph don't contains this Vertex number");

		return vertices.get(num);
	}

	/**
	 *
	 * @param from
	 * @param to
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
			vertices.put(from, vert);
		}
		edges.add(new Edge(from, to));
		vert.addConnectTo(to);
	}

	public Set<Integer> getVertNums() {
		return vertices.keySet();
	}


	/**
	 *
	 * @param center
	 * @return
	 */
	@Override
	public Graph getEgonet(int center) {
		if (center < 0) throw new IllegalArgumentException(
								"num of vertex can't be less than zero");
        CapGraph egonet = new CapGraph();
		if (!this.vertices.containsKey(center))
		    return egonet;

        egonet.addVertex(center);
		HashSet<Integer> egoVerts = this.vertices.get(center).getConnects();
		egoVerts.add(center);

		for (Integer egoVert1 : egoVerts) {
            HashSet<Integer> allVertNeighbours = this.vertices.get(egoVert1).getConnects();

            for (Integer egoVert2 : egoVerts) {
                if (allVertNeighbours.contains(egoVert2)) {
                    egonet.addEdge(egoVert1, egoVert2);
                }
            }
        }
		return egonet;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public List<Graph> getSCCs() {
		HashMap<Integer, HashSet<Integer>> reverseEdges = new HashMap<>();
/*
		for (Integer vert : vertices.keySet()) {
			reverseEdges.put(vert, new HashSet<>());
		}

		for (Integer startEdge : vertices.keySet()) {
			HashSet<Integer> endEdges = vertices.get(startEdge).getEdges();
			for (Integer endEdge : endEdges) {
				reverseEdges.put(endEdge, )
			}
		}
*/

		boolean isReverse = false;




		return null;
	}

	/**
	 *
	 * @param graph
	 * @param verts
	 * @return
	 */
	private ArrayList<Integer> dfs(Graph graph, ArrayList<Integer> verts, boolean isReverse,
								   HashMap<Integer, HashSet<Integer>> reverseEdges) {
		HashSet<Integer> visited = new HashSet<>();
		ArrayList<Integer> finished = new ArrayList<>(verts.size());
		int v;

		while (!verts.isEmpty()) {
			v = verts.get(verts.size() - 1);
			verts.remove(verts.size() - 1);
			if (!visited.contains(v)) {
				dfsVisit(graph, v, visited, finished, isReverse, reverseEdges);
			}
		}
		return finished;
	}

	/**
	 *
	 * @param graph
	 * @param v
	 * @param visited
	 * @param finished
	 */
	private void dfsVisit(Graph graph, int v, HashSet<Integer> visited,
						  ArrayList<Integer> finished, boolean isReverse,
						  HashMap<Integer, HashSet<Integer>> reverseEdges) {
		visited.add(v);
		for (Integer n : getSCCNeighbours(v, isReverse, reverseEdges)) {
			if (!visited.contains(n)) {
				dfsVisit(graph, n, visited, finished, isReverse, reverseEdges);
			}
		}
		finished.add(v);
	}

	/**
	 *
	 * @param vert
	 * @return
	 */
	private HashSet<Integer> getSCCNeighbours(int vert, boolean isReverse,
											  HashMap<Integer, HashSet<Integer>> reverseEdges) {
		if (isReverse) {
			return reverseEdges.get(vert);
		}
		else {
			return vertices.get(vert).getConnects();
		}
	}

	/**
	 *
	 * @return
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		if (vertices == null) return null;

		HashMap<Integer, HashSet<Integer>> export = new HashMap<>();
		for (Integer key : vertices.keySet()) {
			export.put(key, vertices.get(key).getConnects());
		}
		return export;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return "CapGraph{" +
				"vertices=" + vertices.keySet() +
				'}';
	}
}
