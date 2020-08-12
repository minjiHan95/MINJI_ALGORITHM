import java.io.*;
import java.util.*;

public class Main {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        Main app = new Main();
        String gear1 = reader.readLine();
        String gear2 = reader.readLine();
        char[] shorterGear;
        char[] longerGear;

        if (gear1.length() > gear2.length()) {
            shorterGear = gear2.toCharArray();
            longerGear = gear1.toCharArray();
        } else {
            shorterGear = gear1.toCharArray();
            longerGear = gear2.toCharArray();
        }

        int answer = app.findMinimumWidth(shorterGear, longerGear);

        writer.write(String.valueOf(answer));
        writer.flush();
        writer.close();
    }

    private int findMinimumWidth(char[] shorterGear, char[] longerGear) {
        int startIndex = 0;
        boolean available = true;

        while (available) {
            System.out.println("/////////");
            System.out.println(startIndex);
            for (int i = 0; i < shorterGear.length; i++) {
                if (startIndex + i >= longerGear.length) {
                    available = false;
                    break;
                }
                if (i == shorterGear.length - 1) {
                    available = false;
                    break;
                }
                if (shorterGear[i] + longerGear[startIndex + i] <= 99) {
                    continue;
                } else {
                    startIndex = i + 1;
                    break;
                }
            }
        }

        if (startIndex + shorterGear.length > longerGear.length) {
            return startIndex + shorterGear.length;
        } else {
            return longerGear.length;
        }

    }
}