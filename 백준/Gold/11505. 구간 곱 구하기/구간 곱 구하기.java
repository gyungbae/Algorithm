import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[] inputArr;

    static long[] segmentTree;
    static final int DIVIDE = 1000000007;

    static long buildTree(int currentNode, int minNode, int maxNode) {
        if (minNode == maxNode) {
            return segmentTree[currentNode] = inputArr[maxNode];
        }

        int midNode = (minNode + maxNode) / 2;
        long left = buildTree(currentNode * 2, minNode, midNode);
        long right = buildTree(currentNode * 2 + 1, midNode + 1, maxNode);

        return segmentTree[currentNode] = (left * right) % DIVIDE;
    }

    static void updateTree(int currentNode, int minNode, int maxNode, int changeNode, int changeValue) {
        if (changeNode < minNode || changeNode > maxNode) {
            return;
        }

        if (minNode == maxNode) {
            segmentTree[currentNode] = changeValue;
            return;
        }

        int midNode = (minNode + maxNode) / 2;
        int leftChildNode = currentNode * 2;
        int rightChildNode = currentNode * 2 + 1;
        updateTree(leftChildNode, minNode, midNode, changeNode, changeValue);
        updateTree(rightChildNode, midNode + 1, maxNode, changeNode, changeValue);

        segmentTree[currentNode] = (segmentTree[leftChildNode] * segmentTree[rightChildNode]) % DIVIDE;
    }

    static long getProduct(int currentNode, int minNode, int maxNode, int from, int to) {
        if (from > maxNode || to < minNode) {
            return  1;
        }

        if (from <= minNode && maxNode <= to) {
            return segmentTree[currentNode];
        }

        int midNode = (minNode + maxNode) / 2;
        long left = getProduct(currentNode * 2, minNode, midNode, from, to);
        long right = getProduct(currentNode * 2 + 1, midNode + 1, maxNode, from, to);

        return (left * right) % DIVIDE;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        inputArr = new int[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            inputArr[idx] = Integer.parseInt(br.readLine());
        }

        segmentTree = new long[N * 4];
        buildTree(1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int input = 1; input <= M + K; input++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (order == 1) {
                updateTree(1, 1, N, from, to);
            } else {
                sb.append(getProduct(1, 1, N, from, to)).append("\n");
            }
        }

        System.out.println(sb);
    }
}
