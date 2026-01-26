import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int input = 0; input < N; input++) {
            queue.offer(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        while (queue.size() > 1) {
            int num1 = queue.poll();
            int num2 = queue.poll();
            int sum = num1 + num2;

            answer += sum;
            queue.offer(sum);
        }

        System.out.println(answer);
    }
}
