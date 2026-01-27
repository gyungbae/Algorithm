import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parentArr;

    static int find(int node) {
        if (parentArr[node] == node) 
            return node;
        
        return parentArr[node] = find(parentArr[node]);
    }

    static void union(int nodeA, int nodeB) {
        nodeA = find(nodeA);
        nodeB = find(nodeB);
        if (nodeA != nodeB) 
            parentArr[nodeB] = nodeA;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int truthSize = Integer.parseInt(st.nextToken());
        int[] truth = new int[truthSize];
        for (int idx = 0; idx < truthSize; idx++) 
            truth[idx] = Integer.parseInt(st.nextToken());

        if (truthSize == 0) { 
            System.out.println(M);
            return;
        }

        parentArr = new int[N + 1];
        for (int idx = 1; idx <= N; idx++) 
            parentArr[idx] = idx;

        List<int[]> parties = new ArrayList<>();
        for (int input = 0; input < M; input++) {
            st = new StringTokenizer(br.readLine());
            int partySize = Integer.parseInt(st.nextToken());
            int[] party = new int[partySize];
            for (int idx = 0; idx < partySize; idx++) 
                party[idx] = Integer.parseInt(st.nextToken());
            
            parties.add(party);

            for (int idx = 1; idx < partySize; idx++) 
                union(party[0], party[idx]);
        }

        Set<Integer> truthRoots = new HashSet<>();
        for (int t : truth) 
            truthRoots.add(find(t));

        int answer = 0;
        for (int[] party : parties) {
            boolean canLie = true;
            for (int person : party) {
                if (truthRoots.contains(find(person))) {
                    canLie = false;
                    break;
                }
            }
            if (canLie) answer++;
        }

        System.out.println(answer);
    }
}
