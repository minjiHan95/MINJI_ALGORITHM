
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static final Scanner scanner = new Scanner(System.in);

	public void testCase() {
		int n = scanner.nextInt();
		int[] arr = new int[n];
		Stack<Integer> height = new Stack<>();
		Stack<Integer> result = new Stack<>();

		height.push(1);
		result.push(n); // 초기값

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		for (int i = 0; i < n; i++) {
			height.pop();
			height.push(arr[i]);

			for (int j = i; j < n; j++) {

				if (height.peek() > arr[j]) {
					height.pop();
					height.push(arr[j]);

				}

				if (result.peek() < (j - i + 1) * height.peek()) {
					result.pop();
					result.push((j - i + 1) * height.peek());
				}
			}
		}
		System.out.println(result.peek());
	}

	public static void main(String[] args) {
		Main app = new Main();
		int testCase = scanner.nextInt();
		for (int i = 0; i < testCase; i++) {
			app.testCase();
		}
	}
}
