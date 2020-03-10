import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main{
    public static final Scanner scanner = new Scanner(System.in);

    public boolean hasWormHole (int [] distance, Node[] nodes) {
        for(int i=2; i<nodes.length; i++) {
            //돌아가는 길이 있다면
            if(nodes[i].weightArr[1] !=0) {
                distance[i] = distance[i] + nodes[i].weightArr[1];
                if(distance[i]<0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void isShortestPath(Node [] nodes) {
        int length = nodes.length;

        boolean [] isVisited = new boolean[length];
        int [] distance = new int[length];
        Arrays.fill(distance, Integer.MAX_VALUE);

        State initialState = new State(nodes[1], 0);
        PriorityQueue<State> usableState = new PriorityQueue<>();
        usableState.add(initialState);


        while(usableState.isEmpty()==false) {
            State currentState = usableState.poll();
            Node currentNode = currentState.currentNode;

            if(isVisited[currentNode.index]) {
                continue;
            }

            distance[currentNode.index] = currentState.totalWeight;
            isVisited[currentNode.index] = true;

            for(int i=1; i<length; i++) {

                //각각의 노드마다 배열에 저장된 엣지의 갯수 (일단 node의 수만큼 반복 -> 값이 저장된 경우에만 queue에 삽입)
                if(currentNode.weightArr[i]!=0) {

                    Node nextNode = nodes[i];

                    if(isVisited[nextNode.index]) {
                        continue;
                    }

                    int tempWeight = currentState.totalWeight + currentNode.weightArr[i];
                    State nextState = new State(nextNode, tempWeight);
                    usableState.add(nextState);
                }
            }

        }

        boolean answer = hasWormHole(distance, nodes);

        if(answer) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }

    public void testCase(int caseIndex) {
        int node = scanner.nextInt();
        int edge = scanner.nextInt();
        int wormHole = scanner.nextInt();

        Node[] nodeList = new Node[node+1];

        for(int i=0; i<node; i++) {
            nodeList[i+1] = new Node(i+1, node);
        }

        //방향성 존재하지 않는 Edge
        for(int i=0; i<edge; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = scanner.nextInt();

            if (nodeList[u].weightArr[v] != 0) {
                weight = Math.min(weight, nodeList[u].weightArr[v]);

                nodeList[u].addWeight(v, weight);
                nodeList[v].addWeight(u, weight);
            }
            else {
                nodeList[u].addWeight(v, weight);
                nodeList[v].addWeight(u, weight);
            }
        }

        //방향성 + 음수인 weight가 존재할 수 있는 Edge
        for(int i=0; i<wormHole; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = -scanner.nextInt();

            nodeList[u].addWeight(v, weight);
        }
        isShortestPath(nodeList);

//        for(int i=0; i<node; i++) {
//            for(int j=0; j<node; j++) {
//                System.out.println("["+(i+1)+"]"+"["+(j+1)+"]"+"="+nodeList[i+1].weightArr[j+1]);
//            }
//        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        int testCase = scanner.nextInt();

        for(int i=0; i<testCase; i++) {
            app.testCase(i);
        }
    }
}
class Node {
    int index;
    int [] weightArr;
    public Node(int index, int numberOfNode) {
        this.index = index;
        this.weightArr = new int[numberOfNode+1];
    }
    public void addWeight(int targetIndex, int weight) {
        weightArr[targetIndex] = weight;
    }
}

class State implements Comparable<State>{
    Node currentNode;
    int totalWeight;
    public State(Node currentNode, int totalWeight) {
        this.currentNode = currentNode;
        this.totalWeight = totalWeight;
    }
    @Override
    public int compareTo(State other) {
        return this.totalWeight - other.totalWeight;
    }
}