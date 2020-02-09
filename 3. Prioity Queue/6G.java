package proj_ex1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public void findLargeIncome(int n, int m, City[] myCity) {
		
		PriorityQueue<Integer> result = new PriorityQueue<>(Collections.reverseOrder());
		
		PriorityQueue<City> rangeMinimum = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<City> rangeMaximum = new PriorityQueue<>();
		
		for(int i=0; i<=n-m; i++) {
			for(int j=i; j<i+m; j++) {
				rangeMinimum.add(myCity[j]);
				rangeMaximum.add(myCity[j]);
			}
			result.add(rangeMaximum.poll().income-rangeMinimum.poll().income);	
			
			for(int j=0; j<m-1; j++) {
				rangeMinimum.poll();
				rangeMaximum.poll();
			}
		}
		System.out.println(result.peek());
	}
	
	public void testCase(int testIndex) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		City[] myCity = new City[n];
		for(int i=0; i<n; i++) {
			int income = scanner.nextInt();
			myCity[i]= new City(i, income);
		}
		findLargeIncome(n, m, myCity);
	}

	public static void main(String[] args) {
		Main app = new Main();
		int testCase = scanner.nextInt();
		for (int i = 0; i < testCase; i++) {
			app.testCase(i);
		}
	}
}

class City implements Comparable<City> {
	int index;
	int income;

	public City(int index, int income) {
		this.income = income;
		this.index = index;
	}

	@Override
	public int compareTo(City target) {
		if (this.income < target.income)
			return 1;
		else if (this.income > target.income)
			return -1;
		else
			return 0;
	}
}