package algorithm.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TopologicalSort {
    public static int v, e;
    public static int[] inDegree = new int[100001];
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void topologySort() {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= v; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            result.add(now);

            for (int i = 0; i < graph.get(now).size(); i++) {
                inDegree[graph.get(now).get(i)] -= 1;
                if (inDegree[graph.get(now).get(i)] == 0) {
                    q.offer(graph.get(now).get(i));
                }
            }
        }

        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}
