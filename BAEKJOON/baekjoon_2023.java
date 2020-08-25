import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());

        int[] prime = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            dfs(prime[i], n - 1);
        }

        writer.write(String.valueOf(builder));
        writer.flush();
        writer.close();
    }

    private static void dfs(int number, int depth) {
        if (depth == 0) builder.append(number+"\n");

        for (int i = 1; i < 10; i += 2) {
            int temp = number * 10 + i;
            if (isPrime(temp)) {
                dfs(temp, depth - 1);
            }
        }

    }

    private static boolean isPrime(int num) {
        if (num == 1) return false;
        else if (num == 2) return true;

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

}

