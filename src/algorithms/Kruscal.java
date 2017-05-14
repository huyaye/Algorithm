package algorithms;
import java.util.List;

public class Kruscal {
	class Edge implements Comparable<Edge> {
		int p1;
		int p2;
		int weight;

		@Override
		public int compareTo(Edge o) {
			if (weight > o.weight) {
				return 1;
			} else if (weight < o.weight) {
				return -1;
			}
			return 0;
		}
	}

	DisjointSet disjointSet;
	List<Edge> sortedEdgeList;
	int vertexSize;

	Kruscal(int V, List<Edge> sortedEdgeList) {
		this.vertexSize = V;
		disjointSet = new DisjointSet(vertexSize);
	}

	int kruscal(List<Edge> kruscalEdgeList) {
		int totalWeight = 0;

		for (Edge edge : sortedEdgeList) {
			// Check cycle
			if (disjointSet.find(edge.p1) == disjointSet.find(edge.p2)) {
				continue;
			}
			disjointSet.union(edge.p1, edge.p2);
			kruscalEdgeList.add(edge);
			totalWeight += edge.weight;
		}

		return totalWeight;
	}
}
