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

	public void findShortestDay(int[][] tomato, int M, int N, ArrayList<Tomato> possibleTomato, int totalTomato) {
		int answer = 0;
		int totalCount = 0;
		boolean[][] isVisited = new boolean[M + 2][N + 2];

		int length = possibleTomato.size();
		Queue<Tomato> myTomato = new LinkedList<>();

		for (int i = 0; i < length; i++) {
			myTomato.add(possibleTomato.get(i));
		}

		while (myTomato.isEmpty() == false) {
			Tomato currentTomato = myTomato.poll();

			int i = currentTomato.row;
			int j = currentTomato.col;
			int state = currentTomato.state;

			if (isVisited[i][j] == true) {
				continue;
			}
			if (state == 0) {
				isVisited[i][j] = true;
				continue;
			} else if (i < 1 || j < 1 || i > M || j > N) {
				continue;
			}

			isVisited[i][j] = true;

			answer = currentTomato.depth;

			Tomato leftTomato = new Tomato(i - 1, j, tomato[i - 1][j] + state, currentTomato.depth + 1);
			Tomato rightTomato = new Tomato(i + 1, j, tomato[i + 1][j] + state, currentTomato.depth + 1);
			Tomato upTomato = new Tomato(i, j + 1, tomato[i][j + 1] + state, currentTomato.depth + 1);
			Tomato downTomato = new Tomato(i, j - 1, tomato[i][j - 1] + state, currentTomato.depth + 1);

			myTomato.add(leftTomato);
			myTomato.add(rightTomato);
			myTomato.add(upTomato);
			myTomato.add(downTomato);

			totalCount += state;

			if (totalCount != totalTomato) {
				answer = -1;
			}

		}
		System.out.println(answer-1);
	}

	public static void main(String[] args) {
		Main app = new Main();
		int M = scanner.nextInt();
		int N = scanner.nextInt();
		int[][] tomato = new int[N + 2][M + 2];

		int valueOfTomato;
		int totalTomato = M * N;
		ArrayList<Tomato> possibleTomato = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				valueOfTomato = scanner.nextInt();
				tomato[i][j] = valueOfTomato;

				if (valueOfTomato == -1) {
					totalTomato--;
				} else if (valueOfTomato == 1) {
					Tomato myTomato = new Tomato(i, j, 1, 1);
					possibleTomato.add(myTomato);
				}

			}
		}
		app.findShortestDay(tomato, N, M, possibleTomato, totalTomato);
	}
}

class Tomato {
	int row;
	int col;
	int state;
	int depth;

	public Tomato(int row, int col, int state, int depth) {
		this.row = row;
		this.col = col;
		this.state = state;
		this.depth = depth;
	}
}
