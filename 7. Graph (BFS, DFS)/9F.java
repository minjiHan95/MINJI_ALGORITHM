import java.util.*;

public class Main{
    public static Scanner scanner = new Scanner(System.in);

    public void findPrimsPath(int node,int edge, Node[] myGraph) {
        boolean [] isVisited =  new boolean[node+2];
        PriorityQueue<Edge> connectedGraph = new PriorityQueue<>();

        for(int i=0; i<myGraph[0].myEdge.size(); i++) {
            connectedGraph.add(myGraph[0].myEdge.get(i));
        }

        int totalWeight = 0;

        while(connectedGraph.isEmpty()==false) {
            Edge currentEdge = connectedGraph.poll();

            Node currentNode = currentEdge.u;
            Node nextNode = currentEdge.v;
            int weight = currentEdge.weight;

            if(isVisited[nextNode.index]) {
                continue;
            }

            totalWeight += weight;
            //System.out.println(totalWeight+" "+"currentNode"+currentNode.index+"weight"+weight);

            isVisited[currentNode.index] = isVisited[nextNode.index] = true;
            ArrayList<Edge> connectedEdge = nextNode.myEdge;
            for(int i=0; i<connectedEdge.size(); i++) {

                Edge nextEdge = connectedEdge.get(i);
                if(isVisited[nextEdge.v.index]){
                    continue;
                }
                connectedGraph.add(nextEdge);
            }
        }

        System.out.println(totalWeight);

    }

    public static void main(String[] args) {
        Main app = new Main();
        int node = scanner.nextInt();
        int edge = scanner.nextInt();
        Node[] myNode = new Node[node+1];

        for(int i=0; i<node; i++) {
            myNode[i] = new Node(i+1);
        }

        for(int i=0; i<edge; i++) {
            int u = scanner.nextInt()-1;
            int v = scanner.nextInt()-1;
            int weight = scanner.nextInt();

            Edge origin = new Edge(myNode[u], myNode[v], weight);
            Edge dest = new Edge(myNode[v], myNode[u], weight);
            myNode[u].myEdge.add(origin);
            myNode[v].myEdge.add(dest);
        }
        app.findPrimsPath(node, edge, myNode);
    }

}
class Node {
    int index;
    ArrayList<Edge> myEdge = new ArrayList<>();

    public Node(int index) {
        this.index =index;
    }
}
class Edge implements Comparable<Edge>{
    Node u;
    Node v;
    int weight;
    public Edge(Node u, Node v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        if(this.weight < o.weight) return -1;
        else if(this.weight > o.weight) return 1;
        else return 0;
    }
}