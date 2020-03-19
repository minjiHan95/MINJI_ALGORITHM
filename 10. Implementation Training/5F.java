import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public void testCase() {
        int N = scanner.nextInt();
        int[][] arr = new int[N][N];
        arr[0][0] = 1;
        /*
        숫자는 제곱 범위 안에 존재한다.
        ex) [0], [1,2,3], [4,5,6,7,8] ... 이런식으로 행렬을 분해해서 어떤 제곱 범위 안에 들어가는지 판단한다.
        1. 제곱이 될 수 있는 값의 범위는 1 ~ N 까지의 값으로 +1 을 증가하면서 판단한다.
        2. 최대 제곱의 수가 홀수 or 짝수냐에 따라 분리해서 num값을 초기화한다.
         */


        int x = 2;
        for (int index = 1; index < N * N; index++) {
            int num = index;
            int row;
            int col;
            //index가 범위 안에 존재할 때까지 반복
            while (!(index >= (x - 1) * (x - 1) && index < x * x)) {
                x++;
            }

            //x가 홀수냐 짝수냐에 따라 다르게 처리
            if (x % 2 == 0) {
                num = num - (x - 1) * (x - 1);
            } else {
                num = x * x - num - 1;
            }

            if(num<x) {
                row = num%x;
                col = x-1;
            }
            else {
                row = x-1;
                col = x-(num%x)-2;
            }
            arr[row][col] = index+1;
            //System.out.println("arr["+row+"]"+"["+col+"]"+"="+(index+1));
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(arr[i][j]+" ");
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