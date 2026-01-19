import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }

            return o1[1] - o2[1];
        });
        for (int input = 1; input <= N; input++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.offer(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        int answer = 0;
        int endTime = 0;
        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            if (endTime <= info[0]) {
                answer++;
                endTime = info[1];
            }
        }

        System.out.println(answer);
    }
}
