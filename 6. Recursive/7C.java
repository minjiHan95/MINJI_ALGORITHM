package proj_ex1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public int getLinkedGroup(int k, int i, int j, boolean[][] isHouse, boolean[][] isVisited) {
		if (i <= 0 || i > k || j <= 0 || j > k) {
			return 0;
		}
		if(!isHouse[i][j] || isVisited[i][j]) {
			return 0;
		}
			
		isVisited[i][j] = true;

		int count = 1;

		count += getLinkedGroup(k, i + 1, j, isHouse, isVisited); 
		count += getLinkedGroup(k, i - 1, j, isHouse, isVisited);
		count += getLinkedGroup(k, i, j + 1, isHouse, isVisited);
		count += getLinkedGroup(k, i, j - 1, isHouse, isVisited);

		return count;

	}

	public ArrayList<Integer> getGroupNum(int k, boolean[][] isHouse) {
		boolean[][] isVisited = new boolean[k + 2][k + 2];
		ArrayList<Integer> groupSize = new ArrayList<>();

		for (int i = 1; i < k + 1; i++) {
			for (int j = 1; j < k + 1; j++) {
				if (isHouse[i][j] && !isVisited[i][j]) {
					int answer = getLinkedGroup(k, i, j, isHouse, isVisited);
					groupSize.add(answer);
				}
			}
		}
		Collections.sort(groupSize);
		return groupSize;
	}

	public static void main(String[] args) {
		Main app = new Main();
		int k = scanner.nextInt();
		boolean[][] isHouse = new boolean[k + 2][k + 2];

		for (int i = 1; i < k + 1; i++) {
			for (int j = 1; j < k + 1; j++) {
				int value = scanner.nextInt();
				if (value == 1) {
					isHouse[i][j] = true;
				}
			}
		}
		ArrayList<Integer> answer = app.getGroupNum(k, isHouse);
		System.out.println(answer.size());
		for(int i=0; i<answer.size(); i++) {
			System.out.println(answer.get(i));
		}
	}
}