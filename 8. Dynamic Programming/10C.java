import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public int findMaximumIncentice(int [] schedule, int n) {

        if(n==0) {
            return schedule[0];
        }
        else if(n==1) {
            return schedule[1] + schedule[0];
        }
        else if(n==2) {
            return Math.max(schedule[2]+schedule[1], schedule[2]+schedule[0]);
        }

        int length = schedule.length;
        int answer = 0;
        for(int i=0; i<length; i++) {
            int includeX = findMaximumIncentice(schedule, n-3) + schedule[n] + schedule[n-1];
            int excludeX = findMaximumIncentice(schedule, n-2) + schedule[n];
            int tempAnswer = Math.max(includeX, excludeX);
            answer = Math.max(tempAnswer, findMaximumIncentice(schedule, n-1));
 }
        return answer;
    }

    public static void main(String[] args) {
        Main app =  new Main();

        int x = scanner.nextInt();
        int [] schedule = new int[x];

        for(int i=0; i<x; i++) {
            schedule[i] = scanner.nextInt();
        }
        int answer = app.findMaximumIncentice(schedule, x-1);
        System.out.println(answer);
    }
}