import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }

            return o1[0] - o2[0];
        } );

        for (int input = 1; input <= N; input++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{x, y});
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            sb.append(arr[0] + " " + arr[1]).append("\n");
        }

        System.out.println(sb);
    }
}
