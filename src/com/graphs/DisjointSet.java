package com.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DisjointSet {

	int[] rank;
	int[] parent;
	int[] size;

	public DisjointSet(int n) {
		rank = new int[n + 1];
		size = new int[n + 1];
		parent = new int[n + 1];
//		considering 0-n nodes
		for (int i = 0; i <= n; i++) {
			rank[i] = 0;
			size[i] = 1;
//			initially considering nodes are not connected, so each parent would be itself.
			parent[i] = i;
		}
	}

	public void unionByRank(int u, int v) {
		int ulp_v = findParent(v); // ultimate parent of v;
		int ulp_u = findParent(u); // ultimate parent of v;
		if (ulp_u == ulp_v) // already connected so do not connect them.
			return;
		if (rank[ulp_u] < rank[ulp_v]) {
//			If rank of one node is less than other
//			then connect lower rank node to higher rank node
			parent[ulp_u] = ulp_v;
		} else if (rank[ulp_v] < rank[ulp_u]) {
//			If rank of one node is less than other
//			then connect lower rank node to higher rank node
			parent[ulp_v] = ulp_u;
		} else {
//			rank equal case.
//			since rank equal you can connect in any order.
			parent[ulp_v] = ulp_u;
//			update rank of the higher node.
			rank[ulp_u]++;
//			even this would work
//			parent[ulp_u] = ulp_v;
//			rank[ulp_v] ++;
		}
	}
	
	public void unionBySize(int u, int v) {
		int ulp_v = findParent(v); // ultimate parent of v;
		int ulp_u = findParent(u); // ultimate parent of v;
		if (ulp_u == ulp_v) // already connected so do not connect them.
			return;
		if (size[ulp_u] < size[ulp_v]) {
//			when size of u is less than v, always connect smaller size comp to big size comp
//			after union update parent and size.
			size[ulp_v] += size[ulp_u];
			parent[ulp_u] = ulp_v;
		} 
		else {
//			size less than or equal case
//			connect smaller one to bigger one
//			or connect equal comps
//			then update size and parent
			parent[ulp_v] = ulp_u;
			size[ulp_u] += size[ulp_v];
		}
	}

	public int findParent(int u) {
		if (u == parent[u])
			return u;
//		path compression
//		instead of this 5->4->3->2->1
//		we do this 5->4, 5->3, 5->2, 5->1
//		which allows us to find if nodes are connected in O(1) time.
		return parent[u] = findParent(parent[u]);
	}

	public static void main(String[] args) {
		DisjointSet ds = new DisjointSet(7); // taking 7 nodes.
//		ds.unionByRank(1, 2);
//		ds.unionByRank(2, 3);
//		ds.unionByRank(4, 5);
//		ds.unionByRank(6, 7);
//		ds.unionByRank(5, 6);
////		find if 3, 7 are connected
//		if (ds.findParent(3) == ds.findParent(7))
//			System.out.println("Yes");
//		else
//			System.out.println("No");
//		ds.unionByRank(3, 7);
//		if (ds.findParent(3) == ds.findParent(7))
//			System.out.println("Yes");
//		else
//			System.out.println("No");
		
		ds.unionBySize(1, 2);
		ds.unionBySize(2, 3);
		ds.unionBySize(4, 5);
		ds.unionBySize(6, 7);
		ds.unionBySize(5, 6);
//		find if 3, 7 are connected
		if (ds.findParent(3) == ds.findParent(7))
			System.out.println("Yes");
		else
			System.out.println("No");
		ds.unionBySize(3, 7);
		if (ds.findParent(3) == ds.findParent(7))
			System.out.println("Yes");
		else
			System.out.println("No");
		
//		kruskalAlgorithmForMinimumSpanningTree
//		To find MinimumSpanningTree
//		1. sort the edges by their weight
//		2. Then connect the sorted edges -> you'll always take min weights first, 
//		so it will always form min total weight
		int[][] edges = new int[][] {
			{5, 3, 4},
			{3, 2, 3},
			{4, 1, 5},
			{3, 2, 4},
			{7, 2, 6},
			{1, 1, 4},
			{2, 1, 2},
			{7, 2, 6},
			{9, 4, 5},
			{8, 3, 6},
		};
//		System.out.println("Minimum spanning tree total weight is: " + kruskalAlgorithmForMinimumSpanningTree(9, edges));
	}

	private static int kruskalAlgorithmForMinimumSpanningTree(int n, int[][] edges) {
		DisjointSet ds = new DisjointSet(n);
		int minSum = 0;
		List<Edge> edgesList = new ArrayList<>();
		for(int[] e: edges) {
			edgesList.add(new Edge(e[0], e[1], e[2]));
		}
//		Sorting by weights.
		Collections.sort(edgesList, ((a, b) -> a.weight - b.weight));
		for(Edge e: edgesList) {
//			If they do not belong to the same component then connect them
//			as edges are sorted by weights, you always take min weights first
//			until or unless all edges are connected.
			if(ds.findParent(e.src) != ds.findParent(e.dest)) {
				ds.unionByRank(e.src, e.dest);
				minSum += e.weight;
			}
				
		}
		return minSum;
	}
}

class Edge {
	int src;
	int dest;
	int weight;

	public Edge(int weight, int src, int dest) {
		super();
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}

}
