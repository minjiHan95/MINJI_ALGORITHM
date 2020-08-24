import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static ArrayList<Node>[] nodeList;
    static int[] distance;
    static boolean[] isVisited;
    static int n;
    static int m;


    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        n = Integer.parseInt(reader.readLine());
        m = Integer.parseInt(reader.readLine());

        nodeList = new ArrayList[n];
        distance = new int[n];
        isVisited = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            nodeList[i] = new ArrayList<>();
        }
        Arrays.fill(distance, 987654321);

        //input
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            nodeList[from].add(new Node(to, weight));
        }

        st = new StringTokenizer(reader.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int dest = Integer.parseInt(st.nextToken()) - 1;

        int answer = dijkstra(start, dest);

        writer.write(String.valueOf(answer));
        writer.flush();
        writer.close();
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> available = new PriorityQueue<>();
        Node initialNode = new Node(start, 0);
        available.add(initialNode);
        distance[start] = 0;

        while (!available.isEmpty()) {
            Node current = available.poll();

            if(distance[current.end] < current.weight) continue;
            //isVisited[current.end] = true;

            for (Node nextNode : nodeList[current.end]) {
                if (distance[nextNode.end] > distance[current.end] + nextNode.weight) {
                    distance[nextNode.end] = distance[current.end] + nextNode.weight;
                    available.add(new Node(nextNode.end, distance[nextNode.end]));
                }
            }
        }

        return distance[end];
    }

}

class Node implements Comparable<Node> {
    int end;
    int weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

