import java.io.*;
import java.util.*;

public class Main {
    static int n, m;

    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    static void union(int nodeA, int nodeB) {
        int rootA = find(nodeA);
        int rootB = find(nodeB);
        if (rootA == rootB) {
            return;
        }

        arr[rootB] = rootA;
    }

    static int find(int node) {
        if (node == arr[node]) {
            return node;
        }

        return arr[node] = find(arr[node]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        for (int idx = 0; idx <= n; idx++) {
            arr[idx] = idx;
        }

        for (int input = 1; input <= m; input++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (order == 0) {
                union(a, b);
            } else {
                sb.append(find(a) == find(b) ? "yes" : "no").append("\n");
            }
        }

        System.out.println(sb);
    }
}
