import java.util.Scanner;

public class Main {
    public static final Scanner scannner = new Scanner(System.in);

    public boolean isPossible (ArrayTable[] arr) {
        int length = arr.length;
        for(int i=0; i<length-1; i++) {
            if(arr[i].col != arr[i+1].row) return false;
        }
        return true;
    }

    //x번째 행렬부터 y번째 행렬까지의 곱셈중 가장 작은 연산량을 찾는다.
    public int findMultiplication (int x, int y, int [] arr) {
        if(x==y) {
            return 0;
        }
        int min = Integer.MAX_VALUE;

        for(int i=x; i<y; i++) {
            int multiValue = arr[x-1] * arr[i] * arr[y];
            min = Math.min(findMultiplication(x, i, arr) + findMultiplication(i+1, y, arr) + multiValue, min);
        }
        return min;
    }

    public void testCase() {
        int numberOfArr = scannner.nextInt();
        ArrayTable [] myArr = new ArrayTable[numberOfArr];
        for(int i=0; i<numberOfArr; i++) {
            int x =  scannner.nextInt();
            int y = scannner.nextInt();
            myArr[i] = new ArrayTable(x, y);
        }
        if(isPossible(myArr)) {
            int [] valueArr = new int[numberOfArr+1];
            for(int i=0; i<numberOfArr; i++) {
                valueArr[i] = myArr[i].row;
            }
            valueArr[numberOfArr] = myArr[numberOfArr-1].col;
            int answer = findMultiplication(1, numberOfArr, valueArr);
            System.out.println(answer);
        }
        else {
            System.out.println(-1);
        }

    }

    public static void main(String[] args) {
        Main app = new Main();
        int testCase = scannner.nextInt();
        for(int i=0; i<testCase; i++) {
            app.testCase();
        }
    }
}
class ArrayTable {
    int row;
    int col;
    public ArrayTable (int row, int col) {
        this.row = row;
        this.col = col;
    }
}