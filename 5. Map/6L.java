package proj_ex1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public int[] mergeArr(int[] arr1, int[] arr2) {
		int length1 = arr1.length;
		int length2 = arr2.length;

		int[] newArr = new int[length1 + length2];
		for (int i = 0; i < length1; i++) {
			newArr[i] = arr1[i];
		}
		for (int i = length1; i < length1 + length2; i++) {
			newArr[i] = arr2[i - length1];
		}
		for (int i = 0; i < length1 + length2 - 1; i++) {
			for (int j = 0; j < length1 + length2 - i-1; j++) {
				if (newArr[j] > newArr[j + 1]) {
					int temp = newArr[j];
					newArr[j] = newArr[j + 1];
					newArr[j + 1] = temp;
				}
			}
			
		}
		return newArr;

	}

	public static void main(String[] args) {
		Main app = new Main();
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int[] firstArr = new int[n];
		int[] secondArr = new int[m];

		for (int i = 0; i < n; i++) {
			firstArr[i] = scanner.nextInt();
		}
		for (int i = 0; i < m; i++) {
			secondArr[i] = scanner.nextInt();
		}
		int[] newArr = app.mergeArr(firstArr, secondArr);
		for (int i = 0; i < m + n; i++) {
			System.out.print(newArr[i]+" ");
		}
		System.out.println();

	}
}
