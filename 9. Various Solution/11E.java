import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[] house = new int[N+1];

        for(int i=1; i<=N; i++) {
            house[i] = scanner.nextInt();

        }

        for(int i=0; i<M; i++) {
            String command = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            if(command.equals("UPDATE")) {
                house[x] = y;

            } else if(command.equals("GET_MAX")) {
                int length = y-x+1;
                int [] tempArr = new int[length];
                for(int j=0; j<length; j++) {
                    tempArr[j] = house[x+j];
                }
                Arrays.sort(tempArr);
                System.out.println(tempArr[length-1]);
            } else if(command.equals("GET_SUM")) {
                int sum = 0;
                for(int j=x; j<y+1; j++) {
                    sum +=house[j];
                }
                System.out.println(sum);
            }
        }
    }

}

