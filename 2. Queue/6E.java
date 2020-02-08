public class Main {

	public static final Scanner scanner = new Scanner(System.in);

	public void isBombRemovable(Bomb[] myBomb) {
		Queue<Bomb> removableBombs = new LinkedList<>();
		ArrayList<Bomb> removedBombs = new ArrayList<>();

		for (int i = 0; i < myBomb.length; i++) {
			if (myBomb[i].getChildBombs() == 0) {
				// 폭탄 제거해도 상관없는 폭탄들은 일단 넣어두기..
				removableBombs.add(myBomb[i]);
			}
			// 폭탄 제거 후 고려해야 할것
			// myBomb의 parentList를 참조하여 그들의 child수를 update해야함
		}

		// 제거가능한 폭탄이 하나도 남아있지 않을때까지 반복
		while (removableBombs.isEmpty() == false) {
			Bomb b = removableBombs.poll();
			b.remove();
			removedBombs.add(b);

			// 초기에 넣은 값이 중복으로 들어가면 안되기 때문에 parnet중에서 탐색
			// parent는 child가 1이상 있었기 때문에 들어갔을리 없

			ArrayList<Bomb> updateParent = b.getParentBombs();

			for (int i = 0; i < updateParent.size(); i++) {
				if (updateParent.get(i).getChildBombs() == 0) {
					removableBombs.add(updateParent.get(i));
				}
			}
		}
		if (removedBombs.size() == myBomb.length) {
			System.out.println("YES");
		} else {
			System.out.println("NO");

		}
	}

	public void testCase(int testIndex) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		Bomb[] myBomb = new Bomb[n];
		for (int i = 0; i < n; i++) {
			myBomb[i] = new Bomb(i);
		}

		for (int i = 0; i < m; i++) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;

			Bomb parent = myBomb[u];
			Bomb child = myBomb[v];

			child.addParentBombs(parent);
			parent.setChildBombs();

		}
		isBombRemovable(myBomb);
	}

	public static void main(String[] args) {
		Main app = new Main();
		int testCase = scanner.nextInt();
		for (int i = 0; i < testCase; i++) {
			app.testCase(i);
		}
	}

}

class Bomb {
	public final int index;
	private int childBomb;
	private ArrayList<Bomb> parentBombs;

	public Bomb(int index) {
		this.index = index;
		childBomb = 0;
		parentBombs = new ArrayList<Bomb>();
	}

	public void addParentBombs(Bomb parent) {
		this.parentBombs.add(parent);
	}

	public void setChildBombs() {
		childBomb++;
	}

	public int getChildBombs() {
		return childBomb;
	}

	public ArrayList<Bomb> getParentBombs() {
		return this.parentBombs;
	}

	public void remove() {
		for (int i = 0; i < parentBombs.size(); i++) {
			parentBombs.get(i).childBomb--;

		}
		this.childBomb--;
	}
}