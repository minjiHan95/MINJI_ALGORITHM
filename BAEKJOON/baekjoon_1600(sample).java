import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        Main app = new Main();

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        Miro miro = new Miro(row, col);

        for (int i = 0; i < row; i++) {
            String temp = reader.readLine();
            for (int j = 0; j < col; j++) {
                if (temp.charAt(j) == 'S') {
                    miro.setIsAvailable(i, j);
                    miro.setStartPoint(i, j);
                } else if (temp.charAt(j) == '-') {
                    miro.setIsAvailable(i, j);
                } else if (temp.charAt(j) == 'E') {
                    miro.setIsAvailable(i, j);
                    miro.setDesPoint(i, j);
                }
            }
        }
        int answer = app.findMinimumPath(miro);
        writer.write(String.valueOf(answer));
        writer.flush();
        writer.close();
    }

    private int findMinimumPath(Miro miro) {
        Queue<State> availablePath = new LinkedList<>();
        int[][] distance = new int[miro.row][miro.col];
        State initialState = new State(miro.getStartPoint()[0], miro.getStartPoint()[1], 0);
        availablePath.add(initialState);

        while (!availablePath.isEmpty()) {
            State current = availablePath.poll();
            int i = current.row;
            int j = current.col;

            if (i < 0 || j < 0 || i > miro.row || j > miro.col) {
                continue;
            } else if (miro.isVisited[i][j] || !miro.isAvailable[i][j]) {
                continue;
            }

            miro.isVisited[i][j] = true;
            distance[i][j] = current.distance;

            availablePath.add(new State(i - 1, j, current.distance + 1));
            availablePath.add(new State(i + 1, j, current.distance + 1));
            availablePath.add(new State(i, j - 1, current.distance + 1));
            availablePath.add(new State(i, j + 1, current.distance + 1));
        }


        return distance[miro.getDesPoint()[0]][miro.getDesPoint()[1]];
    }
}

class State {
    int row, col;
    int distance;

    public State(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
}

class Miro {
    private int startRow;
    private int startCol;
    private int desRow;
    private int desCol;

    int row;
    int col;

    boolean[][] isAvailable;
    boolean[][] isVisited;

    public Miro(int Row, int Col) {
        this.row = Row;
        this.col = Col;
        isAvailable = new boolean[Row + 1][Col + 1];
        isVisited = new boolean[Row + 1][Col + 1];
    }

    public void setStartPoint(int startRow, int startCol) {
        this.startRow = startRow;
        this.startCol = startCol;
    }

    public void setDesPoint(int desRow, int desCol) {
        this.desRow = desRow;
        this.desCol = desCol;
    }

    public int[] getStartPoint() {
        int[] answer = {startRow, startCol};
        return answer;
    }

    public int[] getDesPoint() {
        int[] answer = {desRow, desCol};
        return answer;
    }

    public void setIsAvailable(int row, int col) {
        isAvailable[row][col] = true;
    }

    public void setIsVisited(int row, int col) {
        isVisited[row][col] = true;
    }

}