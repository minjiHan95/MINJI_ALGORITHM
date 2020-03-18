import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void testCase() throws Exception {
        int N = scanner.nextInt();
        int[][] arr = new int[N][N];
        //int x = N - 1;

        for (int i = 0; i < N * N; i++) {
            int row = 0;
            int col = 0;
            int count = 0;
            int num = i;

            //나눠야하는 수 -> N-1부터 시작해서 -2씩 감소해야함
            int x = N - 1;
            int mok = num/x;
            //몫
            while (mok > 3) {
                num = num - x*4;
                x = x-2;
                if(x==0) {
                    mok = 0;
                    count++;
                    break;
                }
                mok = num/x;
                count++;
            }
            switch (mok) {
                case 0:
                    row = N - x - 1 - count;
                    if(x==0) {
                        col = row;
                        break;
                    }
                    col = num % x + count;
                    break;
                case 1:
                    row = num % x + count;
                    col = x + count;
                    break;
                case 2:
                    row = x + count;
                    col = N - num % x - 1 - count;
                    break;
                case 3:
                    row = N - num % x -1 - count;
                    col = N - x - 1 - count;
                    break;
                default:
                    break;
            }
            //System.out.println("arr["+row+"]"+"["+col+"]"+"="+(i));

            arr[row][col] = i+1;
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(arr[i][j]+ " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws Exception {
        Main app = new Main();
        int testCase = scanner.nextInt();
        for (int i = 0; i < testCase; i++) {
            app.testCase();
        }
    }
}