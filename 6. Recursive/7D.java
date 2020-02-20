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
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		Main app = new Main();
		int x = scanner.nextInt();
		int[] arr = new int[x];
		for (int i = 0; i < x; i++) {
			arr[i] = scanner.nextInt();
		}
		Sorting sorting = new Sorting(arr);
		sorting.mergeSort(arr);

		for (int i = 0; i < x; i++) {
			
			writer.write(String.valueOf(arr[i]));
			if (x > 0) {
				writer.write(" ");
			}
		}
		writer.flush();
		writer.close();

	}
}

class Sorting {

	int [] arr;
	public Sorting (int [] arr) {
		this.arr = arr;
		mergeSort(arr);
	}
	
	public void mergeSort(int[] arr) {
		int n = arr.length;
		int[] buffer = new int[n];
		mergeSort(arr, 0, n-1, buffer);
	}

	public void mergeSort(int[] arr, int left, int right, int[] buffer) {
		if(left>=right) {
			return;
		}
		
		int mid = (left + right) / 2;
		mergeSort(arr, left, mid, buffer);
		mergeSort(arr, mid + 1, right, buffer);

		makeOrderedArr(arr, left, mid, right, buffer);
	}

	public void makeOrderedArr(int[] arr, int left, int mid, int right, int[] buffer) {
		int i = left;
		int j = mid + 1;
		int k = left;

		while (i <= mid && j <= right) {
			if (arr[i] > arr[j]) {
				buffer[k++] = arr[j++];
			} else {
				buffer[k++] = arr[i++];
			}
		}

		while (i <= mid) {
			buffer[k++] = arr[i++];
		}

		while (j <= right) {
			buffer[k++] = arr[j++];
		}

		for (int index = left; index <= right; index++) {
			arr[index] = buffer[index];
		}

	}

}