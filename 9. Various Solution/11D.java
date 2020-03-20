import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public void testCase() {
        int N = scanner.nextInt();
        Circle myCircle;
        Circle[] wifiCilcle = new Circle[N];

        double originX = scanner.nextDouble();
        double originY = scanner.nextDouble();
        double originR = scanner.nextDouble();
        myCircle = new Circle(originX, originY, originR);

        for (int i = 0; i < N; i++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            double r = scanner.nextDouble();
            wifiCilcle[i] = new Circle(x, y, r);
        }

        int totalCount = 0;
        int wifiCount = 0;

        double unitLength = (originR * 2) / 1000;
        for (double i = (originX - originR); i < (originX + originR); i += unitLength) {
            for (double j = (originY - originR); j < (originY + originR); j += unitLength) {
                if(myCircle.isContained(i, j)) {
                    totalCount++;
                    boolean isInside = false;
                    for(Circle current : wifiCilcle) {
                        if(current.isContained(i, j)) {
                            isInside = true;
                            break;
                        }
                    }
                    if(isInside) {
                        wifiCount++;
                    }
                }
            }
        }

        System.out.printf("%.2f\n",(double)wifiCount/totalCount);
    }

    public static void main(String[] args) {
        Main app = new Main();
        int testCase = scanner.nextInt();
        for (int i = 0; i < testCase; i++) {
            app.testCase();
        }
    }
}

class Circle {
    double xPos;
    double yPos;
    double r;

    public Circle(double xPos, double yPos, double r) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.r = r;
    }

    public boolean isContained(double x, double y) {
        double distanceX = this.xPos - x;
        double distanceY = this.yPos - y;
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return this.r >= distance;
    }
}