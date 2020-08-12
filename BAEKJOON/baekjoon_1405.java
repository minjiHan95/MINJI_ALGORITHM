import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    int[][] moveDir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    double answer = 0.0;

    public static void main(String[] args) throws Exception {
        Main app = new Main();
        double[] probability = new double[4];
        boolean[][] isVisited = new boolean[30][30];

        StringTokenizer st = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            probability[i] = Integer.parseInt(st.nextToken()) / 100.0;
        }

        app.findProbability(15, 15, N, 1.00, probability, isVisited);

        writer.write(String.valueOf(app.answer));
        writer.flush();
        writer.close();
    }

    public void findProbability(int i, int j, int depth, double chance, double[] probability, boolean[][] isVisited) {
        if (depth == 0) {
            answer += chance;
            return;
        }
        isVisited[i][j] = true;
        for (int index = 0; index < 4; index++) {
            int nextX = i + moveDir[index][0];
            int nextY = j + moveDir[index][1];

            if (!isVisited[nextX][nextY]) {
                findProbability(nextX, nextY, depth - 1, chance * probability[index], probability, isVisited);
                isVisited[nextX][nextY] = false;
            }
        }
    }
}