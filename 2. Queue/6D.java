/*
자료구조: Linked List 대 ArrayList
Linked List
데이터들이 순서대로 늘어선 것이 아니라 자료의 주소값으로 서로 연결

ArrayList
데이터들이 순서대로 쭉 늘어선 배열의 형식
 */

public class Main {

	public static final Scanner scanner = new Scanner(System.in);

	public ArrayList<Player> getDeadPlayerList(int n, int m, Player[] player) {
		ArrayList<Player> deadPlayer = new ArrayList<>();
		Queue<Player> playingPlayer = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			playingPlayer.add(player[i]);
		}

		for (int i = 0; i < n; i++) {
			int jump = 1 + (m - 1) % playingPlayer.size();
			for (int j = 0; j < jump-1; j++) {
				Player p = playingPlayer.poll();
				playingPlayer.add(p);
			}
			Player dead = playingPlayer.poll();
			deadPlayer.add(dead);
		}

		return deadPlayer;
	}

	public void testCase(int caseIndex) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		Player[] playerArr = new Player[n];
		for (int i = 0; i < n; i++) {
			playerArr[i] = new Player(i + 1);
		}

		ArrayList<Player> deadPlayerList = getDeadPlayerList(n, m, playerArr);
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < n ; i ++){
			if( i > 0 ){
				builder.append(" ");
			}

			Player p = deadPlayerList.get(i);
			builder.append(p.index);
		}
		// 정답을 출력한다
		System.out.println(builder.toString());
	}

	public static void main(String[] args) {
		Main app = new Main();
		int testCase = scanner.nextInt();

		for (int i = 0; i < testCase; i++) {
			app.testCase(i);
		}
	}
}

class Player {
	public final int index;

	public Player(int index) {
		this.index = index;
	}
}