import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public void testCase() {
        int N = scanner.nextInt();
        int[][] arr = new int[N][N];
        int[] rowArr = new int[N];
        int[] colArr = new int[N];
        int[] plusDiagonal = new int[N * 2 - 1];
        int[] minusDiagonal = new int[N * 2 - 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = scanner.nextInt();

                if (arr[i][j] == 1) {
                    rowArr[i]++;
                    colArr[j]++;
                    plusDiagonal[i - j + N - 1]++;
                    minusDiagonal[i + j]++;
                }
            }
        }

        int maximumDefense = 0;

        for(int row=0; row<N; row++) {
            for(int col=0; col<N; col++) {
                int temp = 0;
                if(arr[row][col]==1) {
                    temp = rowArr[row] + colArr[col] + plusDiagonal[row-col+N-1] + minusDiagonal [row + col] -3;
                } else {
                    temp = rowArr[row] + colArr[col] + plusDiagonal[row-col+N-1] + minusDiagonal [row + col];
                }
                maximumDefense = Math.max(maximumDefense, temp);
            }
        }
        System.out.println(maximumDefense);

    }

    public static void main(String[] args) {
        Main app = new Main();
        int testCase = scanner.nextInt();
        for (int i = 0; i < testCase; i++) {
            app.testCase();
        }
    }
}
