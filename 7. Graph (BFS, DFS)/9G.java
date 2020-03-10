import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main{
    public static final Scanner scanner = new Scanner(System.in);

    public int[] findShortestPath(Node origin, Node[] nodeList) {
        int length = nodeList.length;
        PriorityQueue<State> usableWeight = new PriorityQueue<>();
        State initialState = new State(origin, 0);
        usableWeight.add(initialState);

        int [] distance = new int[length];
        boolean [] isVisited = new boolean[length];
        Arrays.fill(distance, Integer.MAX_VALUE);

        while(usableWeight.isEmpty()==false) {
            State currentState = usableWeight.poll();
            Node currentNode = currentState.currentNode;

            if(isVisited[currentNode.nodeIndex]) {
                continue;
            }

            distance[currentNode.nodeIndex] = currentState.totalWeight;
            isVisited[currentNode.nodeIndex] = true;

            for(Edge e : currentNode.myEdgeList){
                Node nextNode = e.v;
                if(isVisited[nextNode.nodeIndex] == true){
                    continue;
                }

                int nextTotalWeight = currentState.totalWeight + e.weight;

                State nextState = new State(nextNode, nextTotalWeight);

                usableWeight.add( nextState );
            }

//            for(int i=0; i<currentNode.myEdgeList.size(); i++) {
//                Node nextNode = currentNode.myEdgeList.get(i).v;
//
//                for(int j = 0; j<nextNode.myEdgeList.size(); j++) {
//                    if(isVisited[nextNode.nodeIndex]) {
//                        continue;
//                    }
//                    Edge myEdge = nextNode.myEdgeList.get(j);
//                    int tempWeight = currentState.totalWeight + myEdge.weight;
//                    State nextState = new State(nextNode, tempWeight);
//                    usableWeight.add(nextState);
//                }
//            }
        }
        return distance;
    }

    public static void main(String[] args) {
        Main app = new Main();

        int node = scanner.nextInt();
        int edge = scanner.nextInt();
        int findIndex = scanner.nextInt();

        Node[] nodeList = new Node[node+1];
        for(int i=1; i<node+1; i++) {
            nodeList[i] = new Node(i);
        }

        for(int i=0; i<edge; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = scanner.nextInt();

            nodeList[u].addEdgeWeight(new Edge(nodeList[u], nodeList[v], weight));
        }
        int [] distance = app.findShortestPath(nodeList[findIndex], nodeList);
        //경로 찾기
        for(int i=0; i<node; i++) {
            if(i+1==findIndex) {
                System.out.println(0);
            }
            else {
                if(distance[i+1]==Integer.MAX_VALUE) {
                    System.out.println("INF");
                }
                else {
                    System.out.println(distance[i+1]);
                }
            }
        }
    }

}
class Node {
    int nodeIndex;
    ArrayList<Edge> myEdgeList;
    public Node (int nodeIndex) {
        this.nodeIndex = nodeIndex;
        myEdgeList = new ArrayList<>();
    }
    public void addEdgeWeight(Edge edge) {
        myEdgeList.add(edge);
    }
}
class Edge{
    Node u;
    Node v;
    int weight;

    public Edge(Node u, Node v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

}
class State implements Comparable<State>{
    Node currentNode;
    int totalWeight;

    public State (Node currentNode, int totalWeight) {
        this.currentNode = currentNode;
        this.totalWeight = totalWeight;
    }
    @Override
    public int compareTo(State o) {
        if(this.totalWeight<o.totalWeight) return -1;
        else if(this.totalWeight>o.totalWeight) return 1;
        else return 0;
    }
}