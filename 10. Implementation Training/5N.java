import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public void findMatchedIndex(String criteria, String pattern) {
        int criLength = criteria.length();
        int patternLength = pattern.length();
        ArrayList<Integer> trueIndex = new ArrayList<>();
        boolean isTrue = false;

        for(int i=0; i<=criLength - patternLength; i++) {
            char myChar = criteria.charAt(i);
            isTrue = false;
            if(myChar==pattern.charAt(0)) {
                for(int j=1; j<patternLength; j++) {
                    if(criteria.charAt(i+j)==pattern.charAt(j)) {
                        isTrue = true;
                    } else {
                        isTrue = false;
                        break;
                    }
                }
                if(isTrue) {
                    trueIndex.add(i);
                }
            }
        }
        for(int i=0; i<trueIndex.size(); i++) {
            System.out.println(trueIndex.get(i));
        }
    }

    public void testCase() {
        String criteriaStr = scanner.next();
        String patternStr = scanner.next();
        findMatchedIndex(criteriaStr, patternStr);

    }

    public static void main(String[] args) {
        Main app = new Main();
        app.testCase();
    }
}
