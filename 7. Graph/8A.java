package proj_ex1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

//BFS, DFS

/*
 1. 정점(N)과 간선(M)의 값을 받아서 인접행렬에 저장
 2. 방문한 순서대로 ArrayList에 저장해서 출력
 	1)BFS(너비우선) ->Queue 로 구현
 	2)DFS(깊이우선) ->Stack으로 구현
 */

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public ArrayList<Integer> findDfsList(boolean[][] adj, int N) {
		ArrayList<Integer> visitedNode = new ArrayList<>();
		Stack<Graph> dfsList = new Stack<>();
		boolean[] isVisited = new boolean[N + 1];
		dfsList.add(new Graph(1, 1));

		while (dfsList.isEmpty() == false) {
			Graph current = dfsList.pop();

			if (isVisited[current.nodeIndex]) {
				continue;
			}
			visitedNode.add(current.nodeIndex);
			isVisited[current.nodeIndex] = true;

			for (int i = N; i > 0; i--) {
				if (isVisited[i] == false && (adj[i][current.nodeIndex] || adj[current.nodeIndex][i])) {
					Graph nextNode = new Graph(i, current.nodeDepth+1);
					dfsList.add(nextNode);
				}
			}

		}

		return visitedNode;
	}

	public ArrayList<Integer> findBfsList(boolean[][] adj, int N) {
		ArrayList<Integer> visitedNode = new ArrayList<>();
		Queue<Graph> bfsList = new LinkedList<>();
		boolean[] isVisited = new boolean[N + 1];

		bfsList.add(new Graph(1, 1));

		while (bfsList.isEmpty() == false) {
			Graph current = bfsList.poll();

			if (isVisited[current.nodeIndex]) {
				continue;
			}

			visitedNode.add(current.nodeIndex);

			isVisited[current.nodeIndex] = true;

			for (int i = 1; i < N + 1; i++) {
				if (isVisited[i] == false && (adj[current.nodeIndex][i] || adj[i][current.nodeIndex])) {
					Graph nextNode = new Graph(i, current.nodeDepth + 1);
					bfsList.add(nextNode);
				}
			}

		}

		return visitedNode;
	}

	public static void main(String[] args) {
		Main app = new Main();
		int N = scanner.nextInt();
		int M = scanner.nextInt();

		boolean[][] adj = new boolean[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();

			adj[u][v] = true;
			adj[v][u] = true;
		}

		ArrayList<Integer> bfsList = app.findBfsList(adj, N);
		ArrayList<Integer> dfsList = app.findDfsList(adj, N);
		
		app.printArrayList(dfsList);
		app.printArrayList(bfsList);
	}

	public void printArrayList(ArrayList<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			if (i > 0) {
				System.out.print("-");
			}
			System.out.print(list.get(i));
		}
		System.out.println();
	}
}

class Graph {
	int nodeIndex;
	int nodeDepth;

	public Graph(int nodeIndex, int nodeDepth) {
		this.nodeDepth = nodeDepth;
		this.nodeIndex = nodeIndex;
	}

}