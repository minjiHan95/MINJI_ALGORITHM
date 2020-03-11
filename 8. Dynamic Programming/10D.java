import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public int findMinimunCost(int[][] arr, int x, int y) {

        if (x < 1 || y < 1) {
            return Integer.MAX_VALUE;
        } 
        else if(x==1 && y==1) {
            return arr[1][1];
        }

        int upPath = findMinimunCost(arr, x - 1, y);
        int leftPath = findMinimunCost(arr, x, y - 1);
        int answer = Math.min(upPath, leftPath) + arr[x][y];

        //System.out.println("x : " + x + " y : " + y + " min : " + Math.min(upPath, leftPath) + " answer" + answer);


        return answer;

    }

    public static void main(String[] args) {
        Main app = new Main();
        int x = scanner.nextInt();
        int[][] arr = new int[x + 1][x + 1];

        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= x; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        int answer = app.findMinimunCost(arr, x, x);
        System.out.println(answer);
    }
}

/*

//오지게 못고쳤던 잘못된 풀이
//재귀함수의 값을 return할때, 그 값을 다시 함수 안에서 정의해서 넘겨주는것은 굉장히 위험
//answer += ~~~~~~~
//answer의 값을 중복되게 사용하는 짓은 자제하도록!

import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public int findMinimunCost(int[][] arr, int x, int y, int length, int answer) {
        if (x < 1 || y < 1 || x > length || y > length) {
            return Integer.MAX_VALUE;
        }

//        if(x==1 && y==1) {
//            return arr[1][1];
//        }

        int upPath = findMinimunCost(arr, x, y - 1, length, answer + arr[x][y-1]);
        int leftPath = findMinimunCost(arr, x - 1, y, length, answer + arr[x-1][y]);


        answer = answer + Math.min(upPath, leftPath);

        System.out.println("x : " + x + " y : " + y + " min : " + Math.min(upPath, leftPath) + " answer" + answer);

        return answer;
    }

    public static void main(String[] args) {
        Main app = new Main();
        int x = scanner.nextInt();
        int[][] arr = new int[x + 2][x + 2];

        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= x; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        int answer = app.findMinimunCost(arr, x, x, x, arr[x][x]);
        System.out.println(answer);
    }
}
*/