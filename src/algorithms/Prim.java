package algorithms;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {	// O(ElogV)
	List<List<Edge>> adjacencyList;
	boolean inTree[];

	public Prim(List<List<Edge>> adjacencyList) {
		this.adjacencyList = adjacencyList;
		inTree = new boolean[adjacencyList.size()];
	}

	public double getMinimumSpanningTree(List<Edge> selectedEdgeList) {
		double totalWeight = 0;

		PriorityQueue<Edge> minWeightHeap = new PriorityQueue<Edge>(new Comparator<Edge>() {
			@Override
			public int compare(Edge a, Edge b) {
				if (a.weight > b.weight) {
					return 1;
				} else if (a.weight < b.weight) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		/*
		 * 초기화 : 첫번째 vertex 를 Tree에 넣고 연결된 edge 들을 Heap 에 넣는다.
		 */
		inTree[0] = true;
		for (Edge edge : adjacencyList.get(0)) {
			minWeightHeap.add(edge);
		}

		while (!minWeightHeap.isEmpty()) {
			// 후보 경로중 가장 작은 가중치의 edge 를 가져온다.
			Edge minWeightEdge = minWeightHeap.poll();
			if (inTree[minWeightEdge.p2] == true) {
				continue; // 이미 Tree 에 들어있는 vertex 와 연결된 edge 일경우 패스.
			}
			inTree[minWeightEdge.p2] = true;
			for (Edge edge : adjacencyList.get(minWeightEdge.p2)) {
				if (inTree[edge.p2] == false) {
					minWeightHeap.add(edge);
				}
			}
			totalWeight += minWeightEdge.weight;
		}

		return totalWeight;
	}
}
