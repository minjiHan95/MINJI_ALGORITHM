import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 아까와의 차이점: 순서를 지정해줘야함
 
   우선순위 큐: 우선순위가 가장 높은 데이터가 가장 먼저 나오는 자료구조   
   힙을 이용하여 구현
   데이터를 삽입할 때 우선순위를 기준으로 최대힙 혹은 최소힙을 구성하고, 데이터를 꺼낼때 루트노드를 얻는다
   루트노드를 삭제할 때는 빈 루트 노드 위치에 맨 마지막 노드를 삽입한 후 아래로 내려가면서 적절한 자리를 찾아서 옮김
   
   이 예제에서 우선순위 큐가 필요한 이유
   	-> 폭탄을 제거할 때 우선순위가 높은(즉, 제일 작은 수의 폭탄을 삽입한 후) 데이터의 변형이 필요한
   
   
   우선순위를 정하는 기준은 Java의 정렬기준과 동일하다. Java는 기본적으로 낮은 숫자부터 큰 숫자까지 오름차순으로 정렬하게 된다.
   즉, 우선순위가 한 가지의 속성으로만 결정될 필요는 없다. 새로운 속성값을 추가한 후 Comparable, Comparator 를 구현하여 우선순위 결정
   
*/

public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public void isRemovedBombs(int n, Bomb[] myBomb) throws Exception {
		ArrayList<Bomb> removedBombs = new ArrayList<>();
		PriorityQueue<Bomb> removableBombs = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			if (myBomb[i].getChildCount() == 0) {
				removableBombs.add(myBomb[i]);
			}
		}
		while (removableBombs.isEmpty() == false) {
			Bomb b = removableBombs.poll();
			removedBombs.add(b);
			b.remove();

			ArrayList<Bomb> newParent = b.getParnetBombs();

			for (int j = 0; j < newParent.size(); j++) {
				if (newParent.get(j).getChildCount() == 0) {
					removableBombs.add(newParent.get(j));
				}
			}

		}
		if (removedBombs.size() == n) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < n; i++) {
				Bomb bomb = removedBombs.get(i);
				if (i > 0) {
					builder.append(" ");
				}
				builder.append(bomb.index);
			}
			builder.append("\n");

			writer.write(builder.toString());
		} else {
			writer.write("NO\n");
		}

	}

	public void testCase(int testIndex) throws Exception {
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		Bomb[] myBomb = new Bomb[n];
		for (int i = 0; i < n; i++) {
			myBomb[i] = new Bomb(i + 1);
		}
		for (int i = 0; i < m; i++) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;

			Bomb parent = myBomb[u];
			Bomb child = myBomb[v];

			child.addParentBombs(parent);
			parent.addChildCount();
		}
		isRemovedBombs(n, myBomb);

	}

	public static void main(String[] args) throws Exception {
		Main app = new Main();
		int testCase = scanner.nextInt();
		for (int i = 0; i < testCase; i++) {
			app.testCase(i);
		}
		writer.flush();
		writer.close();
	}
}

class Bomb implements Comparable<Bomb> {
	public final int index;
	private ArrayList<Bomb> parentBombs;
	private int childCount;

	public Bomb(int index) {
		this.index = index;
		this.childCount = 0;
		this.parentBombs = new ArrayList<>();
	}

	public void addParentBombs(Bomb parent) {
		this.parentBombs.add(parent);
	}

	public void addChildCount() {
		this.childCount++;
	}

	public int getChildCount() {
		return this.childCount;
	}

	public ArrayList<Bomb> getParnetBombs() {
		return this.parentBombs;
	}

	public void remove() {
		int length = this.parentBombs.size();
		for (int i = 0; i < length; i++) {
			this.parentBombs.get(i).childCount--;
		}
	}

	// 비교대상이 무엇인가!
	@Override
	public int compareTo(Bomb target) {
		if (this.index > target.index) {
			return 1;
		} else if (this.index < target.index) {
			return -1;
		} else {
			return 0;
		}
	}
}