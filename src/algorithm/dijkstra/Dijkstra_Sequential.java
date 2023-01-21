package algorithm.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Dijkstra_Sequential {
    static int n, m, start;
    static int[] d;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void dijkstra() {
        d[start] = 0;
        visited[start] = true;

        for (int i = 0; i < graph.get(start).size(); i++) {
            d[graph.get(start).get(i).index] = graph.get(start).get(i).distance;
        }

        for (int i = 0; i < n - 1; i++) {
            int now = getSmallestNode();
            visited[now] = true;

            for (int j = 0; j < graph.get(now).size(); j++) {
                int cost = d[now] + graph.get(now).get(j).distance;
                if (cost < d[graph.get(now).get(j).index]) {
                    d[graph.get(now).get(j).index] = cost;
                }
            }
        }
    }

    private static int getSmallestNode() {
        int min = Integer.MAX_VALUE;
        int idx = 0;
        for (int i = 1; i <= n; i++) {
            if (d[i] < min && !visited[i]) {
                min = d[i];
                idx = i;
            }
        }

        return idx;
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
        visited = new boolean[n + 1];

        Arrays.fill(d, Integer.MAX_VALUE);

        dijkstra();

        for (int i = 1; i <= n; i++) {
            System.out.println(d[i]);
        }
    }

}
