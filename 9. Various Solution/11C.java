import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static Node myNode;

    //존재하는 모든 노드들을 탐색하면서 그래프가 모두 인접한지 확인한다.
    //if) 인접한 그래프라면 -> 통과할 수 있는 경로가 존재하지 않다.
    //else) 인접하지 않는다면 -> 통과할 수 있는 경로가 존재한다.
    //가장 작은 length가 문제의 solution이 된다.
    public boolean isConnectedGraph(Node myNode) {
        Stack<Integer> availableNode = new Stack<>();
        boolean[] isVisited = new boolean[myNode.V + 1];
        int initialNode = 0;
        availableNode.add(initialNode);

        while (availableNode.isEmpty() == false) {
            int currentNode = availableNode.pop();
            if (isVisited[currentNode]) {
                continue;
            }
            isVisited[currentNode] = true;

            for (int i = myNode.V; i > 0; i--) {
                if (myNode.isConnected[currentNode][i]) {
                    availableNode.add(i);
                }
            }
        }
        //만약 방문하지 않은 노드가 하나라도 있다면 인접하지 않은 그래프이다.
        for (int i = 0; i < myNode.V; i++) {
            if (!isVisited[i]) return false;
        }
        return true;
    }

    //position과 반지름의 값을 계산해서 두 노드가 서로 인접한지 확인한다.
    //만약 둘이 연결되어 있는 노드라면, Node에 둘이 인접하다는 것을
    //둘이 인접하지 않는다면 둘의 거리를 저장한다. (후에 최소의 길이를 찾을 때 사용)
    //true라면 -1을 반환, 아니라면 둘의 거리를 반환

    //벽과 인접한 노드는... 처음부터 설정할 수 있음..
    public double addConnectedNode(Sensor[] mySensor) {
        int length = mySensor.length;
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                int x = mySensor[i].x - mySensor[j].x;
                int y = mySensor[i].y - mySensor[j].y;
                double distance = Math.sqrt(x * x + y * y);
                if (distance <= mySensor[i].r + mySensor[j].r) {
                    myNode.addEdge(i+1, j+1);
                } else {
                    minDistance = Math.min(minDistance, distance - (mySensor[i].r + mySensor[j].r));
                }
            }
        }
        return minDistance;
    }

    public void testCase() {
        int wall = scanner.nextInt();
        int node = scanner.nextInt();
        Sensor[] mySensor = new Sensor[node];
        //처음 노드: 왼쪽 벽
        //마지막 노드: 오른쪽 벽
        myNode = new Node(node + 2);
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < node; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int r = scanner.nextInt();

            if (x < r) {
                myNode.addEdge(0, i+1);
                minDistance = Math.abs(Math.min(minDistance, r-x));
            } else if (wall - x < r) {
                myNode.addEdge(node + 1, i+1);
                minDistance = Math.abs(Math.min(minDistance, r-x));
            }
            mySensor[i] = new Sensor(x, y, r);
        }

        double answer = addConnectedNode(mySensor);
        minDistance = Math.min(minDistance, answer);
        if (isConnectedGraph(myNode)) {
            System.out.printf("%.3f", 0.000);
        } else {
            System.out.printf("%.3f", (minDistance)/2);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Main app = new Main();
        int testCase = scanner.nextInt();
        for (int i = 0; i < testCase; i++) {
            app.testCase();
        }
    }
}

class Node {
    int V;
    boolean[][] isConnected;

    public Node(int V) {
        this.V = V;
        isConnected = new boolean[V+1][V+1];
    }

    public void addEdge(int myIndex, int otherIndex) {
        isConnected[myIndex][otherIndex] = isConnected[otherIndex][myIndex] = true;
    }

}

class Sensor {
    int x;
    int y;
    int r;

    public Sensor(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}