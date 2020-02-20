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
		
		for(int i=0; i<x; i++) {
			if(i>0) {
				writer.write(" ");
			}
			writer.write(String.valueOf(arr[i]));
		}
		writer.flush();
		writer.close();
	}

}

class Sorting {
	public Sorting(int[] arr) {
		
		for(int i = 0 ; i < arr.length - 1 ; i++) {
			if(arr[i] > arr[i + 1])
				break;
			
			if(i == arr.length - 1)
				return;
		}
		
		quickSort(0, arr.length, arr);
	}


	public void quickSort(int left, int right, int[] arr) {
		if(left>=right) {
			return;
		}
	
		
		int pivot = arr[left];
		int s = left;
		int i;
		
		int temp;
		
		//피봇보다 작은값
		for(i = left+1; i<right; i++) {
			if(pivot>arr[i]) {
				s++;
				temp = arr[s];
				arr[s] = arr[i];
				arr[i] = temp;
			}		
		}
		
		temp = arr[s];
		arr[s] = arr[left];
		arr[left] = temp;
		
		quickSort(left, s, arr);
		quickSort(s+1, right, arr);
		
	}
}