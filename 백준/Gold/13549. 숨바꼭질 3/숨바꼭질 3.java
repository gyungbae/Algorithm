import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static final int MAX = 100000;
    static int[] dist = new int[MAX + 1];

    static void BFS() {
        Deque<Integer> dq = new ArrayDeque<>();
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[N] = 0;
        dq.add(N);

        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();

            int next = cur * 2;
            if (next <= MAX && dist[next] > dist[cur]) {
                dist[next] = dist[cur];
                dq.addFirst(next);
            }

            next = cur + 1;
            if (next <= MAX && dist[next] > dist[cur] + 1) {
                dist[next] = dist[cur] + 1;
                dq.addLast(next);
            }

            next = cur - 1;
            if (next >= 0 && dist[next] > dist[cur] + 1) {
                dist[next] = dist[cur] + 1;
                dq.addLast(next);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        BFS();
        System.out.println(dist[K]);
    }
}
