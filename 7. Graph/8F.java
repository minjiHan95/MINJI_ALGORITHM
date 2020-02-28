import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static final Scanner scanner = new Scanner(System.in);

	public void findMinimumPath(boolean[][] miro, int col, int row, int i, int j, int desCol, int desRow) {

		State startMiro = new State(i, j, 1);
		boolean[][] isVisited = new boolean[col+2][row+2];
		isVisited[i][j] = false;
		int answer = findMinimumPath(miro, col, row, startMiro, isVisited, desCol, desRow);
		System.out.println(answer-1);

	}

	public int findMinimumPath(boolean[][] miro, int col, int row, State myMiro, boolean[][] isVisited, int desCol, int desRow) {
		int[][] distance = new int[col+2][row+2];

		Queue<State> miroList = new LinkedList<>();
		miroList.add(myMiro);

		while (miroList.isEmpty() == false) {
			State currentMiro = miroList.poll();
		
			int i = currentMiro.col;
			int j = currentMiro.row;
			
			if(i<1 || j<1 || i>col || j>row) {
				continue;
			}
			else if(isVisited[i][j]==true || miro[i][j]==false) {
				continue;
			}
			
			distance[i][j] = currentMiro.disatance;
			isVisited[i][j] = true;


			State leftState = new State(i - 1, j, currentMiro.disatance+1);
			State rightState = new State(i + 1, j, currentMiro.disatance+1);
			State upState = new State(i, j + 1, currentMiro.disatance+1);
			State downState = new State(i, j - 1, currentMiro.disatance+1);

			miroList.add(leftState);
			miroList.add(rightState);
			miroList.add(upState);
			miroList.add(downState);

		}

		return distance[desCol][desRow];
	}

	public static void main(String[] args) {
		Main app = new Main();

		int col = scanner.nextInt();
		int row = scanner.nextInt();

		int startCol = 0, startRow = 0;
		int desCol = 0, desRow = 0;
		boolean[][] miro = new boolean[col + 2][row + 2];

		for (int i = 1; i <= col; i++) {
			String s = scanner.next();

			for (int j = 1; j <= row; j++) {
				char c = s.charAt(j-1);

				if (c == 'S') {
					miro[i][j] = true;
					startCol = i;
					startRow = j;

				} else if (c == '-') {
					miro[i][j] = true;
				} else if (c == '#') {
					miro[i][j] = false;
				} else if (c == 'E') {
					miro[i][j] = true;
					desCol = i;
					desRow = j;
				}

			}
		}
		app.findMinimumPath(miro, col, row, startCol, startRow, desCol, desRow);

	}
}

class State {
	int disatance;
	int col, row;

	public State(int col, int row, int disatance) {
		this.col = col;
		this.row = row;
		this.disatance = disatance;
	}

}