import java.io.*;
import java.util.*;

public class Main {
    static int N, K;

    static int[] record;

    static void BFS() {
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(N);
        record[N] = 0;

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == K) {
                return;
            }

            int next = current - 1;
            if (next >= 0 && record[next] == -1) {
                q.offer(next);
                record[next] = record[current] + 1;
            }

            next = current + 1;
            if (next <= 100000 && record[next] == -1) {
                q.offer(next);
                record[next] = record[current] + 1;
            }

            next = current * 2;
            if (next <= 100000 && record[next] == -1) {
                q.offer(next);
                record[next] = record[current] + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        record = new int[100001];
        Arrays.fill(record, -1);
        BFS();

        System.out.println(record[K]);
    }
}
