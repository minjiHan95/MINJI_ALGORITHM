import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public void testCase() {
        Rectangle myRec = new Rectangle();

        for (int i = 0; i < 2; i++) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();

            myRec.addStartPoint(i, Math.min(x1, x2), Math.min(y1, y2));
            myRec.addDestPoint(i, Math.max(x1, x2), Math.max(y1, y2));

        }
        int distanceOfX = myRec.getDestXPoint() - myRec.getStartXPoint();
        int distanceOfY = myRec.getDestYPoint() - myRec.getStartYPoint();
        int answer = distanceOfX * distanceOfY;

        System.out.println(answer);
    }

    public static void main(String[] args) {
        Main app = new Main();
        int testCase = scanner.nextInt();
        for (int i = 0; i < testCase; i++) {
            app.testCase();
        }
    }
}

class Rectangle {
    int[][] x = new int[2][2];
    int[][] y = new int[2][2];


    public void addStartPoint(int index, int x, int y) {
        this.x[index][0] = x;
        this.y[index][0] = y;
    }

    public void addDestPoint(int index, int x, int y) {
        this.x[index][1] = x;
        this.y[index][1] = y;
    }

    public int getStartXPoint() {
        if (x[0][0] < x[1][0]) return x[1][0];
        else return x[0][0];
    }

    public int getStartYPoint() {
        if (y[0][0] < y[1][0]) return y[1][0];
        else return y[0][0];
    }

    public int getDestXPoint() {
        if (x[0][1] < x[1][1]) return x[0][1];
        else return x[1][1];
    }

    public int getDestYPoint() {
        if (y[0][1] < y[1][1]) return y[0][1];
        else return y[1][1];
    }

}