import java.io.*;


public class Main {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        Main app = new Main();
        int num = Integer.parseInt(reader.readLine());
        String[] fileList = new String[num];
        for (int i = 0; i < num; i++) {
            fileList[i] = reader.readLine();
        }
        String answer = app.findPattern(fileList);

        writer.write(answer);
        writer.flush();
        writer.close();
    }

    private String findPattern(String[] fileList) {
        char[] pattern = fileList[0].toCharArray();

        for(int i=1; i<fileList.length; i++) {
            char[] temp = fileList[i].toCharArray();
            for(int j=0; j<pattern.length; j++) {
                if(pattern[j] != temp[j]) {
                    pattern[j] = '?';
                }
            }
        }
        return String.valueOf(pattern);
    }

}
