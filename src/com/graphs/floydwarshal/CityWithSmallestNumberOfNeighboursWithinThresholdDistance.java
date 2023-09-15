package com.graphs.floydwarshal;

import java.util.Arrays;

//There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi , toi ,weighti]
//represents a bidirectional and weighted edge between cities fromi and toi, 
//and given the integer distance Threshold. You need to find out a city 
//with the smallest number of cities that are reachable through some path and 
//whose distance is at most Threshold Distance, If there are multiple such cities, 
//our answer will be the city with the greatest number.
//
//Note: that the distance of a path connecting cities i and j is equal to the sum of the edges
//weights along that path.

//
//Floyd Warshall
//-We can find shortest path between two different nodes using Dijkshtra & Bellman 
//-then y floyd warshal
//-Floyd warshal helps us to find shortest path between any two given nodes, we maintain
//a 2d matrix with n rows and cols where a particular value at matrix[row][col] gives us the 
//min distance between nodes row and col, so usign this matrix we can maintain all the shortest 
//paths between any two nodes
//-This algo accepts only directed graph, so convert undirected to directed before applying this algo
//-Multisource shorted path
//-Also detects negative cycles
//-Note: we've find path via every node only then we'll get shortest distance possible

//TC: O(n^3) -> where n is no of nodes.
//SC: O(n*n) -> where n is no of nodes.

public class CityWithSmallestNumberOfNeighboursWithinThresholdDistance {

	public static void main(String[] args) {
		int numberOfNodes = 6;
		int numberOfEdges = 6;
		int[][] edges = new int[][] { { 0, 1, 2 }, { 0, 5, 7 }, { 1, 2, 1 }, { 1, 3, 3 }, { 3, 5, 1 }, { 2, 4, 2 } };
		int distanceThreshold = 5;
		int ans = findCity(numberOfNodes, numberOfEdges, edges, distanceThreshold);
		System.out.println("Answer: " + ans);
	}

	public static int findCity(int n, int m, int[][] edges, int distanceThreshold) {
//        Matrix that stores all the shortest paths
		int[][] mat = new int[n][n];
//		Initially fill all values to max
		for (int i = 0; i < n; i++)
			Arrays.fill(mat[i], (int) (1e9));
//       As distance between each node to itself is 0.
		for (int i = 0; i < n; i++)
			mat[i][i] = 0;
//      updating mat with given edge weights
		for (int[] edge : edges) {
			mat[edge[0]][edge[1]] = edge[2];
			mat[edge[1]][edge[0]] = edge[2];
		}

//        Floyd warshall algo starts.
//        You must travel via each edge so k loop
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
				}
			}
		}
		int min = Integer.MAX_VALUE;
		int ans = -1;
//        Whichever node has least neighbours with given distance is the answer.
		for (int i = 0; i < n; i++) {
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (mat[i][j] <= distanceThreshold) {
					count++;
				}
			}
			if (count <= min) {
				min = count;
				ans = i;
			}
		}
		return ans;

	}
}
