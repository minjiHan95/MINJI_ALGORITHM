package algorithm;

import java.util.*;

/*
 오일러 경로의 존재여부: 차수가 홀수 2개 존재]
 오일러 회로의 존재여부: 차수가 홀수 0개  
 */

public class Main {

	public static final Scanner scanner = new Scanner(System.in);

	public boolean isConnected(Graph myGraph) {
		int depth = myGraph.node;
		int count = 0;
		int initialNode = 0;
		boolean[] isVisited = new boolean[depth+1];

		Queue<Integer> connectedGraph = new LinkedList<>();
		connectedGraph.add(initialNode);
		while (connectedGraph.isEmpty() == false) {
			int currentNode = connectedGraph.poll();

			if (isVisited[currentNode]) {
				continue;
			}

			isVisited[currentNode] = true;
			count++;

			for (int i = 0; i < depth; i++) {

				if (myGraph.adjArr[currentNode][i] == 1) {
					connectedGraph.add(i);
				}

			}
		}

		return count == depth;
	}

	public void hasEulerPath(Graph myGraph) {

		if (isConnected(myGraph)) {
			System.out.println("NO");
			return;
		}

		int node = myGraph.node;
		int count = 0;
		int holCouunt = 0;
		for (int i = 0; i < node; i++) {
			if (myGraph.degree[i] % 2 != 0) {
				count++;
			}

		}
		if (count == 2 || count == 0) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

	public void hasEulerCircuit(Graph myGraph) {

		if (isConnected(myGraph)) {
			System.out.println("NO");
			return;
		}

		int node = myGraph.node;
		int count = 0;
		int holCouunt = 0;
		for (int i = 0; i < node; i++) {
			if (myGraph.degree[i] % 2 != 0) {
				count++;
			}

		}
		if (count == 0) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

	public static void main(String[] args) {
		Main app = new Main();
		int x = scanner.nextInt();
		Graph myGraph = new Graph(x);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				int value = scanner.nextInt();

				if (value == 1) {
					myGraph.addDegree(i, j);
				}

			}
		}
		app.hasEulerPath(myGraph);
		app.hasEulerCircuit(myGraph);
	}

}

class Graph {
	int[] degree;
	int[][] adjArr;
	int node;

	public Graph(int node) {
		this.adjArr = new int[node][node];
		this.degree = new int[node];

	}

	public void addDegree(int u, int v) {
		adjArr[u][v] = adjArr[v][u] = 1;

		degree[u]++;
		degree[v]++;

	}
}
