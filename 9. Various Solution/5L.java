import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static final int TIME_LENGTH = 48*60;

    public void printTime(int time) {
        String minutes = String.format("%02d", time/60);
        String seconds = String.format("%02d", time%60);

        System.out.printf(minutes+":"+seconds+"\n");
    }

    public void findCurrentScore(NBA[] team) {
        //초기 상태는 무승부
        int aScore = 0;
        int bScore = 0;

        int aTotal = 0;
        int bTotal = 0;

        int length = team.length;
        for (int i = 0; i < length; i++) {
            if (team[i].whichTeam == 1) {
                aScore++;
            } else {
                bScore++;
            }
            if (i == length - 1) {
                if (aScore > bScore) {
                    aTotal = aTotal + TIME_LENGTH - team[i].time;
                } else if (aScore < bScore) {
                    bTotal = bTotal + TIME_LENGTH - team[i].time;
                }
                break;
            }
            if (aScore > bScore) {
                aTotal = aTotal + team[i + 1].time - team[i].time;
            } else if (aScore < bScore) {
                bTotal = bTotal + team[i + 1].time - team[i].time;
            }
        }

        printTime(aTotal);
        printTime(bTotal);
    }

    public void testCase() {
        int command = scanner.nextInt();
        NBA[] teamList = new NBA[command];

        for (int i = 0; i < command; i++) {
            int team = scanner.nextInt();
            String stringTime = scanner.next();
            String[] splited = stringTime.split(":");

            int minutes = Integer.parseInt(splited[0]);
            int seconds = Integer.parseInt(splited[1]);

            int time = minutes * 60 + seconds;

            teamList[i] = new NBA();

            teamList[i].whichTeam = team;
            teamList[i].time = time;
        }
        findCurrentScore(teamList);
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.testCase();
    }
}

class NBA {
    int whichTeam;
    int time;


}