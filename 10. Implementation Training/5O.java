import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public void findMatchedNum(char[] criteriaArr, char[][] stringArr) {
        int criLength = criteriaArr.length;
        int arrLength = stringArr.length;
        boolean isTrue = true;
        int count = 0;

        for (int i = 0; i <= arrLength - criLength; i++) {
            for (int j = i; j <= arrLength - criLength; j++) {
                if (stringArr[i][j] == criteriaArr[0]) {
                    for (int k = 1; k < criLength; k++) {
                        if (stringArr[i + k][j + k] == criteriaArr[k]) isTrue = true;
                        else {
                            isTrue = false;
                            break;
                        }
                    }
                    if(isTrue) {
                        count++;
                    }
                }
            }
        }

        //가로방향
        for (int i = 0; i < arrLength; i++) {
            for (int j = 0; j <= arrLength - criLength; j++) {
                if (stringArr[i][j] == criteriaArr[0]) {
                    for (int k = 1; k < criLength; k++) {
                        if (stringArr[i][j + k] == criteriaArr[k]) isTrue = true;
                        else {
                            isTrue = false;
                            break;
                        }
                    }
                    if(isTrue) {
                        count++;
                    }
                }
            }
        }

        //세로방향
        for (int i = 0; i <= arrLength - criLength; i++) {
            for (int j = 0; j < arrLength; j++) {
                if (stringArr[i][j] == criteriaArr[0]) {
                    for (int k = 1; k < criLength; k++) {
                        if (stringArr[i + k][j] == criteriaArr[k]) isTrue = true;
                        else {
                            isTrue = false;
                            break;
                        }
                    }
                    if(isTrue) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }

    public void testCase() {
        int N = scanner.nextInt();
        String myString = scanner.next();
        char[] criteriaArr = myString.toCharArray();
        char[][] stringArr = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = scanner.next();
            stringArr[i] = str.toCharArray();
        }
        findMatchedNum(criteriaArr, stringArr);
    }

    public static void main(String[] args) {
        Main app = new Main();
        int testCase = scanner.nextInt();
        for (int i = 0; i < testCase; i++) {
            app.testCase();
        }
    }
}
