package algorithm;

import java.io.*;
import java.util.*;

/*
 인접한 그래프인지 실시간으로 변하는 데이터를 받아 처리
 
 parseInt: 문자열을 입력받아 정수를 반환해주는 내장 함
 */

public class Main {

	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static String[] readLine() throws Exception {
		return reader.readLine().split(" ");
	}

	public boolean isConnected(int from, int to, Graph myGraph) {
		boolean connected = false;

		int depth = myGraph.node;
		boolean[] isVisited = new boolean[depth + 1];

		int initialNode = from;
		int visitedcount = 0;

		Queue<Integer> connectedGraph = new LinkedList<>();
		connectedGraph.add(initialNode);

		while (connectedGraph.isEmpty() == false) {
			int currentNode = connectedGraph.poll();

			if (isVisited[currentNode]) {
				continue;
			}
			if (visitedcount >= depth) {
				continue;
			}
			if (currentNode == to) {
				return true;
			}

			isVisited[currentNode] = true;

			for (int i = 0; i < depth; i++) {
				if (myGraph.myGraph[currentNode][i]) {
					connectedGraph.add(i);
				}
			}

		}

		return connected;
	}

	public static void main(String[] args) throws Exception {
		Main app = new Main();
		int node = scanner.nextInt();
		int testCase = scanner.nextInt();

		Graph myGraph = new Graph(node);

		for (int i = 0; i < testCase; i++) {
			String[] command = readLine();

			int u = Integer.parseInt(command[1]);
			int v = Integer.parseInt(command[2]);

			if (command[0].equals("LINK")) {
				myGraph.addNode(u, v);
				int sum = myGraph.degree[u]+ myGraph.degree[v];
				System.out.println("SIZE = {"+sum+"}");
				
			} else if (command[0].equals("CHECK")) {
				if(app.isConnected(u, v, myGraph)) {
					System.out.println("Connected");
				}
				else {
					System.out.println("Separated");
				}
			}
		}

	}
}

class Graph {
	int node;
	int [] degree;
	boolean[][] myGraph;

	public Graph(int node) {
		this.node = node;
		myGraph = new boolean[node + 1][node + 1];
		degree = new int[node];
	}

	public void addNode(int u, int v) {
		myGraph[u][v] = myGraph[v][u] = true;
		
		degree[u]++;
		degree[v]++;
				
	}
}
