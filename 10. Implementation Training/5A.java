import java.util.Scanner;

public class Main{
    public static final Scanner scanner = new Scanner(System.in);

    public int findMinimumTrash(int [][] arr, int upX, int upY, int downX, int downY) {
        int count = 0;

        for(int i=upX; i<=downX; i++) {
            for(int j=upY; j<=downY; j++) {
                if(arr[i][j]==1) {
                    count++;
                }
            }
        }
        return count;
    }

    public void testCase() {
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int [][] arrN = new int [N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                arrN[i][j] = scanner.nextInt();
            }
        }
        int answer = 10000;
        for(int i=0; i<=N-M; i++) {
            for(int j=0; j<=N-M; j++) {
                answer = Math.min(findMinimumTrash(arrN, i, j, i+M-1, j+M-1), answer);
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) {
        Main app = new Main();
        int testCase = scanner.nextInt();
        for(int i=0; i<testCase; i++) {
            app.testCase();
        }
    }
}