import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    //새로운 배열에 (1,1) ~ (x,y) 로 만들어지는 직사각형의 누적합을 모두 계산하여 초기화
    //구하고자 하는 배열의 행, 열 의 값은 (1,1) 부터 (구하고자 하는 좌표) - XX - YY + 중복되서 처리된 값 더하기!
    public int [][] findAccumulatedValue(int[][] arr, int length) {
        int[][] newArr = new int[length][length];

        for (int i = 1; i < length; i++) {
            for (int j = 1; j < length; j++) {
                newArr[i][j] = arr[i][j];
            }
        }

        for (int i = 1; i < length; i++) {
            for (int j = 1; j < length; j++) {
                newArr[i][j] = newArr[i][j] + newArr[i - 1][j] + newArr[i][j - 1] - newArr[i - 1][j - 1];
            }
        }
        return newArr;
    }

    public int testCase(int x, int y, int destX, int destY, int[][] newArr) {
        return newArr[destX][destY] - newArr[x-1][destY] - newArr[destX][y-1] + newArr[x-1][y-1];
    }

    public static void main(String[] args) {
        Main app = new Main();

        int x = scanner.nextInt();
        int testCase = scanner.nextInt();

        int[][] numberOfArr = new int[x + 1][x + 1];

        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= x; j++) {
                numberOfArr[i][j] = scanner.nextInt();
            }
        }

        int [][] newArr = app.findAccumulatedValue(numberOfArr, x + 1);

        for (int i = 0; i < testCase; i++) {
            int originX = scanner.nextInt();
            int originY = scanner.nextInt();
            int destX = scanner.nextInt();
            int destY = scanner.nextInt();

            int answer = app.testCase(originX, originY, destX, destY, newArr);
            System.out.println(answer);
        }
    }
}