import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public int findMin(int ... arr) {
        int min = arr[0];
        for(int a : arr){
            min = Math.min(min, a);
        }
        return min;
    }

    public int findDTW(int[] arrA, int[] arrB, int row, int col) {
        if(row<0 || col<0) {
            return Integer.MAX_VALUE;
        } else if(row==0 && col==0) {
            return (arrA[0] - arrB[0]) * (arrA[0] - arrB[0]);
        }

        int difference = arrA[row] - arrB[col];
        int square = difference * difference;

        int answer = square+ findMin(
                findDTW(arrA, arrB, row-1, col),
                findDTW(arrA, arrB, row-1, col-1),
                findDTW(arrA, arrB, row, col-1));

        return answer;
    }

    public static void main(String[] args) {
        Main app = new Main();
        int x = scanner.nextInt();
        int[] arrA = new int[x];
        int[] arrB = new int[x];

        for(int i=0; i<x; i++) {
            arrA[i] = scanner.nextInt();
        }
        for(int i=0; i<x; i++) {
            arrB[i] = scanner.nextInt();
        }
        int answer = app.findDTW(arrA, arrB, x-1, x-1);
        System.out.println(answer);
    }
}