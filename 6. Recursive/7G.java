package proj_ex1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/*
원래 짠 코드의 문제... 탐색을 n^2으로 하게 되서 타임아웃 발생함...
어떻게 고칠까....
*/

//public class Main {
//	public static final Scanner scanner = new Scanner(System.in);
//
//	public int findMinNumber (int [] arr, int n) {
//		int count=0;
//		for(int i=0; i<n-1; i++) {
//			for(int j=i+1; j<n; j++) {
//				if(arr[i]>arr[j]) {
//					count++;
//				}
//			}
//		}
//		return count;
//	}
//	
//	public static void main(String[] args) {
//		Main app = new Main();
//		int n = scanner.nextInt();
//		int [] arr = new int [n];
//		for(int i=0; i<n; i++) {
//			arr[i] = scanner.nextInt();
//		}
//		int answer = app.findMinNumber(arr, n);
//		System.out.println(answer);
//	}
//}

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		int n = scanner.nextInt();
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = scanner.nextInt();
		}
		
		Sorting sorting = new Sorting(arr);
		System.out.println(sorting.count);


	}
}

class Sorting {
	long count = 0;

	public Sorting(int[] arr) {
		int N = arr.length;
		int[] buffer = new int[N];
		mergeSort(0, N-1, arr, buffer);
	}

	public void mergeSort(int left, int right, int[] arr, int[] buffer) {

		if (left >= right) {
			return;
		}

		int mid = (left + right) / 2;
		mergeSort(left, mid, arr, buffer);
		mergeSort(mid + 1, right, arr, buffer);


		makeOrderedArr(left, right, mid, arr, buffer);

	}

	public void makeOrderedArr(int left, int right, int mid, int[] arr, int[] buffer) {
		int i = left;
		
		int j = mid + 1;
		int k = left;
		
		while (i <= mid && j <= right) {
			if(arr[i]>arr[j]) {
				buffer[k++] = arr[j++];
				this.count +=mid-i+1;

			}
			else {
				buffer[k++] = arr[i++];
			}
		}
		while (i <= mid) {
			buffer[k++] = arr[i++];

		}
		while (j <= right) {
			buffer[k++] = arr[j++];

		}
		for(int index = left; index<=right; index++) {
			arr[index] = buffer[index];
		}

	}
}