import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] inputArr;

    static int[] segmentTree;

    static int buildTree(int currentNode, int minNode, int maxNode) {
        if (minNode == maxNode) {
            return segmentTree[currentNode] = inputArr[minNode];
        }

        int midNode = minNode + (maxNode - minNode) / 2;
        int leftMin = buildTree(currentNode * 2, minNode, midNode);
        int rightMin = buildTree(currentNode * 2 + 1, midNode + 1, maxNode);

        return segmentTree[currentNode] = Math.min(leftMin, rightMin);
    }

    static int getMin(int currentNode, int minNode, int maxNode, int from, int to) {
        if (from > maxNode || to < minNode) {
            return Integer.MAX_VALUE;
        }

        if (from <= minNode && maxNode <= to) {
            return segmentTree[currentNode];
        }

        int midNode = minNode + (maxNode - minNode) / 2;
        int leftMin = getMin(currentNode * 2, minNode, midNode, from, to);
        int rightMin = getMin(currentNode * 2 + 1, midNode + 1, maxNode, from, to);

        return Math.min(leftMin, rightMin);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputArr = new int[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            inputArr[idx] = Integer.parseInt(br.readLine());
        }

        segmentTree = new int[N * 4];
        buildTree(1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int input = 1; input <= M; input++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            sb.append(getMin(1, 1, N, from, to)).append("\n");
        }

        System.out.println(sb);
    }
}
