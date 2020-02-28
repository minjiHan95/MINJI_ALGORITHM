import java.lang.*;
import java.util.*;



public class Main {

	public static final Scanner scanner = new Scanner(System.in);

	public int findMinimumVirus(int virus) {
		Virus myVirus = new Virus(1, 1);
		Queue<Virus> virusList = new LinkedList<>();
		boolean[] isVisited = new boolean[10001];
		int[] distance = new int[10001];
		virusList.add(myVirus);

		while (virusList.isEmpty() == false) {
			Virus currentIndex = virusList.poll();

			if (currentIndex.number > 10000) {
				continue;
			} else if (isVisited[currentIndex.number] ==true) {
				continue;
			} 
			
			distance[currentIndex.number] = currentIndex.depth;
			isVisited[currentIndex.number] = true;
			
			Virus leftVirus = new Virus(currentIndex.number * 2, currentIndex.depth + 1);
			Virus rightVirus = new Virus(currentIndex.number / 3, currentIndex.depth + 1);


			virusList.add(leftVirus);
			virusList.add(rightVirus);
		}
		return distance[virus]-1;
	}

	public static void main(String[] args) {
		Main app = new Main();
		int testCase = scanner.nextInt();
		for (int i = 0; i < testCase; i++) {
			int x = scanner.nextInt();
			int answer = app.findMinimumVirus(x);
			System.out.println(answer);
		}
	}
}

class Virus {
	int number;
	int depth;

	public Virus(int number, int depth) {
		this.number = number;
		this.depth = depth;
	}
}