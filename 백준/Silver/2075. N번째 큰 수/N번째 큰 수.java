import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Queue<Integer> queue = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int num = Integer.parseInt(st.nextToken());

                if (queue.size() < N) {
                    queue.offer(num);
                } else {
                    if (queue.peek() < num) {
                        queue.poll();
                        queue.offer(num);
                    }
                }
            }
        }

        System.out.println(queue.peek());
    }
}
