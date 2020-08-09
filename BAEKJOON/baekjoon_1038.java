import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        Main app = new Main();

        int num = Integer.parseInt(reader.readLine());
        long answer = app.findDecreaseNum(num);

        writer.write(String.valueOf(answer));
        writer.flush();
        writer.close();


    }

    public long findDecreaseNum(int num) {
        if (num >= 0 && num <= 10) {
            return num;
        } else if (num == 1022) {
            return 9876543210L;
        } else if (num > 1022) {
            return -1;
        }

        Queue<Long> decreaseList = new LinkedList<>();
        long count = 0;
        long answer = 0;
        for (int i = 0; i < 10; i++) {
            decreaseList.add((long) i);
            count++;
        }
        while (count <= num) {
            long front = decreaseList.poll();
            int temp = (int) front % 10;
            for (int i = 0; i < temp; i++) {
                decreaseList.add(front * 10 + i);
                if (count == num) {
                    answer = front * 10 + i;
                }
                count++;
            }
        }
        return answer;

    }
}