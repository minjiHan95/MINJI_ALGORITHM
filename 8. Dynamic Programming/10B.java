import java.util.Scanner;

public class Main{
    public static final Scanner scanner = new Scanner(System.in);

    public double fenceMinimumCost(int[] fence, int lastIndex) {
        int length = fence.length-1;

        if(lastIndex<0) {
            return 0;
        }


        //fence를 통째로 수리하는 값으로 초기화한다.
        double answer = Math.sqrt(fence[lastIndex]+1);

        //해당 index의 fence를 포함해서 수리할 것인지 따로 수리할것인지 결정

        for(int i=1; i<=length; i++) {
            double tempCost = fenceMinimumCost(fence, lastIndex-1) + 1;

            //System.out.println("i"+" : "+i+" //index"+" : "+(lastIndex-1)+" //tempCost"+" : "+tempCost+" //answer"+" : "+answer);

            answer = Math.min(tempCost, answer);
        }
        return answer;
    }

    public static void main(String[] args) {
        Main app = new Main();
        String s = scanner.next();
        char [] tempFence = s.toCharArray();
        int count = 0;
        for(int i=0; i<tempFence.length; i++) {
            if(tempFence[i] == 'X') {
                count++;
            }
        }

        //fence를 가지고 있는 새로운 배열 생성
        int [] myFence = new int[count];
        int index=0;
        for(int i=0; i<tempFence.length; i++) {
            if(tempFence[i]=='X') {
                myFence[index++] = i;
            }
        }

        double answer= app.fenceMinimumCost(myFence, count-1);
        System.out.printf("%.3f", answer);
        System.out.println();
    }
}