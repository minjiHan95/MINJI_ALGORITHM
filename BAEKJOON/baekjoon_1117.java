import java.io.*;
import java.util.*;

public class Main {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(reader.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        Rectangle rectangle = new Rectangle(f, x1, y1, x2, y2, W);
        int answer = (rectangle.calculateLeftArea() + rectangle.calculateRightArea()) * (c + 1);

        writer.write(String.valueOf(W * H - answer));
        writer.flush();
        writer.close();
    }
}

class Rectangle {
    private int criteria;
    private int x1, y1, x2, y2;
    private int W;

    public Rectangle(int criteria, int x1, int y1, int x2, int y2, int W) {
        this.criteria = criteria;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.W = W;
    }

    public int calculateLeftArea() {
        int width = x2 - x1;
        int height = y2 - y1;
        if (-x2 + criteria < 0 && -x1 + criteria < 0) {
            width = 0;
        } else if (-x2 + criteria < 0) {
            width = -x1 + criteria;
        }
        return width * height;
    }

    public int calculateRightArea() {
        int width = x2 - x1;
        int height = y2 - y1;
        if (x2 + criteria > W && x1 + criteria > W) {
            width = 0;
        } else if (x2 + criteria > W) {
            width = W - criteria - x1;
        }
        return width * height;
    }
}