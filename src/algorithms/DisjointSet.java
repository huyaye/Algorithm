package algorithms;

public class DisjointSet {
	int[] parent;
	int[] rank;

	DisjointSet(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	int find(int u) {
		if (u == parent[u]) {
			return u;
		}
		parent[u] = find(parent[u]);
		return parent[u];
	}

	void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v) {
			return;
		}
		if (rank[u] > rank[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		parent[u] = v;
		if (rank[u] == rank[v]) {
			++rank[v];
		}
	}
}
