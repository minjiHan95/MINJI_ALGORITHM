import java.io.*;
import java.util.*;

public class Main {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        Main app = new Main();
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int answer = app.findMinimumCount(N, M);
        writer.write(String.valueOf(answer));
        writer.flush();
        writer.close();
    }

    private int findMinimumCount(int n, int m) {
        if (m == 0 || n == 0) {
            return 0;
        }

        int sausage = n;
        while (sausage > m) {
            sausage = sausage % m;
        }
        int count = ((m - 1) / n) * n;
        int restPerson = m % n;

        count += findMinimumCount(sausage, restPerson);
        return count;
    }

}