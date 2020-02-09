package proj_ex1;

import java.util.Scanner;
import java.util.TreeSet;

/*
  [Set]
  : Set이란, 요소의 순서를 상관하지 않고 "중복되지 않게 저장"하는 자료구조
  1. HashSet(해시셋)
  	:저장되는 데이터의 순서를 파악할 수 없다
  	:Set에서 가장 빠른 탐색 속도를 가진다.
  	:제네릭을 이용하여 저장 데이터를 결정한다.
  	->hashCode, equals 메서드를 이용하여 오버라이딩 한다.
  	
  2. TreeSet(트리셋)
  	:저장되는 데이터의 순서를 파악할 수 있다
  	:HashSet과 마찬가지로 중복저장은 불가하다.
  	:트리셋이 데이터를 탐색하는 방법은 "이진탐색트리"
  		//이진탐색트리(Binary Search Tree) : 이진트리를 사용한 자료구조로 키값에 따라 노드의 위치를 정의한 탐색트리
		 1) 모든 노드는 유일한 키값을 갖는다.
		 2) 왼쪽 노드는 루트의 키보다 작다.
		 3) 오른쪽 노드는 루트의 키보다 크다.
		 4) 모든 서브 트리도 이진탐색트리이다.
  	:제네릭을 이용하여 저장 데이터를 결정한다.
  	->CompareTo 메서드를 이용하여 오버라이딩 한다.	
	
  
*/

public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public void isduplicatedNum(int n, duplicatedNum[] myNum) {
		TreeSet<Integer> mySet = new TreeSet<>();
		mySet.add(myNum[0].getNumValue());
		for (int i = 1; i < n; i++) {
			if(mySet.contains(myNum[i].getNumValue())) {
				myNum[i].setDuplicated();
			}
			else {
				mySet.add(myNum[i].getNumValue());
			}
		}
		for(int i=0; i<n; i++) {
			if(myNum[i].getDuplicated()) {
				System.out.println("DUPLICATED");
			}
			else {
				System.out.println("OK");
			}
		}
	}

	public static void main(String[] args) {
		Main app = new Main();
		int n = scanner.nextInt();
		duplicatedNum[] myNum = new duplicatedNum[n];
		for (int i = 0; i < n; i++) {
			int num = scanner.nextInt();
			myNum[i] = new duplicatedNum(i, num);
		}
		app.isduplicatedNum(n, myNum);
	}
}

class duplicatedNum implements Comparable {
	public final int index;
	private int value;
	private boolean isDuplicated;

	public duplicatedNum(int index, int value) {
		this.index = index;
		this.value = value;
		this.isDuplicated = false;
	}

	public int getNumValue() {
		return value;
	}

	public void setDuplicated() {
		this.isDuplicated = true;
	}
	
	public boolean getDuplicated() {
		return this.isDuplicated;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}