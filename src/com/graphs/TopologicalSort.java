package com.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


// This algo is used to determine the order of the graph from start node to end node.
public class TopologicalSort {
	public static void main(String[] args) {
		int numberOfVertices = 9; // from 0 to 8
		ArrayList<ArrayList<Integer>> adj =  new ArrayList<>();
		for(int i = 0; i < numberOfVertices; i ++)
			adj.add(new ArrayList<>());
		
//		Creating adjacency list.
//		if a node is missed from 0-8 it means it don't have any outgoing edge;
		adj.get(1).add(4);
		adj.get(3).add(2);
		adj.get(4).add(3);
		adj.get(4).add(5);
		adj.get(5).add(0);
		adj.get(6).add(7);
		adj.get(7).add(1);
		adj.get(8).add(6);
		System.out.println(Arrays.toString(usingIndegree(numberOfVertices, adj)));
		System.out.println(Arrays.toString(usingStack(numberOfVertices, adj)));
	}
	
	
//	This algo works by cutting out the edges from start nodes
//	U will keep cutting until you make other node as start node(i.e, no incoming edges)
//	Like that you will keep adding nodes to answer
	private static int[] usingIndegree(int n, ArrayList<ArrayList<Integer>> adj) {
		// Using indegree
        int[] indegree = new int[n];
        int[] ans = new int[n];
        Queue<Integer> q = new LinkedList<>();
        
//      Travelling over the adj list and updating the indegree
//      If a node x, has an outgoing to y -> it means y has an incoming edge. so update indegree[y]
        for(List<Integer> li: adj){
            for(int i: li)
                indegree[i] ++;
        }
        for(int i = 0; i < n; i ++)
//        	If a node has indegree as 0 it means it can be a start node, or an isolated node. 
            if(indegree[i] == 0)
                q.offer(i);
                
        int i = 0;
        while(!q.isEmpty()){
            int poll = q.poll();
            ans[i ++] = poll;
            for(int node: adj.get(poll)){
//            	get the node and cut of all the outgoing edges from it.
//            	i.e., incoming edges for the other node.
//            	Then if you found if node has no incomin9g edges left, so it;s 
//            	a start node again then push it to the queue
                if(-- indegree[node] == 0)
                    q.offer(node);
            }
        }
        return ans;
	}
	
	
//	Using stack:
//	you'll keep adding the nodes to the stack from the end, so that 
//	the top always points to the start node. DFS
	private static int[] usingStack(int n, ArrayList<ArrayList<Integer>> adj) {
		// Using indegree
        int[] ans = new int[n];
        boolean[] v = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i ++) {
        	if(!v[i]) {
        		addToStack(adj, i, v, stack);
        	}
        }
        int i = 0;
        while(!stack.isEmpty()) {
        	ans[i ++] = stack.pop();
        }
        return ans;
	}

	private static void addToStack(ArrayList<ArrayList<Integer>> adj, int i, boolean[] v, Stack<Integer> stack) {
			
		v[i] = true;
		for(int j: adj.get(i)) {
			if(!v[j]) {
				addToStack(adj, j, v, stack);
			}
		}
		stack.push(i);
	}
}

