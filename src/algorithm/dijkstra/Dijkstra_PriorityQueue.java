package algorithm.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra_PriorityQueue {
    static int n, m, start;
    static int[] d;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void dijkstra() {
        d[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node n = pq.poll();
            int now = n.index;
            int dist = n.distance;

            if (d[now] < dist) continue;

            for (int j = 0; j < graph.get(now).size(); j++) {
                Node node = graph.get(now).get(j);
                int cost = d[now] + node.distance;
                if (cost < d[node.index]) {
                    d[node.index] = cost;
                    pq.offer(new Node(node.index, cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
        }
        br.close();

        d = new int[n + 1];

        Arrays.fill(d, Integer.MAX_VALUE);

        dijkstra();

        for (int i = 1; i <= n; i++) {
            System.out.println(d[i]);
        }
    }
}
