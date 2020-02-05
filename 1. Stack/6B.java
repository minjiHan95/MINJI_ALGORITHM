
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public void findTargetTower(int n, Tower[] tower) {
		Stack<Tower> towerStack = new Stack<>();

		for (int i = 0; i < n; i++) {
			Tower t = tower[i];
			Tower targetTower = null;

			while (towerStack.isEmpty() == false && towerStack.peek().height < t.height) {
				towerStack.pop();
			}

			if (towerStack.size() > 0) {
				targetTower = towerStack.peek();
			}

			t.setTargetTower(targetTower);

			towerStack.push(t);
		}
	}

	public void testCase(int testCase) throws Exception {
		Tower[] myTower = new Tower[testCase];
		for (int i = 0; i < testCase; i++) {
			int towerHeight = scanner.nextInt();
			myTower[i] = new Tower(i + 1, towerHeight);
		}
		findTargetTower(testCase, myTower);

		for (int i = 0; i < testCase; i++) {

			if (i > 0) {
				writer.write(" ");
			}
			Tower t = myTower[i];

			if (t.getTargetTower() == null) {
				writer.write("0");
			} else {
				int targetIndex = t.getTargetTower().index;
				writer.write(String.valueOf(targetIndex));
			}
		}
		writer.flush();
		writer.close();

	}

	public static void main(String[] args) throws Exception {
		Main app = new Main();
		int testCase = scanner.nextInt();
		app.testCase(testCase);
	}
}

class Tower {
	public int index;
	public int height;

	private Tower targetTower;

	public Tower(int index, int height) {
		this.index = index;
		this.height = height;
		this.targetTower = null;
	}

	public void setTargetTower(Tower target) {
		this.targetTower = target;
	}

	public Tower getTargetTower() {
		return targetTower;
	}

}
