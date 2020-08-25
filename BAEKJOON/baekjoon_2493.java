import java.io.*;
import java.util.*;

public class Main {
    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static Scanner scanner = new Scanner(System.in);
    public static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        ArrayList<Top> topList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            topList.add(new Top(i, Integer.parseInt(st.nextToken())));
        }

        Stack<Top> available = new Stack<>();
        available.push(new Top(0, 100000000));

        for (Top current : topList) {
            if(available.isEmpty()) break;
            if (available.peek().height <= current.height) {
                available.pop();
            }
            Top target = available.peek();
            builder.append(target.index + " ");
            available.push(current);
        }

        writer.write(String.valueOf(builder));
        writer.flush();
        writer.close();
    }
}

class Top {
    int index;
    int height;

    public Top(int index, int height) {
        this.index = index;
        this.height = height;
    }
}

