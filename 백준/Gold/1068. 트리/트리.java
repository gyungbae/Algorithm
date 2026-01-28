import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] adjList;
    static int deleteNode;

    static int leafNodeCnt;
    static boolean[] removed;

    static void delete(int currentNode) {
        removed[currentNode] = true;

        for (int child : adjList[currentNode]) {
            if (!removed[child]) {
                delete(child);
            }
        }
    }

    static int findLeafNode() {
        int leafNodeCnt = 0;
        for (int node = 0; node < N; node++) {
            if (removed[node]) {
                continue;
            }

            boolean isLeaf = true;
            for (int child : adjList[node]) {
                if (!removed[child]) {
                    isLeaf = false;
                    break;
                }
            }

            if (isLeaf) {
                leafNodeCnt++;
            }
        }

        return leafNodeCnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N];
        for (int node = 0; node < N; node++) {
            adjList[node] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int current = 0; current < N; current++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1) {
                adjList[parent].add(current);
            }
        }

        deleteNode = Integer.parseInt(br.readLine());

        removed = new boolean[N];
        delete(deleteNode);

        System.out.println(findLeafNode());
    }
}
