import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Deque<Integer> dq = new ArrayDeque<>();
        for (int num = 1; num <= N; num++) {
            dq.offer(num);
        }

        while (dq.size() > 1) {
            dq.poll();
            int num = dq.poll();
            dq.offer(num);
        }

        System.out.println(dq.poll());
    }
}
