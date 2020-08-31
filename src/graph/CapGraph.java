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

	/**
	 *
	 * @param num
	 * @param vertex
	 */
	public void addVertex(int num, Vertex vertex) {
		if (num < 0) throw new IllegalArgumentException(
				"num of vertex can't be less than zero");
		if (vertices.containsKey(num)) return;

		vertices.put(num, vertex);
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
		Vertex vert1 = extractVertex(from);
		Vertex vert2 = extractVertex(to);

		vert1.addConnectTo(to);
		vert2.addConnectFrom(from);
	}

	private Vertex extractVertex(int vertNum) {
		Vertex vert;
		if (vertices.containsKey(vertNum))
			vert = vertices.get(vertNum);

		else {
			vert = new Vertex(vertNum);
			vertices.put(vertNum, vert);
		}
		return vert;
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
		if (!vertices.containsKey(center))
		    return egonet;

        egonet.addVertex(center);
		HashSet<Integer> egoVerts = this.vertices.get(center).getConnectsTo();
		egoVerts.add(center);

		for (Integer egoVert1 : egoVerts) {
            HashSet<Integer> allVertNeighbours = this.vertices.get(egoVert1).getConnectsTo();
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
		ArrayList<Graph> sccGraphs = new ArrayList<>();
		boolean isReverse = false;
		ArrayList<Integer> verts = new ArrayList<>(vertices.keySet());
		ArrayList<Integer> firstPass = dfs(verts, isReverse);

		HashSet<Integer> visited = new HashSet<>();
		ArrayList<Integer> sccSubGraph;
		CapGraph scc;

		isReverse = true;
		int v;

		while (!firstPass.isEmpty()) {
			sccSubGraph = new ArrayList<>();

			v = firstPass.get(firstPass.size() - 1);
			firstPass.remove(firstPass.size() - 1);

			if (!visited.contains(v)) {
				dfsVisit(v, visited, sccSubGraph, isReverse);
				scc = new CapGraph();
				for (Integer node : sccSubGraph) {
					scc.addVertex(node);
				}
				sccGraphs.add(scc);
			}
		}
		return sccGraphs;
	}

	/**
	 *
	 * @param verts
	 * @return
	 */
	private ArrayList<Integer> dfs(ArrayList<Integer> verts, boolean isReverse) {
		HashSet<Integer> visited = new HashSet<>();
		ArrayList<Integer> finished = new ArrayList<>(verts.size());
		int v;

		while (!verts.isEmpty()) {
			v = verts.get(verts.size() - 1);
			verts.remove(verts.size() - 1);

			if (!visited.contains(v)) {
				dfsVisit(v, visited, finished, isReverse);
			}

		}
		return finished;
	}

	/**
	 *
	 * @param v
	 * @param visited
	 * @param finished
	 */
	private void dfsVisit(int v, HashSet<Integer> visited,
						  ArrayList<Integer> finished, boolean isReverse) {
		visited.add(v);
		for (Integer n : getSCCNeighbours(v, isReverse)) {
			if (!visited.contains(n)) {
				dfsVisit(n, visited, finished, isReverse);
			}
		}
		finished.add(v);
	}

	/**
	 *
	 * @param vert
	 * @return
	 */
	private HashSet<Integer> getSCCNeighbours(int vert, boolean isReverse) {
		if (isReverse)
			return vertices.get(vert).getConnectsFrom();
		else
			return vertices.get(vert).getConnectsTo();
	}

	public Set<Integer> getVertNums() {
		return vertices.keySet();
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
			export.put(key, vertices.get(key).getConnectsTo());
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
