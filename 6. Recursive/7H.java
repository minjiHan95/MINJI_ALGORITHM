import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public int getDistance(Point a, Point b) {
		int xDistance = b.x - a.x;
		int yDistance = b.y - a.y;

		return xDistance * xDistance + yDistance * yDistance;
	}

	public int findClosetPair(Point[] myPoint, int left, int right) {
			
		if (left >= right) {
			return Integer.MAX_VALUE;
		}

		int minDistance;
		int distance;
		
		int mid = (left + right) / 2;
		
		minDistance = getDistance(myPoint[left], myPoint[right]);
		

		int leftMin, rightMin;
		
		leftMin = findClosetPair(myPoint, left, mid);
		rightMin = findClosetPair(myPoint, mid + 1, right);
		
		minDistance = Math.min(minDistance, leftMin);
		minDistance = Math.min(minDistance, rightMin);
		
		
		distance = myPoint[mid+1].x - myPoint[mid].x;
		
		if(distance<minDistance) {
			minDistance = Math.min(minDistance, getDistance(myPoint[mid+1], myPoint[mid]));
		}
		
		//System.out.print(left+" "+right+" "+minDistance+"\n");
		
		return minDistance;
	}

	public static void main(String[] args) {
		Main app = new Main();
		int testCase = scanner.nextInt();
		
		if(testCase==1) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			System.out.println("0");

		}
		else {
			Point[] myPoint = new Point[testCase];
			for (int i = 0; i < testCase; i++) {
				int x = scanner.nextInt();
				int y = scanner.nextInt();

				myPoint[i] = new Point(x, y);
			}
			Arrays.sort(myPoint);

			int answer = app.findClosetPair(myPoint, 0, testCase - 1);
			System.out.println(answer);
		}

	}
}

class Point implements Comparable<Point> {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point other) {
		// TODO Auto-generated method stub
		if (this.x > other.x) {
			return 1;
		} else if (this.x < other.x) {
			return -1;
		} else {
			return this.y - other.y;
		}
	}
}
