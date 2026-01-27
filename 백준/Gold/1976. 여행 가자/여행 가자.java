import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;

    static int[] parentArr;

    static void union(int nodeA, int nodeB) {
        int rootA = find(nodeA);
        int rootB = find(nodeB);
        if (rootA == rootB) {
            return;
        }

        parentArr[rootB] = rootA;
    }

    static int find(int node) {
        if (parentArr[node] == node) {
            return node;
        }

        int root = find(parentArr[node]);
        parentArr[node] = root;
        return root;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parentArr = new int[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            parentArr[idx] = idx;
        }

        for (int fromNode = 1; fromNode <= N; fromNode++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int toNode = 1;
            while (st.hasMoreTokens()) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(fromNode, toNode);
                }

                toNode++;
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        while (st.hasMoreTokens()) {
            int toNode = Integer.parseInt(st.nextToken());
            if (find(startNode) != find(toNode)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }
}
