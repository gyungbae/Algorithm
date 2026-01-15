import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int input = 1; input <= N; input++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                if (!pq.isEmpty()) {
                    sb.append(pq.poll()).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else{
                pq.offer(num);
            }
        }

        System.out.println(sb);
    }
}
