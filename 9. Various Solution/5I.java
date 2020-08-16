import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public boolean isIntersected(int[] intersection, int[][] line) {
        if (intersection[0] >= line[1][0] && intersection[0] <= line[1][1] && intersection[1] >= line[0][0] && intersection[1] <= line[0][1]) {
            return true;
        }
        return false;
    }

    public boolean parallelIntersected(int[][] line) {
        int minIndex;
        if (line[0][0] < line[0][1]) {
            minIndex = 0;
        } else {
            minIndex = 1;
        }
        if (line[minIndex][1]<line[1-minIndex][0]) {
            return false;
        }
        return true;
    }

    public void testCase() {
        boolean isVertical = false;
        boolean isHorizontal = false;
        int[] intersection = new int[2];
        int[][] line = new int[2][2];
        boolean answer = false;

        for (int i = 0; i < 2; i++) {

            int xStart = scanner.nextInt();
            int yStart = scanner.nextInt();
            int xDest = scanner.nextInt();
            int yDest = scanner.nextInt();

            //수직
            if (xStart == xDest) {
                intersection[i] = xStart;
                line[i][0] = Math.min(yStart, yDest);
                line[i][1] = Math.max(yStart, yDest);
                isVertical = true;
            } else {
                intersection[i] = yStart;
                line[i][0] = Math.min(xStart, xDest);
                line[i][1] = Math.max(xStart, xDest);
                isHorizontal = true;
            }
        }
        if (isHorizontal && isVertical) {
            answer = isIntersected(intersection, line);
        } else if (isHorizontal && intersection[0]==intersection[1]) {
            answer = parallelIntersected(line);
        } else if (isVertical && intersection[0]==intersection[1]) {
            answer = parallelIntersected(line);
        }

        if(answer) {
            System.out.println("YES");
        }else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        int testCase = scanner.nextInt();
        for (int i = 0; i < testCase; i++) {
            app.testCase();
        }
    }
}
