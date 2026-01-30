import java.io.*;
import java.util.*;

public class Main {
    static int V, E;
    static List<int[]> edgeList;

    static int[] unionArr;
    static int edgeCount;
    static long answer;

    static void linkNode(int[] edge) {
        int node1 = edge[0];
        int node2 = edge[1];
        int weight = edge[2];

        if (find(node1) == find(node2)) {
            return;
        }

        union(node1, node2);
        answer += weight;
        edgeCount++;
    }

    static void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 != root2) {
            unionArr[root2] = root1;
        }
    }

    static int find(int node) {
        if (unionArr[node] == node) {
            return node;
        }

        int root = find(unionArr[node]);
        return unionArr[node] = root;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();
        for (int edge = 1; edge <= E; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList.add(new int[]{from, to, weight});
        }
        Collections.sort(edgeList, (o1, o2) -> o1[2] - o2[2]);

        unionArr = new int[V + 1];
        for (int node = 1; node <= V; node++) {
            unionArr[node] = node;
        }
        for (int[] edge : edgeList) {
            linkNode(edge);
            if (edgeCount == V - 1) {
                break;
            }
        }

        System.out.println(answer);
    }
}
