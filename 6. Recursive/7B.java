public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public int findMininumCount(int x, Stack<Disk> A, Stack<Disk> B, Stack<Disk> C) {
		int answer = 0;
		if (x == 0) {
			return 0;
		} else if (x == 1) {
			return 1;
		}
		answer += findMininumCount(x - 1, A, C, B);
		answer += findMininumCount(1, A, B, C);
		answer += findMininumCount(x - 1, B, A, C);

		return answer;
	}

	public static void main(String[] args) {
		Main app = new Main();
		int x = scanner.nextInt();

		Stack<Disk> stack1 = new Stack<>();
		Stack<Disk> stack2 = new Stack<>();
		Stack<Disk> stack3 = new Stack<>();

		for (int i = x; i > 0; i--) {
			Disk d = new Disk(i);
			stack1.push(d);
		}

		int answer = app.findMininumCount(x, stack1, stack2, stack3);
		System.out.println(answer);

	}
}

class Disk {
	public final int height;

	Disk(int height) {
		this.height = height;
	}
}