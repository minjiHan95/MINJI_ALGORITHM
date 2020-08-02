import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Main {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        Main app = new Main();

        HashMap<String, Integer> lampList = new HashMap<>(); //같은 램프 수
        HashMap<Integer, Integer> countList = new HashMap<>(); //꺼진 램프 수 중 최

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        for (int i = 0; i < row; i++) {
            String currentRow = reader.readLine();
            int numOfZero = app.countMatches(currentRow);
            if (!lampList.containsKey(currentRow)) {
                lampList.put(currentRow, 1);
                if (!countList.containsKey(numOfZero)) {
                    countList.put(numOfZero, 1);
                }
                continue;
            }
            int count = lampList.get(currentRow);
            lampList.put(currentRow, count + 1);
            int zero = countList.get(numOfZero);
            if (zero < count + 1) {
                countList.put(numOfZero, count + 1);
            }
        }

        int k = Integer.parseInt(reader.readLine());
        int answer = 0;
        if (countList.containsKey(k)) {
            answer = countList.get(k);
        }

        writer.write(String.valueOf(answer));
        writer.flush();
        writer.close();
    }

    public int countMatches(String str) {
        int count = 0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (chars[i] == '0') {
                count++;
            }
        }
        return count;
    }
}

