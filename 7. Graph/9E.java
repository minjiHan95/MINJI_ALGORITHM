import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public void findKruskalPath(int node, Edge[] myEdge) {
        int depth = myEdge.length;
        int count = 0;
        int totalWeight = 0;

        boolean[] isVisited = new boolean[node+1];
        Queue<Edge> connectedEdge = new LinkedList<>();

        Edge initialEdge = myEdge[0];
        connectedEdge.add(initialEdge);

        while(connectedEdge.isEmpty()==false) {
            Edge currentEdge = connectedEdge.poll();
            int u = currentEdge.u;
            int v = currentEdge.v;

            //실행횟수 초가
            if(count>=depth) {
                continue;
            }
            //둘 다 방문한 노드라면 패스
            if(isVisited[u] && isVisited[v]) {
                continue;
            }
            isVisited[u] = isVisited[v] = true;
            totalWeight += currentEdge.weight;
            count ++;

            for(int i=1; i<depth; i++) {
                Edge nextEdge = myEdge[i];
                connectedEdge.add(nextEdge);
            }
        }
        System.out.println(totalWeight);
    }

    public static void main(String[] args) {
        Main app= new Main();

        int node = scanner.nextInt();
        int edge = scanner.nextInt();
        Edge[] myEdge = new Edge [edge];

        for(int i=0; i<edge; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = scanner.nextInt();
            myEdge[i] = new Edge(u, v, weight);
        }
        Arrays.sort(myEdge);
        app.findKruskalPath(node, myEdge);
    }
}
class Edge implements Comparable<Edge>{
    int u;
    int v;
    int weight;
    public Edge (int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        if(this.weight<o.weight) return -1;
        else if(this.weight>o.weight) return 1;
        else return 0;
    }
}
