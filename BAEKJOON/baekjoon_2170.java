import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
    public static StringBuilder builder = new StringBuilder();


    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        int answer = 0;

        PriorityQueue<Line> pointList = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            pointList.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Line initial = pointList.poll();
        int start = initial.start;
        int end = initial.end;

        while (!pointList.isEmpty()) {
            Line current = pointList.poll();
            int a = current.start;
            int b = current.end;

            if (a >= start && a <= end) {
                if (b <= end) continue;
                end = b;
            } else {
                answer += Math.abs(end - start);
                start = a;
                end = b;
            }
        }
        answer += Math.abs(end - start);
        writer.write(String.valueOf(answer));
        writer.flush();
        writer.close();
    }
}

class Line implements Comparable<Line> {
    int start;
    int end;

    public Line(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Line o) {
        int startValue = this.start - o.start;
        if (startValue == 0) {
            return this.end - o.end;
        }
        return startValue;
    }
}
