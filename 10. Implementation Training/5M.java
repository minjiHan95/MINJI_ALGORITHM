import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public boolean isMatched(String myString, String otherString) {
        int myLength = myString.length();

        if (myLength != otherString.length()) {
            return false;
        }
        for (int i = 0; i < myLength; i++) {
            char myChar = myString.charAt(i);
            char otherChar = otherString.charAt(i);
            if(!(myChar==otherChar || myChar=='?')) {
                return false;
            }
        }
        return true;
    }

    public void testCase(int num, String myString) {
        ArrayList<String> stringList = new ArrayList<String>();

        for (int i = 0; i < num; i++) {
            String str = scanner.next();
            if(isMatched(myString, str)) {
                stringList.add(str);
            }
        }
        for(int i=0; i<stringList.size(); i++) {
            System.out.println(stringList.get(i));
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        int num = scanner.nextInt();
        String myString = scanner.next();
        app.testCase(num, myString);
    }
}
