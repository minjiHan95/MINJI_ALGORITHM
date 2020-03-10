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

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public int findLongestLength(int from, int to, ArrayList<Integer> [] myList, boolean [] isVisited) {
		if(from==to || isVisited[from]==true) {
			return 0;
		}
		
		int length = myList.length;
		
		ArrayList<Integer> [] buffer = new ArrayList[length];
		isVisited[from] = true;
		
		int minLength = Integer.MAX_VALUE;
		
		for(int nextIndex : myList[from]) {
			minLength = Math.min(findLongestLength(nextIndex, to, myList, isVisited)+1, minLength);
		}
		isVisited[from] = false;
		return minLength;
	}
	
	public static void main(String[] args) {
		Main app = new Main();
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		
		int from = scanner.nextInt();
		int to = scanner.nextInt();
		
		ArrayList<Integer> [] myList = new ArrayList[N+1];
		boolean [] isVisited = new boolean[N+1];

		for(int i=1; i<N+1; i++) {
			myList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			
			myList[u].add(v);
			myList[v].add(u);
		}
		int answer = app.findLongestLength(from, to, myList, isVisited);
		System.out.println(answer);
	}
}