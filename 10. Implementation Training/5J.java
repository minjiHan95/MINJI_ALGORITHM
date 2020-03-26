import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public boolean isPossible(boolean[][] isVisited, int x, int y) {
        int length = isVisited.length - 1;

//        System.out.println("==============");
//        System.out.println(x + " " + y);
        if (x < 0 || y < 0 || x >= length || y >= length) {
//            System.out.println("범위 벗어남");
            return false;
        } else if (isVisited[x][y]) {
//            System.out.println("이미 방문함");
            return false;
        }
        isVisited[x][y] = true;
        return true;
    }

    public void testCase() {
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        boolean[][] isVisited = new boolean[N + 1][N + 1];
        int currentX = scanner.nextInt() - 1;
        int currentY = scanner.nextInt() - 1;

        isVisited[currentX][currentY] = true;
        int count = 1;
        boolean possible = true;

        while (possible && K-- != 0) {
            int command = scanner.nextInt();
            int distance = scanner.nextInt();

            switch (command) {
                case 1:
                    for (int j = 0; j < distance; j++) {
                        possible = isPossible(isVisited, currentX, --currentY);
                        if (possible) {
                            count++;
//                            System.out.println(currentX);
//                            System.out.println(currentY);
//                            System.out.println(count);
                            continue;
                        } else {
                            break;
                        }
                    }
                    break;

                //System.out.println(possible);
                case 2:
                    for (int j = 0; j < distance; j++) {
                        possible = isPossible(isVisited, currentX, ++currentY);
                        if (possible) {
                            count++;
//                            System.out.println(currentX);
//                            System.out.println(currentY);
//                            System.out.println(count);
                            continue;
                        }else {
                            break;
                        }
                    }
                    break;

                case 3:
                    for (int j = 0; j < distance; j++) {
                        possible = isPossible(isVisited, --currentX, currentY);
                        if (possible) {
                            count++;
                            continue;
                        }else {
                            break;
                        }
                    }
                    break;

                case 4:
                    for (int j = 0; j < distance; j++) {
                        possible = isPossible(isVisited, ++currentX, currentY);
                        if (possible) {
                            count++;
                            continue;
                        }else {
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        for (int i = 0; i < K; i++) {
            int tempX = scanner.nextInt();
            int tempY = scanner.nextInt();
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        Main app = new Main();
        int testCase = scanner.nextInt();
        for (int i = 0; i < testCase; i++) {
            app.testCase();
        }
    }
}
