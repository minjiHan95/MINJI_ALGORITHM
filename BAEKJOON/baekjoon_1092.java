import java.io.*;
import java.util.*;

public class Main {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        ArrayList<Integer> craneList = new ArrayList<>();
        ArrayList<Integer> boxList = new ArrayList<>();

        int crane = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < crane; i++) {
            craneList.add(Integer.parseInt(st.nextToken()));
        }

        int box = Integer.parseInt(reader.readLine());
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < box; i++) {
            boxList.add(Integer.parseInt(st.nextToken()));
        }
        reader.close();

        Collections.sort(craneList, Comparator.reverseOrder());
        Collections.sort(boxList, Comparator.reverseOrder());

        if (boxList.get(0) > craneList.get(0)) {
            writer.write(String.valueOf(-1));
        } else {
            int time = 0; //수행 시간
            while (boxList.size() != 0) {
                int craneIndex = 0;
                int boxIndex = 0;
                while (craneIndex < crane) {
                    if (boxIndex == boxList.size()) break;

                    if (boxList.get(boxIndex) <= craneList.get(craneIndex)) {
                        boxList.remove(boxIndex);
                        craneIndex++;
                    } else if (boxList.get(boxIndex) > craneList.get(craneIndex)) {
                        boxIndex++;
                    }
                }
                time++;
            }
            writer.write(String.valueOf(time));
        }
        writer.flush();
        writer.close();
    }
}