import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static int[] segmentDisplay = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};

    public int findNumber(int totalWeight, int weight) {

        if (weight < 0) {
            return 0;
        } else if (weight == 0) {
            return 1;
        }
        int count = 0;

        if (totalWeight > 6 && totalWeight == weight) {
            for (int i = 1; i < segmentDisplay.length; i++) {
                count = count + findNumber(totalWeight, weight - segmentDisplay[i]);
            }
        } else {
            for (int i = 0; i < segmentDisplay.length; i++) {
                count = count + findNumber(totalWeight, weight - segmentDisplay[i]);
            }
        }
        return count;
    }

    public void testCase() {
        int totalWeight = scanner.nextInt();

        if (totalWeight == 0) {
            System.out.println(0);
        } else {
            int answer = findNumber(totalWeight, totalWeight);
            System.out.println(answer);
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        int testCase = scanner.nextInt();
        for (int i = 0; i < testCase; i++) {
            app.testCase();
        }
    }
}