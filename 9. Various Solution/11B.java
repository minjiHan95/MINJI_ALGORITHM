import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static int[] robotPosition;
    public int essential;

    public boolean isPossible(int minLingth ,int limitLength) {
        int startIndex = 0;
        for (int i = 0; i < robotPosition.length; i++) {
            if (startIndex >= essential) {
                return true;
            }

            //일단 범위 안에 들어가는 값인지만 확인한다
            if (robotPosition[i] + minLingth > startIndex && robotPosition[i] + minLingth <= startIndex + limitLength * 2) {
                startIndex += limitLength * 2;
//                System.out.println("startIndex: "+ startIndex+ " i: "+ i);
            }
            else if (robotPosition[i] - minLingth >= startIndex && robotPosition[i] - minLingth <= startIndex + limitLength * 2) {
                startIndex += limitLength * 2;
//                System.out.println("startIndex: "+ startIndex+ " i: "+ i);
            }
        }
        return false;
    }

    public int findMinimumLength(int maxLength, int limit) {
        int answer = -1;
        for(int i=maxLength; i>=0; i--) {
            if(isPossible(i, limit)) {
                answer = i;
            }
        }
        return answer;
    }

    public void testCase() {
        int robot = scanner.nextInt();
        int maxLength = scanner.nextInt();
        essential = scanner.nextInt();
        robotPosition = new int[robot];

        for (int i = 0; i < robot; i++) {
            robotPosition[i] = scanner.nextInt();
        }
        int answer = findMinimumLength(10, maxLength);
        System.out.println(answer);
    }

    public static void main(String[] args) {
        Main app = new Main();
        int testCase = scanner.nextInt();
        for (int i = 0; i < testCase; i++) {
            app.testCase();
        }
    }
}