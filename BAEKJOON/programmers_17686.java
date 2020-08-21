import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        File[] myFile = new File[files.length];

        for (int i = 0; i < files.length; i++) {
            myFile[i] = new File(files[i]);
        }
        Arrays.sort(myFile);

        String[] answer = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            answer[i] = myFile[i].head + myFile[i].number + myFile[i].tail;
        }
        return answer;
    }
}

class File implements Comparable<File> {
    String head = "";
    String number = "";
    String tail = "";
    String[] result;

    public File(String str) {
        result = detach(str);
    }

    public String[] detach(String str) {
        int i = 0;

        for (; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                break;
            }
            this.head += ch;
        }
        for (; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!(ch >= '0' && ch <= '9')) {
                break;
            }
            this.number += ch;
        }
        for (; i < str.length(); i++) {
            char ch = str.charAt(i);
            tail += ch;
        }
        String[] result = {head.toLowerCase(), number, tail};
        return result;
    }

    @Override
    public int compareTo(File o) {
        int headValue = this.result[0].compareTo(o.result[0]);

        if (headValue == 0) {
            int num1 = Integer.parseInt(this.result[1]);
            int num2 = Integer.parseInt(o.result[1]);
            return num1 - num2;
        } else {
            return headValue;
        }
    }

}