import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        Main app = new Main();

        int k = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        boolean[][] isAvailable = new boolean[row + 1][col + 1];


        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < col; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == 0) {
                    isAvailable[i][j] = true;
                }
            }
        }

        if (row == 1 && col == 1 && isAvailable[0][0]) {
            writer.write(String.valueOf(0));
        } else {
            int answer = app.findMinimumPath(isAvailable, k);

            if (answer == 0) {
                writer.write(String.valueOf(-1));
            } else {
                writer.write(String.valueOf(answer));
            }
        }
        writer.flush();
        writer.close();
    }

    private int findMinimumPath(boolean[][] isAvailable, int k) {
        int row = isAvailable.length - 1;
        int col = isAvailable[0].length - 1;
        boolean[][][] isVisited = new boolean[row + 1][col + 1][k + 1];
        int[][] distance = new int[row][col];

        Queue<State> availableState = new LinkedList<>();
        State initialState = new State(0, 0, 0, k);
        availableState.add(initialState);

        while (!availableState.isEmpty()) {
            State current = availableState.poll();
            int i = current.row;
            int j = current.col;

            if (i < 0 || j < 0 || i >= row || j >= col) {
                continue;
            } else if (isVisited[i][j][current.remainingHorse] || !isAvailable[i][j]) {
                continue;
            }

            isVisited[i][j][current.remainingHorse] = true;
            if (distance[i][j] != 0) {
                distance[i][j] = Math.min(distance[i][j], current.distance);
            } else {
                distance[i][j] = current.distance;
            }

            if (current.remainingHorse > 0) {
                availableState.add(new State(i - 1, j - 2, current.distance + 1, current.remainingHorse - 1));
                availableState.add(new State(i - 1, j + 2, current.distance + 1, current.remainingHorse - 1));
                availableState.add(new State(i - 2, j - 1, current.distance + 1, current.remainingHorse - 1));
                availableState.add(new State(i - 2, j + 1, current.distance + 1, current.remainingHorse - 1));
                availableState.add(new State(i + 1, j - 2, current.distance + 1, current.remainingHorse - 1));
                availableState.add(new State(i + 1, j + 2, current.distance + 1, current.remainingHorse - 1));
                availableState.add(new State(i + 2, j - 1, current.distance + 1, current.remainingHorse - 1));
                availableState.add(new State(i + 2, j + 1, current.distance + 1, current.remainingHorse - 1));
            }
            availableState.add(new State(i + 1, j, current.distance + 1, current.remainingHorse));
            availableState.add(new State(i - 1, j, current.distance + 1, current.remainingHorse));
            availableState.add(new State(i, j - 1, current.distance + 1, current.remainingHorse));
            availableState.add(new State(i, j + 1, current.distance + 1, current.remainingHorse));
        }
        return distance[row - 1][col - 1];
    }
}

class State {
    int row, col;
    int distance;
    int remainingHorse;

    public State(int row, int col, int distance, int remainingHorse) {
        this.row = row;
        this.col = col;
        this.distance = distance;
        this.remainingHorse = remainingHorse;
    }
}