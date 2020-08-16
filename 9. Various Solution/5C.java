import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public void testCase() {
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int[] rowArr = new int[N];
        int[] colArr = new int[N];

        //오른쪽으로 증가하는 대각선의 합
        //(i-j)값이 음수부터 시작하기 때문에 길이만큼 더해준 값으로 index 시작한다.
        int[] plusDiagonal = new int[N * 2 - 1];
        //왼쪽으로 증가하는 대각선의 합
        //(1,1)부터 시작 -> -2를 해준 상태에서 index 시작한다.
        int[] minusDiagonal = new int[N * 2 - 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0) {
                    rowArr[i]++;
                    colArr[j]++;
                    plusDiagonal[i - j + N - 1]++;
                    minusDiagonal[i + j]++;
                }
            }
        }

        int maximumDefense = 0;

        for (int i = 0; i < K; i++) {
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            if(arr[row][col]==1) {
                maximumDefense = Math.max(maximumDefense, rowArr[row] + colArr[col] + plusDiagonal[row-col+N-1] + minusDiagonal [row + col] -3);
            } else {
                maximumDefense = Math.max(maximumDefense, rowArr[row] + colArr[col] + plusDiagonal[row-col+N-1] + minusDiagonal [row + col]);
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