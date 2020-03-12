import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    // ... varargs 또는 가변인자: int 객체가 0개부터 여러개까지 매개변수로 올수 있다
    public int findMin(int... arr) {
        int min = arr[0];
        for (int a : arr) {
            min = Math.min(min, a);
        }
        return min;
    }

    public int findMinimum(int row, int col, int[][] arrA, int[][] arrB) {

        if(row<0 || col <0 || col>=arrA[0].length) {
            return 1000000000;
        } else if (row ==0) {
            return (arrA[row][col] - arrB[row][col]) * (arrA[row][col] - arrB[row][col]);
        }

        int differnence = (arrA[row][col] - arrB[row][col]) * (arrA[row][col] - arrB[row][col]);

        int minimum = differnence + findMin(
                findMinimum(row - 1, col - 1, arrA, arrB),
                findMinimum(row - 1, col, arrA, arrB),
                findMinimum(row - 1, col + 1, arrA, arrB));

        return minimum;
    }

    public static void main(String[] args) {
        Main app = new Main();

        int row = scanner.nextInt();
        int col = scanner.nextInt();

        int[][] arrA = new int[row][col];
        int[][] arrB = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arrA[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arrB[i][j] = scanner.nextInt();
            }
        }
        int min = app.findMinimum(row-1, col-1, arrA, arrB);

        System.out.println(min);
    }
}


