import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public void testCase() {
        int N = scanner.nextInt();
        int[] sumArr = new int[N + 1];
        int[][] answerArr = new int[N][N];
        answerArr[0][0] = 1;
        answerArr[N-1][N-1] = N*N;

        sumArr[1] = 1;
        for (int i = 2; i <= N; i++) {
            sumArr[i] = sumArr[i - 1] + i;
        }

        for (int i = 1; i <= N * N / 2; i++) {
            int x = 0;
            int num = i;
            while (!(i >= sumArr[x] && i < sumArr[x + 1])) {
                x++;
            }
            if (x % 2 == 0) {
                num = sumArr[x + 1] - num - 1;
            } else {
                num = num - sumArr[x];
            }
//            System.out.println(num+" , "+(x-num)+" : "+ (i));
//            System.out.println((N-num-1)+" , "+(N-(x-num)-1)+" : "+ (N*N - i));

            answerArr[num][x-num] = i+1;
            answerArr[N-num-1][N-(x-num)-1] = N*N - i;
        }
        for(int i=0; i<N; i++) {
            for(int j =0; j<N; j++) {
                System.out.print(answerArr[i][j]+" ");
            }
            System.out.println();
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