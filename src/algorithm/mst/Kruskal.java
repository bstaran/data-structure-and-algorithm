package algorithm.mst;

import datastructure.DisjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Kruskal {

    static ArrayList<Edge> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[] nodes = new int[v + 1];

        for (int i = 1; i <= v; i++)
            nodes[i] = i;

        graph = new ArrayList<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.add(new Edge(a, b, c));
        }

        Collections.sort(graph);

        DisjointSet ds = new DisjointSet();

        int answer = 0;
        for (Edge edge : graph) {
            int a = edge.nodeA();
            int b = edge.nodeB();
            int cost = edge.cost();
            if (ds.find(nodes, a) != ds.find(nodes, b)) {
                ds.union(nodes, a, b);
                answer += cost;
            }
        }

        System.out.println(answer);
    }
}

record Edge(int nodeA, int nodeB, int cost) implements Comparable<Edge> {

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}
