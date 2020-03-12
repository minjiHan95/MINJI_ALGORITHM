import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public int findLSC (int row, int col, boolean [][] arr) {
        int rowSize = arr.length;
        int colSize = arr[0].length;

        if(row<0 || col<0) {
            return 0;
        }
        else if(row==0) {
            if(arr[row][col]) {
                return 1;
            }
            return 0;
        }

        //문자가 일치하는 경우
        if(arr[row][col]) {
            //(row, col)을 선택하는 경우, (row, col)을 선택하지 않는 경우
            return Math.max(findLSC(row-1, col-1, arr) +1 , findLSC(row-1, col-1, arr));
        }

        //문자가 일치하지 않는 경우
        else {
            return Math.max(findLSC(row-1, col, arr), findLSC(row, col-1, arr));
        }
    }

    public static void main(String[] args) {
        Main app = new Main();

        String s1 = scanner.next();
        String s2 = scanner.next();

        int s1Length = s1.length();
        int s2Length = s2.length();

        char [] s1Arr = s1.toCharArray();
        char [] s2Arr = s2.toCharArray();

        if(s1Length<s2Length) {
            boolean [][] isCorrespond = new boolean[s1Length+1][s2Length+1];
            for(int i=0; i<s1Length; i++) {
                for(int j=0; j<s2Length; j++) {
                    if(s1Arr[i]==s2Arr[j]) isCorrespond[i][j] = true;
                }
            }
            int answer = app.findLSC(s1Length,s2Length, isCorrespond);
            System.out.println(answer);
        }
        else {
            boolean [][] isCorrespond = new boolean[s2Length+1][s1Length+1];
            for(int i=0; i<s2Length; i++) {
                for(int j=0; j<s1Length; j++) {
                    if(s2Arr[i]==s1Arr[j]) isCorrespond[i][j] = true;
                }
            }
            int answer = app.findLSC(s2Length,s1Length, isCorrespond);
            System.out.println(answer);
        }


    }
}