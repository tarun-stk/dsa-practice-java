package com.graphs.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Given a sorted dictionary of an alien language having N words and k starting alphabets 
 * 	of standard dictionary. Find the order of characters in the alien language.
	Note: Many orders may be possible for a particular test case, thus you may return 
		any valid order and output will be 1 if the order of string returned by the function 
		is correct else 0 denoting incorrect string returned.
 * 
 * 
 * 	sorted input
 *  {"baa","abcd","abca","cab","cad"}
 *  baa - 0
 *  abcd - 1
 *  abca - 2
 *  cab - 3
 *  cad - 4
 *  
 * here -b-aa(0) comes before -a-bcd(1) because b is less then a
 * abc-d-(1) comes before abc-a-(2) because d is smaller then a
 * -a-bca(2) comes before -c-ab(3) becuase a is smaller then c
 * ca-b-(3) comes before ca-d-(4) because b is smaller then d
 * 
 * so we've something coming before something, so we can use topo sort algo
 * first create graph with directed edges from smaller to bigger
 * ex: (b,a) - b->a
 * (d,a) - b->a
 * (a,c) - b->a
 * (b,d) - b->a
 * 	create graph with these edges.
 * then simply apply topo sort to get the order and return it.
 * 
 */
public class AlienDictionary {

	public static void main(String[] args) {

		String[] sortedDict = { "baa", "abcd", "abca", "cab", "cad" };
		System.out.println(findOrder(sortedDict, sortedDict.length, 4));
	}

	static public String findOrder(String[] dict, int n, int k) {
		// Write your code here
		int[] inDegree = new int[k];
//        Treating chars as ints later will convert to char 
		List<Integer>[] adj = new ArrayList[k];
		String st = "";
		
		for (int i = 0; i < k; i++) {
			adj[i] = new ArrayList();
		}

		for (int i = 0; i < n - 1; i++) {
			char[] s1 = dict[i].toCharArray();
			char[] s2 = dict[i + 1].toCharArray();
			int j = 0;

//            Finding the not similar character to figure out which comes first and next.
			while (j < s1.length && j < s2.length && s1[j] == s2[j])
				++j;

//            Created an edge, also updating indegree val.
//            Bigger character will have an incoming edge.
			if (j < s1.length && j < s2.length) {
				inDegree[s2[j] - 'a']++;
				adj[s1[j] - 'a'].add(s2[j] - 'a');
			}

		}
		Queue<Integer> q = new LinkedList<>();
//        Adding ints who've a indegree value as 0, means its can be a starting letter
//        Also simultaneously adding the anser.
		for (int i = 0; i < k; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
				st += (char) (i + 97) + "";
			}

		}

//        Applying standard topo sort algo.
		while (!q.isEmpty()) {
			int poll = q.poll();
			for (int i : adj[poll]) {
				if (--inDegree[i] == 0) {
					q.offer(i);
					st += (char) (i + 97) + "";
				}

			}
		}
		return st;
	}

}
