import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static char[] wordList;
    static boolean[] isVisited;
    static int L;
    static int C;


    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(reader.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(reader.readLine());
        wordList = new char[C];
        isVisited = new boolean[C + 1];


        for (int i = 0; i < C; i++) {
            wordList[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(wordList);

        dfs(0, 0, 0, 0);
        writer.flush();
        writer.close();
    }

    public static void dfs(int currentIndex, int depth, int ja, int mo) throws IOException {
        if (depth == L && ja >= 2 && mo >= 1) {
            print();
            return;
        }

        for (int i = currentIndex; i < C; i++) {
            isVisited[i] = true;
            dfs(i+1, depth + 1, ja + (check(wordList[i]) ? 0 : 1), mo + (check(wordList[i]) ? 1 : 0));
            isVisited[i] = false;
        }
    }

    public static void print() throws IOException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < C; i++) {
            if (isVisited[i]) {
                builder.append(wordList[i]);
            }
        }
        builder.append("\n");
        writer.write(builder.toString());
    }

    public static boolean check(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return true;
        }
        return false;
    }
}

