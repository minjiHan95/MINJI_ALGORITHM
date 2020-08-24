import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static char[] wordList;
    static boolean[] isVisited;
    static int n;
    static int m;


    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boolean[][] input = new boolean[n + 1][m + 1];
        int[][] result = new int[n][m];
        int answer = 0;

        //input
        for (int i = 0; i < n; i++) {
            String str = reader.readLine();
            for (int j = 0; j < m; j++) {
                if (str.charAt(j) == '1') {
                    input[i][j] = true;
                    result[i][j] = 1;
                    answer = 1;
                }
            }
        }


        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (input[i][j]) {
                    result[i][j] = Math.min(result[i - 1][j], Math.min(result[i - 1][j - 1], result[i][j - 1])) + 1;
                    answer = Math.max(answer, result[i][j]);
                }
            }
        }

        writer.write(String.valueOf(answer * answer));
        writer.flush();
        writer.close();
    }
}

