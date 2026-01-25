import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Map<Integer, Integer> map;

    static int[] memo;

    static void DFS(int current, int depth) {
        if (current > 100) {
            return;
        }

        if (memo[current] <= depth) {
            return;
        }

        memo[current] = depth;
        if (map.containsKey(current)) {
            current = map.get(current);
        }

        for (int dice = 1; dice <= 6; dice++) {
            DFS(current + dice, depth + 1);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        for (int input = 1; input <= N + M; input++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map.put(from, to);
        }

        memo = new int[101];
        Arrays.fill(memo, Integer.MAX_VALUE);
        DFS(1, 0);

        System.out.println(memo[100]);
    }
}
