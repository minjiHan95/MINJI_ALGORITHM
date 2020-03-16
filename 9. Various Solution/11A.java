import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static Subway[] mySubway;
    public static int maximumCost;


    public boolean isPossible(int myCost, int maxV) {
        int length = mySubway.length;

        for(int i=0; i<length; i++) {
            if(maxV - mySubway[i].V>0) {
                int myP = ((maxV - mySubway[i].V - 1) / mySubway[i].P) + 1;
                myCost -= myP;
            }
        }

        if (myCost < 0) {
            return false;
        }
        return true;
    }

    public int findMaximumV(int x, int y) {

        while(x<y) {
            int midV = (x+y+1)/2;
            if(isPossible(maximumCost, midV)) {
                x = midV;
            }
            else {
                y = midV-1;
            }
        }
        return x;
    }

    public void testCase() {
        int x = scanner.nextInt();
        maximumCost = scanner.nextInt();
        mySubway = new Subway[x];

        for (int i = 0; i < x; i++) {
            mySubway[i] = new Subway(i);
            int myV = scanner.nextInt();
            mySubway[i].addV(myV);
        }

        for (int i = 0; i < x; i++) {
            int myP = scanner.nextInt();
            mySubway[i].addP(myP);
        }

        Arrays.sort(mySubway);
        int answer = findMaximumV(0, 100000);
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

class Subway implements Comparable<Subway> {
    int index;
    int V;
    int P;

    public Subway(int index) {
        this.index = index;
    }

    public void addV(int V) {
        this.V = V;
    }

    public void addP(int P) {
        this.P = P;
    }

    @Override
    public int compareTo(Subway o) {
        return this.V - o.V;
    }
}