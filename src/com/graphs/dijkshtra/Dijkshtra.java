package com.graphs.dijkshtra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//This algo is used to find out shortest distance from source node to any given node.
public class Dijkshtra {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int[] shortestPath(int N,int M, int[][] edges) {
// 		Using Dijkshtra
//		Declaring PriorityQueue with int array
//		where arr[0] will be distance
//		arr[1] wil be node. -> ie., distance travelled to reach node 
//		Using PriorityQueue as min heap, because at each point we need min distance.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        List<List<List<Integer>>> adj = new ArrayList<>();
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for(int i = 0; i < N; i ++)
            adj.add(new ArrayList<>());
//        edges arr will have 3 values at each index
//        0 -> source node
//        1 -> destination node
//        2 -> with distance
//        i.e from src to destination it has that much distance
//        Note: it;s a direct edge.
        
//        Creating a adj list with dest node and dist value, each index represents indiv node
//        	with it's neighbours
        for(int[] edge: edges)
            adj.get(edge[0]).add(Arrays.asList(edge[1], edge[2]));
        
//        Considering source node as 0 for this example
//        so reach source node the distance is always 0.
//        PriorityQueue wil have dist and node at each point.
//        example if (500, 4) -> distance 500 to reach node 4.
        pq.add(new int[]{0, 0});
        dist[0] = 0;
        while(!pq.isEmpty()){
            int[] poll = pq.remove();
            int d = poll[0];
            int node = poll[1];
//            if the distance you calculated previously/or not computed before
//            is less than current dist you got,then update the dist array and add it to PriorityQueue
            for(List<Integer> neighbourWithWeight: adj.get(node)){
                int newWeight = neighbourWithWeight.get(1) + d;
                int neighbour = neighbourWithWeight.get(0);
                if(newWeight < dist[neighbour]){
                    dist[neighbour] = newWeight;
                    pq.add(new int[]{newWeight, neighbour});
                }
            }
        }
        for(int i = 0; i < N; i ++){
            if(dist[i] == Integer.MAX_VALUE)
                dist[i] = -1;
        }
        return dist;
	}

}
