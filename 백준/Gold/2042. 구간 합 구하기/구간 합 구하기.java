import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static long[] inputArr;

    static long[] segmentTree;

    static long buildTree(int currentNode, int minNode, int maxNode) {
        if (minNode == maxNode) {
            return segmentTree[currentNode] = inputArr[maxNode];
        }

        int midNode = (minNode + maxNode) / 2;
        long leftSum = buildTree(currentNode * 2, minNode, midNode);
        long rightSum = buildTree(currentNode * 2 + 1, midNode + 1, maxNode);

        return segmentTree[currentNode] = leftSum + rightSum;
    }

    static void updateTree(int currentNode, int minNode, int maxNode, int changeNode, long changeValue) {
        if (minNode > changeNode || maxNode < changeNode) {
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

        segmentTree[currentNode] = segmentTree[leftChildNode] + segmentTree[rightChildNode];
    }

    static long getSum(int currentNode, int minNode, int maxNode, int from, int to) {
        if (minNode > to || maxNode < from) {
            return 0;
        }

        if (minNode >= from && maxNode <= to) {
            return segmentTree[currentNode];
        }

        int midNode = (minNode + maxNode) / 2;
        long leftSum = getSum(currentNode * 2, minNode, midNode, from, to);
        long rightSum = getSum(currentNode * 2 + 1, midNode + 1, maxNode, from, to);

        return leftSum + rightSum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        inputArr = new long[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            inputArr[idx] = Long.parseLong(br.readLine());
        }

        segmentTree = new long[N * 4];
        buildTree(1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int input = 1; input <= M + K; input++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            long to = Long.parseLong(st.nextToken());

            if (order == 1) {
                updateTree(1, 1, N, from, to);
            } else {
                sb.append(getSum(1, 1, N, from, (int) to)).append("\n");
            }
        }

        System.out.println(sb);
    }
}
