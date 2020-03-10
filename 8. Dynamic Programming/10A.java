import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public void findLISValue(int [] arr) {
        int length = arr.length;
        int [] answerArr = new int[length];
        answerArr[0] = 1;
        int max = 0;
        for(int i=1; i<length; i++) {
            for(int j=0; j<i; j++) {
                if(arr[i] > arr[j] && answerArr[i] < answerArr[j] +1) {
                    //앞에서 계산한 거리를 새롭게 갱신하다
                    //i의 값이 j보다 크고 && LIS의 갯수가 앞서 계산한 값보다 작다면,
                    //i의 값이 j보다 크다는 것은 보장되었기 때문에 +1을 해서 계산한다.
                    answerArr[i] = answerArr[j] +1;
                }
            }
        }
        for(int i=0; i<length; i++) {
            max = Math.max(answerArr[i], max);
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        Main app = new Main();
        int numberOfValue = scanner.nextInt();
        int [] LISArr = new int[numberOfValue];
        for(int i=0; i<numberOfValue; i++) {
            LISArr[i] = scanner.nextInt();
        }
        app.findLISValue(LISArr);
    }

}