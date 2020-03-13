import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public boolean isMatched (char a, char b) {
        if(a=='(') {
            return b ==')';
        }
        else if(a=='['){
            return b==']';
        }
        return false;
    }

    public int findLSCParenthesis(int x, int y, char[] arr) {
        int length = arr.length;

        if(x>=y || x<0 || y>=length) {
            return 0;
        }

        int answer = 0;

        if(isMatched(arr[x], arr[y])) {
            answer = 2 + findLSCParenthesis(x+1, y-1, arr);
        }
        
        for(int i=x; i<y; i++) {
            int tempAnswer = findLSCParenthesis(i+1, y, arr) + findLSCParenthesis(x, i, arr);
            answer = Math.max(answer, tempAnswer);
        }

        return answer;
    }

    public void testCase() {
        String s = scanner.next();
        char [] sArr = s.toCharArray();
        int answer = findLSCParenthesis(0, sArr.length-1, sArr);
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